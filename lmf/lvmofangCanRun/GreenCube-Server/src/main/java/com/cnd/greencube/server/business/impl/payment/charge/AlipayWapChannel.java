package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.config.SysConfiguration;
import com.pingplusplus.model.Charge;

/**
 * alipay_wap alipay_wap 适用于手机网页支付，需开通支付宝手机网页支付服务，发起 alipay_wap 的支付请求需要额外两个参数：
 * success_url 是指的支付成功后的 Client 同步通知处理地址。 cancel_url是指的支付取消后的 Client 同步通知处理地址。
 * 这两个 url 不要在页面文件的后面再加上自定义参数，例如：
 * 
 * 错误的写法： http://www.alipay.com/alipay/return_url.php?xx= 正确的写法：
 * http://www.alipay.com/alipay/return_url.php Ping++ 把这些参数放在了 Charge 对象的 extra
 * 字段里，在发起交易请求的时候需要在 extra 里填写这两个参数。
 * 
 * @author huxg
 * 
 */
public class AlipayWapChannel extends AbstractChannel implements IChannel {
	public AlipayWapChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();

		Map<String, String> extramap = new HashMap<String, String>();
		extramap.put("success_url", SysConfiguration.getProperty("webgate.url") + "/cbtrigger/payment/success/" + paymentId);
		extramap.put("cancel_url", SysConfiguration.getProperty("webgate.url") + "/cbtrigger/payment/cancel/" + paymentId);
		chargeParams.put("extra", extramap);
		return Charge.create(chargeParams);
	}

}
