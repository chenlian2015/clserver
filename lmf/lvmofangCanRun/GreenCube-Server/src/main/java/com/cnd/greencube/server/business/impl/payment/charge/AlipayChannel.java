package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.Map;

import com.pingplusplus.model.Charge;

/**
 * App：支付宝手机支付渠道
 * 
 * @author huxg
 * 
 */
public class AlipayChannel extends AbstractChannel implements IChannel {
	public AlipayChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();
		return Charge.create(chargeParams);
	}
}
