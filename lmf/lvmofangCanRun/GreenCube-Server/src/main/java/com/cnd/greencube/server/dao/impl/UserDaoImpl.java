package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 用户Dao
 * 
 * @author huxg
 * 
 */
@Repository("UserDaoImpl")
public class UserDaoImpl extends RedisDaoSupportImpl<User, String> implements UserDao {
	public static String CACHE_MAP_USER_CACHE1 = "MAP_USER_CACHE1";

	/**
	 * 判断用户账号是否存在
	 * 
	 * @return
	 */
	public boolean isExistUserName(String loginname) {
		String sql = "select count(*) from User where loginName =  ? ";
		return super.queryCount(sql, new Object[] { loginname }) > 0;
	}

	/**
	 * 通过账号密码查询用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByUserNameAndPassword(final String username, final String password) {
		String sql = "select id from User t where loginName = ? and password = ?";
		List<User> users = (List<User>) super.findList(sql, new Object[] { username, password });
		if (users == null || users.isEmpty())
			return null;
		final User user = users.get(0);

		return user;
	}

	/***
	 * 关键字查询id
	 * 
	 * @param name
	 * @return
	 */
	public List<User> getCNameAndCNc(String name) {
		String ql = "select id from User where name like ? or nickname like ?";
		Object[] objs = null;
		if (!StringUtils.isEmptyAfterTrim(name)) {
			objs = new Object[] { "%" + name.trim() + "%", "%" + name.trim() + "%" };
		}
		return (List<User>) super.findList(ql, objs);
	}

	@Override
	public void updateUserLoginCache(final String username, final String password, final String userid) {
		jedisTemplate.execute(new JedisAction() {
			@Override
			public Object action(Jedis jedis) {
				jedis.hset(CACHE_MAP_USER_CACHE1, username + ":" + password, userid);
				return null;
			}
		});
	}
}
