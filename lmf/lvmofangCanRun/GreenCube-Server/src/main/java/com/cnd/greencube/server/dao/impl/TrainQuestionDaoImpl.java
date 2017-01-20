package com.cnd.greencube.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.TrainQuestionDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.TrainQuestion;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 培训-题目数据访问类
 * 
 * @author huxg
 * 
 */
@Repository("TrainQuestionDaoImpl")
public class TrainQuestionDaoImpl extends RedisDaoSupportImpl<TrainQuestion, String> implements TrainQuestionDao {
	@Override
	public List<TrainQuestion> loadQuestions(String trainId) {
		String sql = "select t.id from TrainQuestion t ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(trainId)) {
			sql += " where t.trainId = ?";
			params.add(trainId);
		}
		sql += " order by createTime desc";
		return super.findList(sql, params.toArray());
	}
}
