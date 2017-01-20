package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.Map;

import com.pingplusplus.model.Charge;

/**
 * App：微信手机端支付渠道
 * 
 * @author huxg
 * 
 */
public class WxChannel extends AbstractChannel implements IChannel {
	public WxChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();
		return Charge.create(chargeParams);
	}
}
