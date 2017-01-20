package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.Map;

import com.pingplusplus.model.Charge;

/**
 * bfb 适用于 App 支付，需开通百度钱包移动快捷支付服务。
 * @author huxg
 *
 */
public class BfbChannel extends AbstractChannel implements IChannel {
	public BfbChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();
		return Charge.create(chargeParams);
	}
}
