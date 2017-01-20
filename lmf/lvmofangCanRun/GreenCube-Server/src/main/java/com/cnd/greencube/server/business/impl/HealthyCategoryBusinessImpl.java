/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.HealthyCategoryBusiness;
import com.cnd.greencube.server.cache.CACHE_HEALTHY_CATEGORY;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 业务类别服务实现类
 * 
 * @author huxg
 * 
 */
@Service("HealthyCategoryBusinessImpl")
public class HealthyCategoryBusinessImpl extends BaseBusinessImpl<CodeHealthyCategory, String>
		implements HealthyCategoryBusiness {

	@Resource(name = "CodeHealthyCategoryDaoImpl")
	protected CodeHealthyCategoryDao codeHealthyCategoryDao;

	@Resource(name = "CACHE_HEALTHY_CATEGORY")
	protected CACHE_HEALTHY_CATEGORY cache;

	@Override
	public String getHealthyCategoriesFromCache() {
		return cache.getFromCache();
	}

	@Override
	public CodeHealthyCategory getHealthyCategory(String id) {
		return codeHealthyCategoryDao.get(id);
	}

	@Transactional
	@Override
	public CodeHealthyCategory createHealthyCategory(String parentId, CodeHealthyCategory category) {
		category.setId(null);
		if (category.getOrder() == null)
			category.setOrder(99);

		codeHealthyCategoryDao.save(category);

		String parentClassCode = null;
		// 如果有父节点，得到父节点的classcode
		if (!StringUtils.isEmpty(parentId)) {
			CodeHealthyCategory parent = codeHealthyCategoryDao.get(parentId);
			if (parent != null) {
				if (!StringUtils.isEmpty(parent.getClassCode())) {
					parentClassCode = parent.getClassCode();
				}
			}
		}
      //如果当前类别是父节点或者父节点的classcode是null
		if (StringUtils.isEmpty(parentClassCode)) {
			category.setClassCode(category.getId());
		} else {
			category.setClassCode(parentClassCode + "." + category.getId());
		}

		codeHealthyCategoryDao.update(category);
		return category;
	}

	/**
	 * 更新业务分类
	 */
	@Transactional
	@Override
	public CodeHealthyCategory updateHealthyCategory(CodeHealthyCategory category) {
		codeHealthyCategoryDao.update(category);
		return category;
	}

	/**
	 * 删除业务分类
	 */
	@Transactional
	@Override
	public boolean deleteCategory(String categoryId) {
		codeHealthyCategoryDao.delete(categoryId);
		return true;
	}

}
