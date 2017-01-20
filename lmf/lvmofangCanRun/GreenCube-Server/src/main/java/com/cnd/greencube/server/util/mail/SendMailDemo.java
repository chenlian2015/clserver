/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.util.mail;

public class SendMailDemo {
	public void send(String email, String econtent,String title) {
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		// 邮箱用户名
		mailInfo.setUserName("1498653869@qq.com");
		// 邮箱密码
		mailInfo.setPassword("WPL24643789!@");
		// 发件人邮箱
		mailInfo.setFromAddress("1498653869@qq.com");
		// 收件人邮箱
		mailInfo.setToAddress(email);
		// 邮件标题
		mailInfo.setSubject(title);
		// 邮件内容
		mailInfo.setContent(econtent);
		// 发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// 发送文体格式
		sms.sendTextMail(mailInfo);
		// 发送html格式
		SimpleMailSender.sendHtmlMail(mailInfo);
	}


	public String judgeEmail(String email) {
		String etype = email.trim().substring(email.indexOf("@") + 1, email.indexOf("."));
		String url = null;
		if ("163".equals(etype)) {
			url = "http://mail.163.com/";
		} else if ("126".equals(etype)) {
			url = "http://mail.126.com/";
		} else if ("qq".equals(etype)) {
			url = "http://mail.qq.com/";
		} else if ("yahoo".equals(etype)) {
			url = "https://cn.overview.mail.yahoo.com/";
		} else if ("sina".equals(etype)) {
			url = "http://mail.sina.com.cn/";
		} else if ("sohu".equals(etype)) {
			url = "http://mail.sohu.com/";
		}
		return url;
	}
}
