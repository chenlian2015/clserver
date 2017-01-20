/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.thrift.TException;

import com.cnd.greencube.server.business.NotifyBusiness;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.NotifyService;
/**
 * 通知服务实现类
 * 
 * @author huxg
 * 
 */
public class NotifyServiceImpl implements NotifyService.Iface {
	@Resource(name = "NotifyBusinessImpl")
	protected NotifyBusiness notifyBusiness;

	@Override
	public String myletters(String userid) throws TException {
		try {
			notifyBusiness.myletters(userid);
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

}
