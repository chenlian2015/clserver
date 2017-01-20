/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.api.MailAPI;
import com.cnd.greencube.server.business.RegistBusiness;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.encrypt.Encryption;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("RegistBusinessImpl")
public class RegistBusinessImpl extends BaseBusinessImpl implements RegistBusiness {
	@Resource(name = "UserDaoImpl")
	private UserDao userDao;

	@Resource(name = "VirtualAccountDaoImpl")
	private VirtualAccountDao virtualAccountDao;

	@Transactional
	@Override
	public User appRegist(String mobile, String verifyCode, String imei, String password) {
		return registUser(mobile, mobile, password, null, mobile);
	}

	@Transactional
	@Override
	public User webRegist(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 创建用户
	 * 
	 * @param loginName
	 * @param nickName
	 * @param password
	 * @param email
	 * @param mobile
	 * @return
	 */
	User registUser(String loginName, String nickName, String password, String email, String mobile) {
		User user = new User();
		user.setLoginName(loginName);
		user.setMobile(mobile);
		user.setEmail(email);

		String name = StringUtils.isEmpty(nickName) ? loginName : nickName;
		user.setName(name);
		user.setNickname(name);

		try {
			user.setPassword(Encryption.encodeMD5(password));
		} catch (Exception e) {
			user.setPassword("25d55ad283aa400af464c76d713c07ad");
		}
		userDao.save(user);

		// 生成虚拟账户
		VirtualAccount va = new VirtualAccount();
		va.setUserId(user.getId());
		va.setBalance(0);
		va.setCbitBalance(0);
		virtualAccountDao.save(va);
		userDao.updateUserLoginCache(user.getLoginName(), user.getPassword(), user.getId());

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】恭喜，注册成功";
			String content = "您已成为“绿魔方”健康生活平台会员，您的账号是：" + loginName + "，密码：" + password + "，请牢记！";

			if (!StringUtils.isEmpty(email)) {
				MailAPI.sendMail(user.getEmail(), title, content, false);
			}

			if (!StringUtils.isEmpty(mobile)) {
				// 发送短信通知
				ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
				sms.sendMessage(user.getMobile(), content);
			}
		} catch (Exception e) {

		}

		return user;
	}
}
