package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MeetingDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Meeting;

@Repository("MeetingDaoImpl")
public class MeetingDaoImpl extends RedisDaoSupportImpl<Meeting, String> implements MeetingDao {

}
