package com.cnd.greencube.web.base.filter.parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ForwardInterceptor {
	Object pack(String key, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	void forward(String key, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
