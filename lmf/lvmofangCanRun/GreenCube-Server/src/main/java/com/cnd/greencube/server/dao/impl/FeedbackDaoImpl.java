package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FeedbackDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Feedback;

@Repository("FeedbackDaoImpl")
public class FeedbackDaoImpl extends RedisDaoSupportImpl<Feedback, String> implements FeedbackDao {

}
