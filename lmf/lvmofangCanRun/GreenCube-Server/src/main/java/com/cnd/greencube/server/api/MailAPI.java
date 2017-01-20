package com.cnd.greencube.server.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnd.greencube.server.config.SysConfiguration;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.mail.MailSenderInfo;
import com.cnd.greencube.server.util.mail.SimpleMailSender;

/**
 * 邮件发送API类
 * 
 * @author apple
 * 
 */
public class MailAPI {
	private static final Log logger = LogFactory.getLog(MailAPI.class);

	public static String sendMail(String targetMailAddress, String title, String content, boolean isHtml) {
		String strEnable = SysConfiguration.getInstance().getProperty("mailserver.enable");
		boolean enableMailServer = StringUtils.isEmpty(strEnable) ? false : Boolean.parseBoolean(strEnable);
		if (!enableMailServer)
			return Message.okMessage();

		if (StringUtils.isEmpty(targetMailAddress)) {
			if (logger.isDebugEnabled())
				logger.debug("企图向" + targetMailAddress + "发送邮件标题为空的邮件，已被过滤。");
			return Message.errorMessage("企图向" + targetMailAddress + "发送邮件标题为空的邮件，已被过滤。");
		}

		if (StringUtils.isEmpty(content)) {
			if (logger.isDebugEnabled())
				logger.debug("企图向" + targetMailAddress + "发送邮件内容为空的邮件，已被过滤。");

			return Message.errorMessage("企图向" + targetMailAddress + "发送邮件内容为空的邮件，已被过滤。");
		}

		try {
			String port = SysConfiguration.getProperty("mailserver.port");
			String host = SysConfiguration.getProperty("mailserver.host");
			String user = SysConfiguration.getProperty("mailserver.user");
			String from = SysConfiguration.getProperty("mailserver.from");
			String pwd = SysConfiguration.getProperty("mailserver.pwd");
			String validate = SysConfiguration.getProperty("mailserver.validate");

			// 设置邮件服务器信息
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(host);
			mailInfo.setMailServerPort(port);
			try {
				mailInfo.setValidate(Boolean.parseBoolean(validate));
			} catch (Exception e) {
			}
			mailInfo.setUserName(user);
			mailInfo.setPassword(pwd);
			mailInfo.setFromAddress(from);
			mailInfo.setToAddress(targetMailAddress);
			mailInfo.setSubject(title);
			mailInfo.setContent(content);

			// 发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			if (isHtml)
				SimpleMailSender.sendHtmlMail(mailInfo);
			else
				sms.sendMailNews(mailInfo);

			if (logger.isDebugEnabled()) {
				logger.debug("向" + targetMailAddress + "发送了一封邮件：" + title);
			}

			return Message.errorMessage("信息不完整，邮件发送失败");
		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("向" + targetMailAddress + "发送一封邮件时系统出错", e);

			return Message.errorMessage("向" + targetMailAddress + "发送一封邮件时系统出错：" + e.getMessage());
		}
	}

}
