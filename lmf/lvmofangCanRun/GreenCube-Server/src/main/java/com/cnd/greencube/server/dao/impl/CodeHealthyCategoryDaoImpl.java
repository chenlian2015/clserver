package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.CodeHealthyCategory;

@Repository("CodeHealthyCategoryDaoImpl")
public class CodeHealthyCategoryDaoImpl extends RedisDaoSupportImpl<CodeHealthyCategory, String> implements CodeHealthyCategoryDao {

}
