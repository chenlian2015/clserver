package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.User;

public interface UserDao extends BaseDao<User, String> {

	/**
	 * 判断用户账号是否存在
	 * 
	 * @return
	 */
	public boolean isExistUserName(String loginname);

	/**
	 * 通过账号密码查询用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByUserNameAndPassword(final String username, final String password);


	/***
	 * 关键字查询id
	 * 
	 * @param name
	 * @return
	 */
	public List<User> getCNameAndCNc(String name);
	
	
	/**
	 * 更新用户登录缓存
	 * @param username
	 * @param password
	 * @param userid
	 */
	public void updateUserLoginCache(final String username, final String password, final String userid);
	
}
