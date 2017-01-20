package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MeetingMessageDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MeetingMessage;

@Repository("MeetingMessageDaoImpl")
public class MeetingMessageDaoImpl extends RedisDaoSupportImpl<MeetingMessage, String> implements MeetingMessageDao {
}
