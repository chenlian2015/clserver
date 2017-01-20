package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiPaymentDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiPayment;

@Repository("FiPaymentDaoImpl")
public class FiPaymentDaoImpl extends RedisDaoSupportImpl<FiPayment, String> implements FiPaymentDao {

}
