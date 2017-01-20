package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiMemberFeeCategoryDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiMemberFeeCategory;

@Repository("FiMemberFeeCategoryDaoImpl")
public class FiMemberFeeCategoryDaoImpl extends RedisDaoSupportImpl<FiMemberFeeCategory, String> implements FiMemberFeeCategoryDao {

}
