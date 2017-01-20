package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Area;

public interface AreaDao extends BaseDao<Area, String> {

	/**
	 * 从数据库中读取地区
	 * @return
	 */
	public List<Area> getAreaList();
	
	/**
	 * 取得一个省下的全部市
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<Area> getCities(String provinceId);
}
