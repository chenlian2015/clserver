/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import com.cnd.greencube.server.entity.User;

/**
 * 登录服务实现类
 * 
 * @version 1.0
 */
public interface LoginBusiness {
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
	User login(final String username, final String pwd, final String verifycode, boolean pwdEncrypted, String usertype) throws Exception ;

	/**
	 * 退出登录接口
	 * 
	 * @param userid
	 *            -- 用户id
	 */
	void logout(String userid);
}