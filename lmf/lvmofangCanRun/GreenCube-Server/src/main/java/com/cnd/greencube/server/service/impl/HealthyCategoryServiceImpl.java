/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSONArray;
import com.cnd.greencube.server.business.HealthyCategoryBusiness;
import com.cnd.greencube.server.cache.CACHE_HEALTHY_CATEGORY;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.HealthyCategoryService;
import com.cnd.greencube.server.util.JsonUtils;

/**
 * 业务类别服务实现类
 * 
 * @author huxg
 * 
 */
public class HealthyCategoryServiceImpl extends AbstractServiceImpl implements HealthyCategoryService.Iface {
	private static final Logger logger = Logger.getLogger(HealthyCategoryServiceImpl.class);

	@Resource(name = "HealthyCategoryBusinessImpl")
	protected HealthyCategoryBusiness healthyCategoryBusiness;
	
	@Resource(name = "CACHE_HEALTHY_CATEGORY")
	protected CACHE_HEALTHY_CATEGORY cache;

	
	@Override
	public String getHealthyCategories() throws TException {
		try {
			String jsonStr = healthyCategoryBusiness.getHealthyCategoriesFromCache();
			JSONArray ja = JsonUtils.String2JSONArray(jsonStr);
			return Message.okMessage(ja);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getHealthyCategory(String id) throws TException {
		try {
			CodeHealthyCategory c = healthyCategoryBusiness.getHealthyCategory(id);
			return Message.okMessage(c);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String createHealthyCategory(String parentId, String categoryJson) throws TException {
		try {
			CodeHealthyCategory c = JsonUtils.String2Bean(categoryJson, CodeHealthyCategory.class);
			CodeHealthyCategory category  = healthyCategoryBusiness.createHealthyCategory(parentId, c);
			cache.writeCache();
			return Message.okMessage(category);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新业务分类
	 */
	@Override
	public String updateHealthyCategory(String categoryJson) throws TException {
		try {
			CodeHealthyCategory c = JsonUtils.String2Bean(categoryJson, CodeHealthyCategory.class);
			CodeHealthyCategory category  = healthyCategoryBusiness.updateHealthyCategory(c);
			cache.writeCache();
			return Message.okMessage(category);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除业务分类
	 */
	@Override
	public String deleteCategory(String categoryId) throws TException {
		try {
			healthyCategoryBusiness.deleteCategory(categoryId);
			cache.writeCache();
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
