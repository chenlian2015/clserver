/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import com.cnd.greencube.server.entity.User;


/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public interface RegistBusiness {
	/**
	 * App注册
	 * 
	 * @param mobile
	 *            -- 手机号码
	 * @param verifyCode
	 *            -- 验证码
	 * @param imei
	 *            -- 手机串号
	 * @param password
	 *            -- 密码
	 * @return 成功与否
	 */
	User appRegist(String mobile, String verifyCode, String imei, String password);
	
	/**
	 * Web的注册
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	User webRegist(String usernmae, String password, String nickname, String arg3);

}
