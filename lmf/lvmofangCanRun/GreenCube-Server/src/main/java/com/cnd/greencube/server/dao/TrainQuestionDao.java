package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.TrainQuestion;

public interface TrainQuestionDao extends BaseDao<TrainQuestion, String> {
	List<TrainQuestion> loadQuestions(String trainId);
}
