/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.business.UserBusiness;
import com.cnd.greencube.server.cache.CACHE_AREA;
import com.cnd.greencube.server.dao.FiMemberFeeDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.encrypt.Encryption;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("UserBusinessImpl")
public class UserBusinessImpl extends BaseBusinessImpl implements UserBusiness {
	@Resource(name = "UserDaoImpl")
	private UserDao userDao;

	@Resource(name = "CACHE_AREA")
	private CACHE_AREA cacheArea;

	@Resource(name = "jedisTemplate")
	private JedisTemplate jedisTemplate;

	@Resource(name = "FiMemberFeeDaoImpl")
	FiMemberFeeDao memberFeeDao;

	@Transactional
	@Override
	public void bindMobile(String userid, String mobile) {
		if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(mobile))
			throw new NotifyException("Error001 绑定手机号码失败");
		User user = userDao.get(userid);
		if (user != null) {
			if (!mobile.equals(user.getMobile())) {
				user.setMobile(mobile);
				userDao.update(user);
			}
		}
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "select t.id from User t where t.email = ?";
		List<User> users = userDao.findList(sql, new Object[] { email });
		User user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User getUserByMobile(String mobile) {
		String sql = "select t.id from User t where t.mobile = ?";
		List<User> users = userDao.findList(sql, new Object[] { mobile });
		User user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User getUserByUserName(String username) {
		String sql = "select t.id from User t where loginName =  ? ";
		List<User> users = userDao.findList(sql, new Object[] { username });
		User user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User getUserById(String userid) {
		User user = userDao.get(userid);
		return user;
	}

	@Transactional
	@Override
	public void updatePwd(String userid, String oldPassword, String newPassword) {
		User user = userDao.get(userid);
		if (user.getPassword() != null && user.getPassword().equals(oldPassword)) {
			user.setPassword(Encryption.encodeMD5(newPassword));
		}
		userDao.update(user);
		userDao.updateUserLoginCache(user.getLoginName(), user.getPassword(), user.getId());
	}

	@Transactional
	@Override
	public void forgetPwd(String phone, String newPassword) throws Exception {
		User user = getUserByMobile(phone);
		if (user == null)
			throw new Exception("手机号码有误，请重试！");
		user.setPassword(Encryption.encodeMD5(newPassword));
		userDao.update(user);
	}

	@Transactional
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Transactional
	@Override
	public void updateUserLastLoginTime(String userid) {
		User user = userDao.get(userid);
		user.setLastLoginTime(new Date());
		userDao.update(user);
	}

	/**
	 * 分页获取注册用户列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 注册用户json数组
	 */
	@Override
	public List<User> getUsersForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from User t";
		String ctql = "select count(t) from User t";
		return userDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public User addUser(User user) {
		userDao.save(user);
		return user;
	}

}
