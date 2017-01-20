package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.Map;

import com.pingplusplus.model.Charge;

/**
 * upacp 适用于 App 支付，限 2015 年元旦后的银联新商户使用。需要开通银联全渠道支付服务。
 * 安卓平台需要到 http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin 下载银联支付的插件，iOS 不需要下载银联安全支付 App。
 * 
 * @author huxg
 * 
 */
public class UpacpChannel extends AbstractChannel implements IChannel {
	public UpacpChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Map<String, Object> chargeParams = super.init();
		return Charge.create(chargeParams);
	}
}
