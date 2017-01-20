package com.cnd.greencube.web.base.api;

import com.cnd.greencube.web.base.dao.jdbc.JdbcDAO;
import com.cnd.greencube.web.base.util.SpringUtils;

public class JdbcAPI {
	public static JdbcDAO getJdbc() {
		JdbcDAO jdbc = (JdbcDAO) SpringUtils.getBean("jdbcDao");
		return jdbc;
	}
}
