package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.cnd.greencube.server.business.OpportunitySpaceCategoryBusiness;
import com.cnd.greencube.server.dao.OpportunitySpaceCategoryDao;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.entity.OpportunitySpaceCategory;
import com.cnd.greencube.server.entity.OpportunitySpaceCategory;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 工作空间business分类实现
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("rawtypes")
@Service("OpportunitySpaceCategoryBusinessImpl")
public class OpportunitySpaceCategoryBusinessImpl extends BaseBusinessImpl implements OpportunitySpaceCategoryBusiness {
	@Resource(name = "OpportunitySpaceCategoryDaoImpl")
	protected OpportunitySpaceCategoryDao opportunitySpaceCategoryDao;

	/**
	 * 得到所有工作空间分类
	 * 
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<OpportunitySpaceCategory> loadOpportunitySpaceCategorys() {
		String sql = "select t.id from OpportunitySpaceCategory t order by t.classCode, t.order";
		List<OpportunitySpaceCategory> osc = opportunitySpaceCategoryDao.findList(sql);

		List<OpportunitySpaceCategory> result = new ArrayList<OpportunitySpaceCategory>();

		Map<String, OpportunitySpaceCategory> mapCategory = new HashMap<String, OpportunitySpaceCategory>();
		for (OpportunitySpaceCategory c : osc) {
			String fjdm = c.getClassCode();
			if (StringUtils.isEmpty(fjdm) || !fjdm.contains(".")) {
				result.add(c);
			} else {
				// 取得父亲的id
				String[] p = fjdm.split("\\.");
				String parentId = p[p.length - 2];
				OpportunitySpaceCategory parent = mapCategory.get(parentId);
				if (null != parent) {
					parent.addSubCategory(c);
				}
			}

			mapCategory.put(c.getId(), c);
		}
		return result;
	}

	@Override
	public List<OpportunitySpaceCategory> loadRecommendCategorys() {
		String sql = "select t.id from OpportunitySpaceCategory t where t.recommendStatus = 1 order by t.classCode, t.order";
		List<OpportunitySpaceCategory> osc = opportunitySpaceCategoryDao.findList(sql);

		List<OpportunitySpaceCategory> result = new ArrayList<OpportunitySpaceCategory>();

		Map<String, OpportunitySpaceCategory> mapCategory = new HashMap<String, OpportunitySpaceCategory>();
		for (OpportunitySpaceCategory c : osc) {
			String fjdm = c.getClassCode();
			if (StringUtils.isEmpty(fjdm) || !fjdm.contains(".")) {
				result.add(c);
			} else {
				// 取得父亲的id
				String[] p = fjdm.split("\\.");
				String parentId = p[p.length - 2];
				OpportunitySpaceCategory parent = mapCategory.get(parentId);
				if (null != parent) {
					parent.addSubCategory(c);
				}
			}

			mapCategory.put(c.getId(), c);
		}
		return result;
	}

	/**
	 * 依据id得到工作空间分类实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public OpportunitySpaceCategory getOpportunitySpaceCategory(String id) {
		return opportunitySpaceCategoryDao.get(id);
	}

	/**
	 * 新增工作空间分类
	 * 
	 * @param parentId
	 * @param OSCategory
	 * @return
	 */
	@Override
	public OpportunitySpaceCategory createOpportunitySpaceCategory(String parentId,
			OpportunitySpaceCategory OSCategory) {
		OSCategory.setId(null);
		if (OSCategory.getOrder() == null)
			OSCategory.setOrder(99);

		opportunitySpaceCategoryDao.save(OSCategory);

		String parentClassCode = null;
		// 如果有父节点，得到父节点的classcode
		if (!StringUtils.isEmpty(parentId)) {
			OpportunitySpaceCategory parent = opportunitySpaceCategoryDao.get(parentId);
			if (parent != null) {
				if (!StringUtils.isEmpty(parent.getClassCode())) {
					parentClassCode = parent.getClassCode();
				}
			}
		}
		// 如果当前类别是父节点或者父节点的classcode是null
		if (StringUtils.isEmpty(parentClassCode)) {
			OSCategory.setClassCode(OSCategory.getId());
		} else {
			OSCategory.setClassCode(parentClassCode + "." + OSCategory.getId());
		}

		opportunitySpaceCategoryDao.update(OSCategory);
		return OSCategory;
	}

	/**
	 * 修改工作空间分类
	 * 
	 * @param OSCategory
	 * @return
	 */
	@Override
	public OpportunitySpaceCategory updateOpportunitySpaceCategory(OpportunitySpaceCategory OSCategory) {
		opportunitySpaceCategoryDao.update(OSCategory);
		return null;
	}

	/**
	 * 删除业务分类
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteOpportunitySpaceCategory(String id) {
		opportunitySpaceCategoryDao.delete(id);
		return true;
	}

}
