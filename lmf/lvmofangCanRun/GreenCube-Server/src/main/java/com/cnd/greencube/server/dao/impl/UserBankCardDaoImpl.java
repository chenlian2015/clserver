package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.dao.UserBankCardDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.UserBankCard;

@Repository("UserBankCardDaoImpl")
public class UserBankCardDaoImpl extends RedisDaoSupportImpl<UserBankCard, String> implements UserBankCardDao{

}
