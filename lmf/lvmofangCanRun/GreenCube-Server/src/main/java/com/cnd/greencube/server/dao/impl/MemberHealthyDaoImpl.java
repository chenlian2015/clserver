package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MemberHealthyDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MemberHealthy;

@Repository("MemberHealthyDaoImpl")
public class MemberHealthyDaoImpl extends RedisDaoSupportImpl<MemberHealthy, String> implements MemberHealthyDao {

}
