package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.TrainQuestionOption;

public interface TrainQuestionOptionDao extends BaseDao<TrainQuestionOption, String> {
	List<TrainQuestionOption> loadQuestionOptions(String questionId) ;
}
