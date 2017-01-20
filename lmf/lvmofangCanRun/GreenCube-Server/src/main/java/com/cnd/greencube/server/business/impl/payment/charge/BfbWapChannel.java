package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.config.SysConfiguration;
import com.pingplusplus.model.Charge;

/**
 * bfb_wap 适用于手机网页支付，需要开通百度钱包手机网页支付。 发起支付请求需要额外的参数 bfb_login 和 result_url。
 * bfb_login 表明是否需要登录百度钱包来进行支付，值为 true 或者 false。 result_url 表示 Client
 * 支付完成的同步回调地址，该地址不必再加上自定义参数。 Ping++ 把这两个参数放在了 Charge 对象的 extra
 * 字段里。在发起交易请求的时候需要在 extra 里填写 bfb_login 和 result_url。
 * 
 * @author huxg
 * 
 */
public class BfbWapChannel extends AbstractChannel implements IChannel {
	public BfbWapChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();

		Map<String, String> extramap = new HashMap<String, String>();
		extramap.put("bfb_login", "true");
		extramap.put("result_url", SysConfiguration.getProperty("webgate.url") + "/cbtrigger/payment/success/" + paymentId);
		chargeParams.put("extra", extramap);
		return Charge.create(chargeParams);
	}
}
