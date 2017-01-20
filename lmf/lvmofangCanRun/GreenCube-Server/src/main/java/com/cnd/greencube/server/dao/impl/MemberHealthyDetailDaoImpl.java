package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MemberHealthyDetailDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MemberHealthyDetail;

@Repository("MemberHealthyDetailDaoImpl")
public class MemberHealthyDetailDaoImpl extends RedisDaoSupportImpl<MemberHealthyDetail, String> implements MemberHealthyDetailDao {

}
