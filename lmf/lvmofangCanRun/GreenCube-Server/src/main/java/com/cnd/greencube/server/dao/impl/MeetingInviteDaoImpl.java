package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MeetingInviteDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MeetingInvite;

@Repository("MeetingInviteDaoImpl")
public class MeetingInviteDaoImpl extends RedisDaoSupportImpl<MeetingInvite, String> implements MeetingInviteDao {

}
