package com.cnd.greencube.server.business.impl.payment.charge;

import com.pingplusplus.model.Charge;

/**
 * 手机网页：微信公众号支付
 * 是微信公众账号支付，只能用于微信内置浏览器内，而且只有服务号才能申请该支付功能。
 * 而发起 wx_pub 支付，首先需要在微信公众平台进行相关的开发配置，然后需要获取到 open_id 用于发起交易。
 * 详情参考：https://www.pingxx.com/guidance/config
 * @author huxg
 *
 */
public class WxPubChannel extends AbstractChannel implements IChannel {
	public WxPubChannel (String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		throw new Exception("不支持的支付方式：微信公众号支付。尚未对该支付方式做过测试！");
	}
}
