package com.cnd.greencube.web.base.plugin.sms.rest;

import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.plugin.sms.ISMSProvider;
import com.cnd.greencube.web.base.util.HttpUtils;
import com.cnd.greencube.web.base.util.StringUtils;

/**
 * Restful版本的实现
 * 
 * @author huxg
 * 
 */
public class RestfulSMSProvider implements ISMSProvider {
	String serviceUrl = "http://test2.ndchina.cn/api/bs3webservice.asmx";
	String user = "greencube";
	String password = "123456";

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean sendMessage(String mobile, String message) {
		String enabledSms = SysConfiguration.getInstance().getProperty("sms.interface.enable");
		boolean smsEnabled = StringUtils.isEmpty(enabledSms) ? false : Boolean.parseBoolean(enabledSms);
		if (smsEnabled) {
			try {
				message = URLEncoder.encode(message, "GBK");
				String params = "?username=" + user + "&password=" + password + "&mobile=" + mobile + "&message=" + message;
				String zt = HttpUtils.get4String(serviceUrl + params, null);
				if (!StringUtils.isEmpty(zt)) {
					int i = Integer.parseInt(zt);
					if (i < 0) {
						return false;
					} else {
						return true;
					}
				}
				return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
