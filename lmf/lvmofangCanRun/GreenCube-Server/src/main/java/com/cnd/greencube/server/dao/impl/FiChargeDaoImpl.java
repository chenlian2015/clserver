package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiChargeDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiCharge;

@Repository("FiChargeDaoImpl")
public class FiChargeDaoImpl extends RedisDaoSupportImpl<FiCharge, String> implements FiChargeDao {

}
