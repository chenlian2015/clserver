package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.LogUserBehaviourDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.LogUserBehaviour;

@Repository("LogUserBehaviourDaoImpl")
public class LogUserBehaviourDaoImpl extends RedisDaoSupportImpl<LogUserBehaviour, String> implements LogUserBehaviourDao {

}
