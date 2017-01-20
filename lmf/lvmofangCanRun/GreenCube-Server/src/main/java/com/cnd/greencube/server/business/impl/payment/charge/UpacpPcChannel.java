package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.config.SysConfiguration;
import com.pingplusplus.model.Charge;

/**
 * PC端：银联支付 upacp_pc 适用于 PC 网页支付，需要开通银联网关支付服务，发起支付请求，需要额外的参数 result_url ， Ping++
 * 把该参数放在 Charge 对象的 extra 字段里，需要在发起支付请求的时候在 extra 字段里填写 result_url。 upacp_pc 对
 * result_url 格式没有特殊要求，付款完成后 PC 客户端跳转到 result_url， 渠道会以 POST 方式发送订单信息给
 * result_url，其中 orderId对应 Charge 对象里的 order_no。
 * 
 * @author huxg
 * 
 */
public class UpacpPcChannel extends AbstractChannel implements IChannel {
	public UpacpPcChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
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
