/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.interceptor;

import org.hibernate.EmptyInterceptor;

public class Test extends EmptyInterceptor{
	@Override
	public String onPrepareStatement(String sql) {
		//System.out.println(sql); //TODO 1
		return super.onPrepareStatement(sql);
	}
}
