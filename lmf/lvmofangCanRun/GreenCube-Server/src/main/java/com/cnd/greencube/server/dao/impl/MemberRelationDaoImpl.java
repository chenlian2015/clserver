package com.cnd.greencube.server.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MemberRelationDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MemberRelation;

@Repository("MemberRelationDaoImpl")
public class MemberRelationDaoImpl extends RedisDaoSupportImpl<MemberRelation, String> implements MemberRelationDao{
	

}
