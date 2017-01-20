/*
 * Copyright 2005-2020 Top Team All rights reserved.
 * Support: 
 * License: top team license
 */
package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Area;

/**
 * Dao - 货品
 * 
 * @author Top Team（ ）
 * @version 1.0
 */
@Repository("AreaDaoImpl")
public class AreaDaoImpl extends RedisDaoSupportImpl<Area, String> implements AreaDao {

	/**
	 * 取得地区列表
	 * 
	 * @return
	 */
	public List<Area> getAreaList() {
		String sql = "select t.id from Area t order by t.classCode, t.id";
		return super.findList(sql);
	}

	/**
	 * 取得一个省下的所有市
	 */
	public List<Area> getCities(String provinceId) {
		String sql = "select t.id from Area t where t.CClassCode = ?";
		return super.findList(sql, new Object[] { provinceId });
	}
}