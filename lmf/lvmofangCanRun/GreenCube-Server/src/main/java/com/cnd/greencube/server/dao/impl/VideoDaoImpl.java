/*
 * Copyright 2005-2020 Top Team All rights reserved.
 * Support: 
 * License: top team license
 */
package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.VideoDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Video;

/**
 * Dao - 货品
 * 
 * @author Top Team（ ）
 * @version 1.0
 */
@Repository("VideoDaoImpl")
public class VideoDaoImpl extends RedisDaoSupportImpl<Video, String> implements VideoDao {
}