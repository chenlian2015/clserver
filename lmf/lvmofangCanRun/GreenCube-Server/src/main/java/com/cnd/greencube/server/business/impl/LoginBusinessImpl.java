/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.business.LoginBusiness;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.encrypt.Encryption;
import com.thoughtworks.xstream.XStream;

/**
 * 登录服务实现类
 * 
 * @version 1.0
 */
@Service("LoginBusinessImpl")
public class LoginBusinessImpl extends BaseBusinessImpl<User, String> implements LoginBusiness {

	// 登录缓存
	public static final String LOGIN_USER_CACHE = "MAP_USER_CACHE1";

	@Resource(name = "jedisTemplate")
	protected JedisTemplate jedisTemplate;

	@Resource(name = "UserDaoImpl")
	protected UserDao userDao;

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User login(final String username, final String pwd, final String verifycode, boolean pwdEncrypted, String usertype) throws Exception {
		try {
			final String password = !pwdEncrypted ? Encryption.encodeMD5(pwd) : pwd;
			User user = (User) jedisTemplate.execute(new JedisAction() {
				@Override
				public Object action(Jedis jedis) {
					String id = jedis.hget(LOGIN_USER_CACHE, username + ":" + password);
					User user = null;
					if (!StringUtils.isEmpty(id)) {
						String xml = jedis.hget(RedisDaoSupportImpl.POOL + "User", id);
						if (!StringUtils.isEmpty(xml)) {
							try {
								XStream x = new XStream();
								user = (User) x.fromXML(xml);
							} catch (Exception e) {
							}
						}
					}

					return user;
				}
			});

			if (user == null)
				user = userDao.getUserByUserNameAndPassword(username, password);
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 退出登录接口
	 * 
	 * @param userid
	 *            -- 用户id
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void logout(String userid) {
		// 设置用户退出时间
		final User user = super.get(userid);

		if (user != null) {
			user.setLastLoginTime(null);
			super.update(user);

			jedisTemplate.execute(new JedisAction() {
				@Override
				public Object action(Jedis jedis) {
					XStream x = new XStream();
					String userxml = x.toXML(user);
					jedis.hset(RedisDaoSupportImpl.POOL + "User", user.getId(), userxml);
					return null;
				}
			});
		}
	}
}