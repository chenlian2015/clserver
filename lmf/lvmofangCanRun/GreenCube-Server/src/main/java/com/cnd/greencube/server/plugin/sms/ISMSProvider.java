package com.cnd.greencube.server.plugin.sms;

/**
 * 短信服务者
 * @author huxg
 *
 */
public interface ISMSProvider {
	boolean sendMessage(String mobile, String message);
}
