package com.cnd.greencube.server.plugin.sms.manyitong;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.cnd.greencube.server.config.SysConfiguration;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.StringUtils;

public class ManyitongSMSProvider implements ISMSProvider {
	String serviceUrl = "http://test2.ndchina.cn/api/bs3webservice.asmx";
	String soaActionUrl = "http://tempuri.org/SendSMS";
	String user = "greencube";
	String password = "123456";
	String channelName = "【民生恒江】";

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getSoaActionUrl() {
		return soaActionUrl;
	}

	public void setSoaActionUrl(String soaActionUrl) {
		this.soaActionUrl = soaActionUrl;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Override
	public boolean sendMessage(String mobile, String message) {
		String enabledSms = SysConfiguration.getInstance().getProperty("sms.interface.enable");
		boolean smsEnabled = StringUtils.isEmpty(enabledSms) ? false : Boolean.parseBoolean(enabledSms);
		if (true == smsEnabled) {
			SendSMSMessageWork work = new SendSMSMessageWork(mobile, message);
			Thread t = new Thread(work);
			t.start();
		}
		return true;
	}

	public static void main(String[] args) {
		ManyitongSMSProvider p = new ManyitongSMSProvider();
		p.sendMessage("13720003175", "test2");
	}

	class SendSMSMessageWork implements Runnable {
		private String mobile;
		private String message;

		public SendSMSMessageWork(String mobile, String message) {
			this.mobile = mobile;
			this.message = message;
		}

		public void run() {
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost httpPost = null;
			try {
				httpPost = new HttpPost(serviceUrl);
				String xml = makeXml(mobile, message);
				StringEntity stringEntity = new StringEntity(xml, "utf-8");
				httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
				httpPost.addHeader("SOAPAction", soaActionUrl);
				httpPost.setEntity(stringEntity);

				HttpResponse response = httpclient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String returnVal = EntityUtils.toString(entity);

				if (!StringUtils.isEmpty(returnVal)) {
					int begin = returnVal.indexOf("Status");
					begin += 6;

					int end = returnVal.indexOf("/Status");

					String status = returnVal.substring(begin, end);

					status = status.replaceAll("&gt;", "");
					status = status.replaceAll("&lt;", "");
					System.out.println("短信发送状态：" + status);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (httpPost != null)
					httpPost.releaseConnection();
			}
		}

		String makeXml(String mobile, String message) {
			return "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><SendSMS xmlns=\"http://tempuri.org/\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><UserName>"
					+ user
					+ "</UserName><UserPsw>"
					+ password
					+ "</UserPsw><AccountType>2</AccountType><AccountInfo>恒江</AccountInfo><SchTime/><SMSMobile>"
					+ mobile + "</SMSMobile><SMSContent>" + channelName + message + "</SMSContent></SendSMS></s:Body></s:Envelope>";
		}
	}
}
