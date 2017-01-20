package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.RequirementDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Requirement;

@Repository("RequirementDaoImpl")
public class RequirementDaoImpl extends RedisDaoSupportImpl<Requirement, String> implements RequirementDao {

}
