/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import com.cnd.greencube.server.business.NotifyBusiness;

/**
 * 通知服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("NotifyBusinessImpl")
public class NotifyBusinessImpl extends BaseBusinessImpl implements NotifyBusiness {

	@Override
	public String myletters(String userid) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
