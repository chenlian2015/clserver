package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.Map;

import com.pingplusplus.model.Charge;

/**
 * applepay_upacp 即 Apple Pay ，适用于 App 支付，需要开通银联手机支付。Apple Pay 接入流程和银联手机支付类似，具体可参考 Server-SDK 接入指南。
 * @author huxg
 *
 */
public class ApplepayUpacpChannel extends AbstractChannel implements IChannel {
	public ApplepayUpacpChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();
		return Charge.create(chargeParams);
	}
}
