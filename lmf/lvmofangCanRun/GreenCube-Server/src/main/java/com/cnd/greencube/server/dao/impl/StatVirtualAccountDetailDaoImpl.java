package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.StatVirtualAccountDetailDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.StatVirtualAccountDetail;

@Repository("StatVirtualAccountDetailDaoImpl")
public class StatVirtualAccountDetailDaoImpl extends RedisDaoSupportImpl<StatVirtualAccountDetail, String> implements StatVirtualAccountDetailDao {

}
