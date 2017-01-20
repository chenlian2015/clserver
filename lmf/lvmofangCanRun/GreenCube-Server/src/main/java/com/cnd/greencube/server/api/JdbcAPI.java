package com.cnd.greencube.server.api;

import com.cnd.greencube.server.dao.jdbc.JdbcDAO;
import com.cnd.greencube.server.util.SpringUtils;

public class JdbcAPI {
	public static JdbcDAO getJdbc() {
		JdbcDAO jdbc = (JdbcDAO) SpringUtils.getBean("jdbcDao");
		return jdbc;
	}
}
