package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.OpportunitySpaceCategory;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 工作空间-分类business
 * 
 * @author dongyali
 *
 */
public interface OpportunitySpaceCategoryBusiness {
	/**
	 * 得到所有工作空间分类
	 * 
	 * @return
	 */
	List<OpportunitySpaceCategory> loadOpportunitySpaceCategorys();
	/**
	 * 得到推荐的工作空间分类
	 * 
	 * @return
	 */
	List<OpportunitySpaceCategory> loadRecommendCategorys();

	/**
	 * 依据id得到工作空间分类实体
	 * 
	 * @param id
	 * @return
	 */
	OpportunitySpaceCategory getOpportunitySpaceCategory(String id);

	/**
	 * 新增工作空间分类
	 * 
	 * @param parentId
	 * @param OSCategory
	 * @return
	 */
	OpportunitySpaceCategory createOpportunitySpaceCategory(String parentId, OpportunitySpaceCategory OSCategory);

	/**
	 * 修改工作空间分类
	 * 
	 * @param OSCategory
	 * @return
	 */
	OpportunitySpaceCategory updateOpportunitySpaceCategory(OpportunitySpaceCategory OSCategory);

	/**
	 * 删除工作空间分类
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOpportunitySpaceCategory(String id);
}
