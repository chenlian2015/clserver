package com.cnd.greencube.server.business.impl.payment.charge;

import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.config.SysConfiguration;
import com.cnd.greencube.server.util.StringUtils;
import com.pingplusplus.Pingpp;

public abstract class AbstractChannel {
	static final String url = SysConfiguration.getProperty("payment.pingpp.url");
	static final String appId = SysConfiguration.getProperty("payment.pingpp.app.id");
	static final String apiKey = SysConfiguration.getProperty("payment.pingpp.api.key");

	String paymentId;
	int amount;
	String clientIp;
	String subject;
	String body;
	String description;
	String indefier;

	public AbstractChannel(String indefier, String paymentId, int amount, String clientIp, String subject, String body, String description) {
		this.indefier = indefier;
		this.paymentId = paymentId;
		this.amount = amount * 100;
		this.clientIp = clientIp;
		this.subject = subject;
		this.body = body;
		this.description = description;
	}

	Map<String, Object> init() {
		// 支付提交至服务器
		Pingpp.apiKey = apiKey;

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("app", app);

		// 货币代码，目前pin++仅支持人民币，所以是cny
		chargeParams.put("currency", "cny");

		// 订单号
		chargeParams.put("order_no", this.paymentId);

		// 订单总金额，单位为分
		chargeParams.put("amount", this.amount);

		// 渠道
		chargeParams.put("channel", indefier);

		// 发起支付请求的终端IP地址，格式为IPV4，如：127.0.0.1
		chargeParams.put("client_ip", StringUtils.isEmptyAfterTrim(this.clientIp) ? null : this.clientIp);

		// 商品标题，最长32个Unicode字符
		chargeParams.put("subject", org.apache.commons.lang.StringUtils.abbreviate(this.subject.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 32));

		// 商品描述信息，最长为128个字符
		chargeParams.put("body", org.apache.commons.lang.StringUtils.abbreviate(this.body.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 128));

		// 订单附件说明，最多255个字符
		chargeParams.put("description", StringUtils.isEmptyAfterTrim(this.description) ? null : this.description);
		return chargeParams;
	}
}
