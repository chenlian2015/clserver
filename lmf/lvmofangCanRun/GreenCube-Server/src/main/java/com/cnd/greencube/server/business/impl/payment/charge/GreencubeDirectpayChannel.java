package com.cnd.greencube.server.business.impl.payment.charge;

import com.cnd.greencube.server.business.impl.payment.MyCharge;
import com.cnd.greencube.server.util.StringUtils;
import com.pingplusplus.model.Charge;

/**
 * 绿魔方账户余额支付
 * 
 * @author huxg
 * 
 */
public class GreencubeDirectpayChannel extends AbstractChannel implements IChannel {
	public GreencubeDirectpayChannel(String indefier, String paymentId, int amoun, String clientIp, String subject, String body, String description) {
		super(indefier, paymentId, amoun, clientIp, subject, body, description);
	}

	@Override
	public Charge createCharge() throws Exception {
		Charge c = new MyCharge();

		c.setOrderNo(this.paymentId);
		c.setAmount(this.amount);
		c.setChannel("greencube_directpay");
		c.setClientIp(StringUtils.isEmptyAfterTrim(this.clientIp) ? null : this.clientIp);

		// 商品标题，最长32个Unicode字符
		c.setSubject(org.apache.commons.lang.StringUtils.abbreviate(this.subject.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 32));

		// 商品描述信息，最长为128个字符
		c.setBody(org.apache.commons.lang.StringUtils.abbreviate(this.body.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 128));

		// 订单附件说明，最多255个字符
		c.setDescription(StringUtils.isEmptyAfterTrim(this.description) ? null : this.description);
		return c;
	}
}
