package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cnd.greencube.server.business.OpportunitySpaceCategoryBusiness;
import com.cnd.greencube.server.entity.OpportunitySpaceCategory;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.OpportunitySpaceService;
import com.cnd.greencube.server.util.JsonUtils;

/**
 * 工作空间服务实现类
 * 
 * @author dongyali
 *
 */
public class OpportunitySpaceServiceImpl extends AbstractServiceImpl implements OpportunitySpaceService.Iface {
	private static final Logger logger = Logger.getLogger(OpportunitySpaceServiceImpl.class);

	@Resource(name = "OpportunitySpaceCategoryBusinessImpl")
	protected OpportunitySpaceCategoryBusiness opportunitySpaceCategoryBusiness;

	/**
	 * 新增工作空间分类
	 * 
	 * @param parentId
	 * @param OSCategory
	 * @return
	 */
	@Override
	public String createOpportunitySpaceCategory(String parentId, String categoryJson) throws TException {
		try {
			OpportunitySpaceCategory c = JsonUtils.String2Bean(categoryJson, OpportunitySpaceCategory.class);
			OpportunitySpaceCategory category = opportunitySpaceCategoryBusiness
					.createOpportunitySpaceCategory(parentId, c);
			return Message.okMessage(category);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改工作空间分类
	 * 
	 * @param OSCategory
	 * @return
	 */
	@Override
	public String updateOpportunitySpaceCategory(String categoryJson) throws TException {
		try {
			OpportunitySpaceCategory c = JsonUtils.String2Bean(categoryJson, OpportunitySpaceCategory.class);
			OpportunitySpaceCategory category = opportunitySpaceCategoryBusiness.updateOpportunitySpaceCategory(c);
			return Message.okMessage(category);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除工作空间分类
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String deleteOpportunitySpaceCategory(String id) throws TException {
		try {
			opportunitySpaceCategoryBusiness.deleteOpportunitySpaceCategory(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 依据id得到工作空间分类实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String getOpportunitySpaceCategory(String id) throws TException {
		try {
			OpportunitySpaceCategory c = opportunitySpaceCategoryBusiness.getOpportunitySpaceCategory(id);
			return Message.okMessage(c);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 得到所有工作空间分类
	 * 
	 * @return
	 */
	@Override
	public String loadOpportunitySpaceCategorys() throws TException {
		try {
			List<OpportunitySpaceCategory> osList = opportunitySpaceCategoryBusiness.loadOpportunitySpaceCategorys();
			JSONArray ja = JsonUtils.String2JSONArray(JSON.toJSONString(osList));
			return Message.okMessage(ja);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
	/**
	 * 依据id得到工作空间分类实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String loadRecommendCategorys() throws TException {
		try {
			List<OpportunitySpaceCategory> osList = opportunitySpaceCategoryBusiness.loadRecommendCategorys();
			JSONArray ja = JsonUtils.String2JSONArray(JSON.toJSONString(osList));
			return Message.okMessage(ja);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
