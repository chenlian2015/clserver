/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public interface UserBusiness {
	/**
	 * 绑定手机号码
	 * 
	 * @param userid
	 * @param mobile
	 */
	void bindMobile(String userid, String mobile);

	/**
	 * 根据Email取得用户
	 * 
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);

	/**
	 * 根据手机号码取得用户
	 * 
	 * @param mobile
	 * @return
	 */
	User getUserByMobile(String mobile);

	/**
	 * 根据用户名取得用户
	 * 
	 * @param username
	 * @return
	 */
	User getUserByUserName(String username);

	/**
	 * 根据id取得用户
	 * 
	 * @param userid
	 * @return
	 */
	User getUserById(String userid);

	/**
	 * 更新用户密码
	 * 
	 * @param userid
	 * @param oldPassword
	 * @param newPassword
	 */
	void updatePwd(String userid, String oldPassword, String newPassword);

	/**
	 * 忘记密码
	 * 
	 * @param userid
	 * @param phone
	 * @param verifyCode
	 * @param newPassword
	 */

	void forgetPwd(String phone, String newPassword) throws Exception;

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 更新用户最后一次登录时间
	 * 
	 * @param userid
	 */
	void updateUserLastLoginTime(String userid);

	/**
	 * 分页获取注册用户列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 注册用户json数组
	 */
	List<User> getUsersForPagelit(PageInfo pageInfo);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	User addUser(User user);

}
