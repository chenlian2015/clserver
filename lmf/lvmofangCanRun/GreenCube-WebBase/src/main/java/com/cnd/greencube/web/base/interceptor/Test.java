package com.cnd.greencube.web.base.interceptor;

import org.hibernate.EmptyInterceptor;

public class Test extends EmptyInterceptor{
	@Override
	public String onPrepareStatement(String sql) {
		//System.out.println(sql); //TODO 1
		return super.onPrepareStatement(sql);
	}
}
