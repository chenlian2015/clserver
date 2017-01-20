/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.RegistBusiness;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.RegistService;
/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public class RegistServiceImpl implements RegistService.Iface {
	private static final Logger logger = Logger.getLogger(RegistServiceImpl.class);
	@Resource(name = "RegistBusinessImpl")
	protected RegistBusiness registBusiness;

	@Override
	public String appRegist(String mobile, String verifyCode, String imei, String password) throws TException {
		try {
			User user = registBusiness.appRegist(mobile, verifyCode, imei, password);
			return Message.okMessage(user);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String webRegist(String arg0, String arg1, String arg2, String arg3) throws TException {
		try {
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
}
