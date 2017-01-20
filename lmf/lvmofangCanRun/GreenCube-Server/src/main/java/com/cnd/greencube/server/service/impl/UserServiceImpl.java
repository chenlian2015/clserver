/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.UserBusiness;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.UserService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public class UserServiceImpl extends AbstractServiceImpl implements UserService.Iface {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Resource(name = "UserBusinessImpl")
	protected UserBusiness userBusiness;

	@Override
	public String bindMobile(String userid, String mobile) throws TException {
		try {
			userBusiness.bindMobile(userid, mobile);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getUserByEmail(String email) throws TException {
		try {
			User u = userBusiness.getUserByEmail(email);
			return Message.okMessage(u);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getUserByUserName(String username) throws TException {
		try {
			User u = userBusiness.getUserByUserName(username);
			return Message.okMessage(u);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getUserById(String userid) throws TException {
		try {
			User u = userBusiness.getUserById(userid);
			return Message.okMessage(u);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getUserByMobile(String mobile) throws TException {
		try {
			User u = userBusiness.getUserByMobile(mobile);
			return Message.okMessage(u);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updatePwd(String userid, String oldPassword, String newPassword) throws TException {
		try {
			userBusiness.updatePwd(userid, oldPassword, newPassword);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 忘记密码
	 * 
	 * @param userid
	 * @param phone
	 * @param verifyCode
	 * @param newPassword
	 */
	@Override
	public String forgetPwd(String phone, String newPassword) {
		try {
			userBusiness.forgetPwd(phone, newPassword);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.errorMessage(e.getMessage());
		}
	}

	@Override
	public String updateUser(String jsonUser) throws TException {
		try {
			User u = JsonUtils.String2Bean(jsonUser, User.class);
			userBusiness.updateUser(u);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateUserLastLoginTime(String userid) throws TException {
		try {
			userBusiness.updateUserLastLoginTime(userid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页获取注册用户列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 注册用户json数组
	 */
	@Override
	public String getUsersForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<User> users = userBusiness.getUsersForPagelit(pageInfo);
			return Message.okMessage(users, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String addUser(String userJson) throws TException {
		User user = JsonUtils.String2Bean(userJson, User.class);
	   user = userBusiness.addUser(user);
	   String id = user.getId();
		return id;
	}
}
