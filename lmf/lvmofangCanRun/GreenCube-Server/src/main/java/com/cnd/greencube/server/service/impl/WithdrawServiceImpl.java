package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cnd.greencube.server.business.UserAccountBusiness;
import com.cnd.greencube.server.business.WithdrawBusiness;
import com.cnd.greencube.server.entity.FiWithdraw;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.WithdrawService;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.TokenUtils;
import com.cnd.greencube.server.util.encrypt.Encryption;

/**
 * 提现服务实现类
 * 
 * @author 胡晓光
 * 
 */
public class WithdrawServiceImpl extends AbstractServiceImpl implements WithdrawService.Iface {
	private static final Logger logger = Logger.getLogger(WithdrawServiceImpl.class);

	@Resource(name = "WithdrawBusinessImpl")
	protected WithdrawBusiness withdrawBusiness;

	@Resource(name = "UserAccountBusinessImpl")
	protected UserAccountBusiness userAccountBusiness;
	
	/**
	 * 申请提现
	 */
	@Override
	public String applyWithdraw(String token, String password, String bankcardId, int amount) {
		try {
			// 此处判断用户虚拟账户密码是否正确
			String userid = TokenUtils.isTokenRight(token);
			VirtualAccount account = userAccountBusiness.getUserAccount(userid);
			if (account.getAccountPassword() == null) {
				// 未设置账户取款密码，需跳转到设置密码界面
				return Message.errorMessage(new String [] {"errno"}, new String [] {"-1"});
			}
			
			if (!account.getAccountPassword().equals(Encryption.encodeMD5(password))) {
				// 取款密码不正确，需提示用户
				return Message.errorMessage(new String [] {"errno"}, new String [] {"-3"});
			}
			
			if (account.getBalance() == null || account.getBalance().intValue() < amount) {
				// 账户余额不足
				return Message.errorMessage(new String [] {"errno"}, new String [] {"-2"});
			}
			
			FiWithdraw withdraw = withdrawBusiness.applyWithdraw(userid, password, bankcardId, amount);
			return Message.okMessage(withdraw);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return Message.errorMessage(e.getMessage());
		}
	}

	/**
	 * 返回提现信息
	 */
	@Override
	public String loadWithdraw(int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<FiWithdraw> withdraw = withdrawBusiness.loadWithdraw(pageInfo);
			return Message.okMessage(withdraw);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 通过提现申请
	 */
	@Override
	public String approveWithdraw(String id, String status) {
		try {
			withdrawBusiness.approveWithdraw(id, status);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 驳回提现申请
	 */
	@Override
	public String rejectWithdraw(String id) {
		try {
			withdrawBusiness.rejectWithdraw(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 返回在几个小时之内的转账申请
	 */
	@Override
	public String loadWithdrawApplyBeforeHours(int hour, int pageNum) {
		try {
			PageInfo pageInfo = null;
			if (pageNum >= 1) {
				pageInfo = initPageInfo(pageNum);
			}
			List<FiWithdraw> withdraw = withdrawBusiness.loadWithdrawApplyBeforeHours(hour, pageInfo);
			return Message.okMessage(withdraw);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
}
