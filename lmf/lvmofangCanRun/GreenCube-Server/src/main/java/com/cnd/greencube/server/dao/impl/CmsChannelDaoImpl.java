package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.CmsChannelDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.CmsChannel;

@Repository("CmsChannelDaoImpl")
public class CmsChannelDaoImpl extends RedisDaoSupportImpl<CmsChannel, String> implements CmsChannelDao {
}
