package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.QADao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Qa;

@Repository("QADaoImpl")
public class QADaoImpl extends RedisDaoSupportImpl<Qa, String> implements QADao {

}
