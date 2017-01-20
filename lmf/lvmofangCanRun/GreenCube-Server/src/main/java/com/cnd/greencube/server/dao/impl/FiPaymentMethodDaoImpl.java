package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiPaymentMethodDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiPaymentMethod;

@Repository("FiPaymentMethodDaoImpl")
public class FiPaymentMethodDaoImpl extends RedisDaoSupportImpl<FiPaymentMethod, String> implements FiPaymentMethodDao {

}
