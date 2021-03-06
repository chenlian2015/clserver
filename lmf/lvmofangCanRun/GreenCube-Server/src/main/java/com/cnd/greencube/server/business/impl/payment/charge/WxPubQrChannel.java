package com.cnd.greencube.server.business.impl.payment.charge;

import com.pingplusplus.model.Charge;

/**
 * PC端：支付宝手机支付渠道
 * alipay_pc_direct 适用于 PC 网页支付，需要开通支付宝即时到账服务。发起 alipay_pc_direct 的支付请求需要额外参数success_url，该参数指的是支付成功后的 Client 同步通知处理地址，该 url 不要在页面文件后加上自定义参数，例如：
 * 错误的写法：http://www.alipay.com/alipay/return_url.php?xx=
 * 正确的写法：http://www.alipay.com/alipay/return_url.php
 * Ping++ 把这些参数放在了 Charge 对象的 extra 字段里，在发起交易请求的时候需要在 extra 里填写该参数。
 * @author huxg
 *
 */
public class WxPubQrChannel extends AbstractChannel  implements IChannel {
	public WxPubQrChannel (String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		throw new Exception("不支持的支付方式：微信公众号支付。尚未对该支付方式做过测试！");
	}
}
