/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Train;
import com.cnd.greencube.server.entity.TrainCategory;
import com.cnd.greencube.server.entity.TrainQuestion;
import com.cnd.greencube.server.entity.TrainQuestionOption;

/**
 * 培训服务实现类
 * 
 * @author huxg
 * 
 */
public interface TrainBusiness {
	/**
	 * 获取试卷类别列表
	 * 
	 * @return
	 */
	List<TrainCategory> loadTrainCategories();

	/**
	 * 获取类别详细信息
	 * 
	 * @param categoryId
	 * @return
	 * 
	 * @param categoryId
	 */
	TrainCategory getTrainCategoryById(String categoryId);

	/**
	 * 保存类别
	 * 
	 * @param category
	 * 
	 * @param categoryJson
	 */
	void saveTrainCategory(TrainCategory category);

	/**
	 * 更新类别
	 * 
	 * @param categoryJson
	 */
	void updateTrainCategory(TrainCategory category);

	/**
	 * 删除类别
	 * 
	 * @param categoryId
	 * 
	 * @param categoryId
	 */
	void deleteTrainCategoryById(String categoryId);

	/**
	 * 获取试卷列表
	 * 
	 * @param categoryId
	 *            -- 类别id，可以为空，为空时则查询全部培训
	 * @return
	 * 
	 * @param categoryId
	 */
	List<Train> loadTrains(String categoryId);

	/**
	 * 获取试卷详细信息
	 * 
	 * @param trainId
	 * @return
	 * 
	 * @param trainId
	 */
	Train getTrainById(String trainId);

	/**
	 * 保存试卷
	 * 
	 * @param trainJson
	 * 
	 * @param trainJson
	 */
	void saveTrain(Train train);

	/**
	 * 更新试卷
	 * 
	 * @param trainJson
	 * 
	 * @param trainJson
	 */
	void updateTrain(Train train);

	/**
	 * 删除试卷
	 * 
	 * @param trainId
	 * 
	 * @param trainId
	 */
	void deleteTrainById(String trainId);

	/**
	 * 获取试题列表
	 * 
	 * @param trainId
	 *            -- 培训id，可为空，为空时则提取全部试题
	 * @return
	 * 
	 * @param trainId
	 */
	List<TrainQuestion> loadQuestions(String trainId);

	/**
	 * 通过关键字搜索一个试题
	 * 
	 * @param keyword
	 */
	List<TrainQuestion> searchQuestions(String keyword);

	/**
	 * 获取试题详细信息
	 * 
	 * @return
	 * 
	 * @param questionId
	 */
	TrainQuestion getQuestionById(String questionId);

	/**
	 * 删除试题
	 * 
	 * 
	 */
	void deleteQuestionById(String questionId);

	/**
	 * 保存问题
	 * @param question
	 * @param seqs
	 * @param titles
	 */
	void saveQuestion(TrainQuestion question, String[] seqs, String[] titles);
	
	
	/**
	 * 更新问题
	 * @param question
	 * @param seqs
	 * @param titles
	 */
	void updateQuestion(TrainQuestion question, String[] seqs, String[] titles);
	
	/**
	 * 获取选择题选项列表
	 * 
	 * @return
	 * 
	 * @param questionId
	 */
	List<TrainQuestionOption> loadQuestionOptions(String questionId);
	
	/**
	 * 重缓存中装载试卷
	 */
	String loadPaperFromCache(String trainId);
	
	/**
	 * 写缓存
	 * @param trainId
	 */
	void writePaperFromCache(String trainId);
	
}
