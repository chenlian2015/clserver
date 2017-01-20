package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.TrainQuestionOptionDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.TrainQuestionOption;

/**
 * 问题答案数据访问类
 * 
 * @author huxg
 * 
 */
@Repository("TrainQuestionOptionDaoImpl")
public class TrainQuestionOptionDaoImpl extends RedisDaoSupportImpl<TrainQuestionOption, String> implements TrainQuestionOptionDao {
	@Override
	public List<TrainQuestionOption> loadQuestionOptions(String questionId) {
		String sql = "select t.id from TrainQuestionOption t where t.questionId = ?";
		return super.findList(sql, new Object[] { questionId });
	}
}
