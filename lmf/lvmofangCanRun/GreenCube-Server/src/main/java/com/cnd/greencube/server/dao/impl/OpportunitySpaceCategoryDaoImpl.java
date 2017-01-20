package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.OpportunitySpaceCategoryDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.OpportunitySpaceCategory;

/**
 * 工作空间-分类Dao实现
 * 
 * @author dongyali
 *
 */
@Repository("OpportunitySpaceCategoryDaoImpl")
public class OpportunitySpaceCategoryDaoImpl extends RedisDaoSupportImpl<OpportunitySpaceCategory, String>
		implements OpportunitySpaceCategoryDao {

}
