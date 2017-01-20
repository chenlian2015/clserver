package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.WithdrawDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiWithdraw;

@Repository("WithdrawDaoImpl")
public class WithdrawDaoImpl extends RedisDaoSupportImpl<FiWithdraw, String> implements WithdrawDao {

}
