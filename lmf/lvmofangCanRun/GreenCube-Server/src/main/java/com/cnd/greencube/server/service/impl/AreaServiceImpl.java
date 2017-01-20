/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cnd.greencube.server.business.AreaBusiness;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.AreaService;
import com.cnd.greencube.server.util.JsonUtils;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
public class AreaServiceImpl extends AbstractServiceImpl implements AreaService.Iface {
	private static final Logger logger = Logger.getLogger(AreaServiceImpl.class);

	@Resource(name = "AreaBusinessImpl")
	private AreaBusiness areaBusiness;

	/**
	 * 删除地区
	 */
	@Override
	public String deleteArea(String id) throws TException {
		try {
			areaBusiness.deleteArea(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新地区
	 */
	@Override
	public String updateArea(String areaInfo) throws TException {
		try {
			Area area = JsonUtils.String2Bean(areaInfo, Area.class);
			areaBusiness.updateArea(area);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据ID取得地区对象
	 */
	@Override
	public String getAreaById(String areaId) throws TException {
		try {
			Area area = areaBusiness.getAreaById(areaId);
			return JsonUtils.bean2String(area);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得全部地区
	 */
	@Override
	public String getAllAreas() throws TException {
		try {
			String str = areaBusiness.getAllAreasFromCache();
			JSONArray ja = JSON.parseArray(str);
			Message message = new Message();
			message.setData(ja);
			message.setSuccess(true);
			return message.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return Message.error();
		}
	}
	
	/**
	 * 取得全部地区
	 */
	@Override
	public String getAllCities() throws TException {
		try {
			List<Area> areas = areaBusiness.getAllCities();
			return Message.okMessage(areas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return Message.error();
		}
	}
}
