/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.LoginBusiness;
import com.cnd.greencube.server.business.UserBusiness;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.LoginService;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 登录服务实现类
 * 
 * @version 1.0
 */
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService.Iface {
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Resource(name = "LoginBusinessImpl")
	protected LoginBusiness loginBusiness;

	@Resource(name = "UserBusinessImpl")
	protected UserBusiness userBusiness;

	@Resource(name = "jedisTemplate")
	protected JedisTemplate jedisTemplate;

	/**
	 * 登录接口
	 * 
	 * @param username
	 *            -- 用户名
	 * @param pwd
	 *            -- 密码
	 * @param verifycode
	 *            -- 验证码
	 * @param pwdEncrypted
	 *            -- 是否已经对密码做了MD5加密，如果名为密码则传递false,如果是已加密的密码则传递true
	 * @param usertype
	 *            -- 用户类型
	 */
	@Override
	public String login(String username, String pwd, String verifycode, boolean pwdEncrypted, String usertype) {
		try {
			if (StringUtils.isEmptyAfterTrim(username))
				return Message.errorMessage("账号不能为空，请重试");

			if (StringUtils.isEmptyAfterTrim(pwd))
				return Message.errorMessage("密码不能为空，请重试");

			User user = loginBusiness.login(username, pwd, verifycode, pwdEncrypted, usertype);
			if (user == null)
				return Message.errorMessage("用户名或密码有误，请重试");

			// 此处返回用户的登录token

			return Message.okMessage(user);
		} catch (Exception e) {
			logger.error(e);
			return Message.errorMessage("用户名或密码有误，请重试");
		}
	}

	/**
	 * 维护App心跳
	 */
	@Override
	public String appHeartBeat(String token, String imei, String __remoteAddr) throws TException {
		try {
			String userid = TokenUtils.isTokenRight(token);
			if (!StringUtils.isEmpty(userid)) {
				User user = userBusiness.getUserById(userid);
				String newToken = TokenUtils.recreateToken(token, imei, __remoteAddr);
				user.setId(newToken);
				return Message.okMessage(user);
			}
			return Message.error();
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * App端登录
	 */
	@Override
	public String applogin(String imei, String username, String pwd, String verifycode, boolean pwdEncrypted, String __remoteAddr) throws TException {
		try {
			if (StringUtils.isEmptyAfterTrim(username))
				return Message.errorMessage("账号不能为空，请重试");

			if (StringUtils.isEmptyAfterTrim(pwd))
				return Message.errorMessage("密码不能为空，请重试");

			final User user = loginBusiness.login(username, pwd, verifycode, pwdEncrypted, null);
			if (user == null)
				return Message.errorMessage("用户名或密码有误，请重试");

			final String current = String.valueOf(System.currentTimeMillis());

			final String token = TokenUtils.generateLoginToken(imei, __remoteAddr, current, user.getId());

			user.setId(token);
			return Message.okMessage(user);
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 退出登录接口
	 * 
	 * @param userid
	 *            -- 用户id
	 */
	@Override
	public String logout(String userid) {
		try {
			loginBusiness.logout(userid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}