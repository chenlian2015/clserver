/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.AreaBusiness;
import com.cnd.greencube.server.cache.CACHE_AREA;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.entity.Area;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("AreaBusinessImpl")
public class AreaBusinessImpl extends BaseBusinessImpl implements AreaBusiness {
	@Resource(name = "AreaDaoImpl")
	private AreaDao areaDao;

	@Resource(name = "CACHE_AREA")
	private CACHE_AREA cacheArea;

	@Resource(name = "jedisTemplate")
	private JedisTemplate jedisTemplate;

	/**
	 * 删除地区
	 */
	@Transactional
	@Override
	public boolean deleteArea(String id) {
		areaDao.delete(id);
		return true;
	}

	/**
	 * 更新地区
	 */
	@Transactional
	@Override
	public boolean saveArea(Area area) {
		area.setId(null);
		areaDao.save(area);
		return true;
	}

	/**
	 * 更新地区
	 */
	@Transactional
	@Override
	public boolean updateArea(Area area) {
		areaDao.update(area);
		return true;
	}

	/**
	 * 根据ID取得地区对象
	 */
	@Transactional(readOnly = true)
	@Override
	public Area getAreaById(String areaId) {
		return (Area) areaDao.get(areaId);
	}

	/**
	 * 取得全部地区
	 */
	@Override
	public String getAllAreasFromCache() {
		return cacheArea.getAreaCache();
	}

	@Override
	public List<Area> getAllCities() {
		String sql = "select t.id from Area t where t.type = 1 order by firstChar asc ";
		List<Area> areas = areaDao.findList(sql);
		return areas;
	}
}
