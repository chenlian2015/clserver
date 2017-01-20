package com.cnd.greencube.web.admin.scheduler.b2ctransfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TServiceClient;

import com.cnd.greencube.server.entity.FiWithdraw;
import com.cnd.greencube.server.service.WithdrawService;
import com.cnd.greencube.web.base.api.MailAPI;
import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.plugin.sms.ISMSProvider;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.SpringUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.util.uuid.Key;
import com.cnd.greencube.web.base.vo.Message;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Transfer;

/**
 * B2C转账申请监控者 12小时内拉去一次数据，并进行转账服务
 * 
 * @author 胡晓光
 * 
 */
public class B2CRequestMonitor implements Runnable {
	@Override
	public void run() {
		try {
			// 从服务器端获取到提现申请，
			ThriftClientTemplate thriftTemplate = (ThriftClientTemplate) SpringUtils.getBean("ThriftClientTemplate");
			String result = thriftTemplate.execute("WithdrawService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					WithdrawService.Client withdrawService = (WithdrawService.Client) client;
					// 取得12小时之内的转账申请
					return withdrawService.loadWithdrawApplyBeforeHours(24, 0);
				}
			});
			List<FiWithdraw> withdraws = Message.unpackList(result, FiWithdraw.class);

			// 处理每一个转账请求
			for (FiWithdraw w : withdraws) {
				if (w.getTransferMethod() != null && w.getTransferMethod().intValue() == FiWithdraw.METHOD_WX) {
					// 微信转账
					if (StringUtils.isEmpty(w.getOpenId())){
						ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
						sms.sendMessage(SysConfiguration.getProperty("pay.upacp.b2cpay"), SysConfiguration.getProperty("健康检查 检测到用户的一笔提现申请服务器无法自动完成（申请微信提现，但缺少OpenID），请人工处理，ID：" + w.getId()));
						MailAPI.sendMail(SysConfiguration.getProperty("notify.system.email"), "【绿魔方】提现异常通知 请管理员注意并仔细检查" , " 检测到用户的一笔提现申请服务器无法自动完成（申请微信提现，但缺少OpenID），请人工处理！ID：" + w.getId(), false);
					} else {
						wxTransfer(w);
					}
				} else if (w.getTransferMethod() != null && w.getTransferMethod().intValue() == FiWithdraw.METHOD_BANK) {
					String b2cpay = SysConfiguration.getProperty("pay.upacp.b2cpay");
					boolean isEnableUpacB2c = Boolean.parseBoolean(b2cpay);
					if (false == isEnableUpacB2c) {
						// 自动使用银联代付功能支付
					} else {
						// 非银联代付的，讲采用人工转账。不予处理，可通过短信等形式提醒相关人员通知转账
						// 发送短信通知
						ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
						sms.sendMessage(SysConfiguration.getProperty("pay.upacp.b2cpay"), SysConfiguration.getProperty("健康检查 检测到用户的一笔提现申请已经超过12小时尚未进行转账，请及时处理！ID：" + w.getId()));
						MailAPI.sendMail(SysConfiguration.getProperty("notify.system.email"), "【绿魔方】提现异常通知 请管理员注意并仔细检查" , " 检测到用户的一笔提现记录已经超过12小时尚未进行转账，请及时处理！ID：" + w.getId(), false);
					}
				}
			}
		} catch (Exception e) {

		} finally {

		}
	}
	
	void wxTransfer (FiWithdraw withdraw){
		// 尝试通过Pin ++微信转账进行转账
		try {
			// 支付提交至服务器
			Pingpp.apiKey = SysConfiguration.getProperty("payment.pingpp.api.key");
			String orderId = Key.key();
			Map<String, Object> transferParams = new HashMap<String, Object>();
			transferParams.put("amount", withdraw.getAmount());
			transferParams.put("currency", "cny");
			transferParams.put("type", "b2c");
			transferParams.put("order_no", orderId);
			transferParams.put("channel", "wx_pub");
			transferParams.put("recipient", withdraw.getOpenId());
			transferParams.put("description", "企业转账（个人微信提现）" + orderId + "号");
			
			Map<String, String> app = new HashMap<String, String>();
			app.put("id", SysConfiguration.getProperty("payment.pingpp.app.id"));
			transferParams.put("app", app);
			Transfer transfer = Transfer.create(transferParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
