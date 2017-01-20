/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import com.cnd.greencube.server.entity.CodeHealthyCategory;

/**
 * 业务类别服务实现类
 * 
 * @author huxg
 * 
 */
public interface HealthyCategoryBusiness extends BaseBusiness<CodeHealthyCategory, String> {
	/**
	 * 从缓存中读取全部业务分类
	 * 
	 * @return
	 */
	String getHealthyCategoriesFromCache();

	/**
	 * 取得某一个业务分类
	 * 
	 * @param id
	 * @return
	 */
	CodeHealthyCategory getHealthyCategory(String id);

	/**
	 * 创建业务分类
	 * 
	 * @param parentId
	 * @param categoryJson
	 * @return
	 */
	CodeHealthyCategory createHealthyCategory(String parentId, CodeHealthyCategory category);

	/**
	 * 修改业务分类
	 * 
	 * @param categoryJson
	 * @return
	 */
	CodeHealthyCategory updateHealthyCategory(CodeHealthyCategory category);

	/**
	 * 删除业务分类
	 * 
	 * @param categoryId
	 * @return
	 */
	boolean deleteCategory(String categoryId);

}
