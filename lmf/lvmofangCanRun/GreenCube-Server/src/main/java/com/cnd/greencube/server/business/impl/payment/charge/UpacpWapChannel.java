package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.config.SysConfiguration;
import com.pingplusplus.model.Charge;

/**
 * 手机网页：银联支付， 限 2015 年元旦后的银联新商户使用，需要开通银联全渠道手机网页支付。
 * 详情参考：https://www.pingxx.com/guidance/config
 * 
 * @author huxg
 * 
 */
public class UpacpWapChannel extends AbstractChannel implements IChannel {
	public UpacpWapChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();

		Map<String, String> extramap = new HashMap<String, String>();
		extramap.put("result_url", SysConfiguration.getProperty("webgate.url") + "/cbtrigger/payment/success/" + paymentId);
		chargeParams.put("extra", extramap);
		return Charge.create(chargeParams);
	}
}
