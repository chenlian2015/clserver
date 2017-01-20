/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Area;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public interface AreaBusiness {

	/**
	 * 删除地区
	 */
	boolean deleteArea(String id);

	/**
	 * 更新地区
	 */
	boolean saveArea(Area area);

	/**
	 * 更新地区
	 */
	boolean updateArea(Area area);

	/**
	 * 根据ID取得地区对象
	 */
	Area getAreaById(String areaId);

	/**
	 * 取得全部地区
	 */
	String getAllAreasFromCache();

	List<Area> getAllCities();
}
