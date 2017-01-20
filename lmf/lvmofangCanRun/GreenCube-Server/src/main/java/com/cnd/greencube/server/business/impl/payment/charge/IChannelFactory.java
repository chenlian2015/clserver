package com.cnd.greencube.server.business.impl.payment.charge;

public interface IChannelFactory {
	IChannel createChargeChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description)
			throws Exception;
}
