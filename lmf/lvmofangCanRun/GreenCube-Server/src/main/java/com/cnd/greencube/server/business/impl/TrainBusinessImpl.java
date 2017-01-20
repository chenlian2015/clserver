/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.TrainBusiness;
import com.cnd.greencube.server.cache.CACHE_TEST_PAPER;
import com.cnd.greencube.server.dao.TrainCategoryDao;
import com.cnd.greencube.server.dao.TrainDao;
import com.cnd.greencube.server.dao.TrainQuestionDao;
import com.cnd.greencube.server.dao.TrainQuestionOptionDao;
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
@SuppressWarnings("rawtypes")
@Service("TrainBusinessImpl")
public class TrainBusinessImpl extends BaseBusinessImpl implements TrainBusiness {
	@Resource(name = "CACHE_TEST_PAPER")
	protected CACHE_TEST_PAPER cache_test_paper;

	@Resource(name = "TrainCategoryDaoImpl")
	protected TrainCategoryDao trainCategoryDao;

	@Resource(name = "TrainDaoImpl")
	protected TrainDao trainDao;

	@Resource(name = "TrainQuestionDaoImpl")
	protected TrainQuestionDao trainQuestionDao;

	@Resource(name = "TrainQuestionOptionDaoImpl")
	protected TrainQuestionOptionDao trainQuestionOptionDao;

	@Override
	public List<TrainCategory> loadTrainCategories() {
		return trainCategoryDao.loadTrainCategories();
	}

	@Override
	public TrainCategory getTrainCategoryById(String categoryId) {
		return trainCategoryDao.get(categoryId);
	}

	@Transactional
	@Override
	public void saveTrainCategory(TrainCategory category) {
		category.setOrder(99);
		trainCategoryDao.save(category);
	}

	@Transactional
	@Override
	public void updateTrainCategory(TrainCategory category) {
		trainCategoryDao.update(category);
	}

	@Transactional
	@Override
	public void deleteTrainCategoryById(String categoryId) {
		trainCategoryDao.delete(categoryId);
	}

	@Override
	public List<Train> loadTrains(String categoryId) {
		return trainDao.loadTrains(categoryId);
	}

	@Override
	public Train getTrainById(String trainId) {
		return trainDao.get(trainId);
	}

	@Transactional
	@Override
	public void saveTrain(Train train) {
		train.setTopicCount(0);
		train.setCreateTime(new Date());
		trainDao.save(train);
	}

	@Transactional
	@Override
	public void updateTrain(Train train) {
		trainDao.update(train);
		cache_test_paper.writePaperFromCache(train.getId());
	}

	@Transactional
	@Override
	public void deleteTrainById(String trainId) {
		trainDao.delete(trainId);
		cache_test_paper.removeFromCache(trainId);
	}

	@Override
	public List<TrainQuestion> loadQuestions(String trainId) {
		return trainQuestionDao.loadQuestions(trainId);
	}

	@Override
	public List<TrainQuestion> searchQuestions(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public void saveQuestion() {
	// int length = seqs.length;
	// topic.setDCreateTime(new Date());
	// String number = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
	// + "";
	// boolean flag = true;
	// while (flag) {
	// boolean f = topicIsExist(number);
	// if (f) {
	// flag = false;
	// } else {
	// number = number + "0";
	// }
	// }
	// topic.setCTopicNumber(number);
	// examTopicDao.save(topic);
	// ExamTopicItem item = null;
	// for (int i = 0; i < length; i++) {
	// item = new ExamTopicItem();
	// item.setCTopicItem(titles[i]);
	// item.setCItemSeq(seqs[i]);
	// item.setDCreateTime(new Date());
	// examTopicItemDao.save(item);
	// }
	// }

	// @Override
	// public void updateQuestion(){
	// if (StringUtils.isEmpty(topic.getCTopicNumber())) {
	// String number = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
	// + "";
	// topic.setCTopicNumber(number);
	// }
	// examTopicDao.update(topic);
	// List<String> seqlist = new ArrayList<String>();
	// List<String> titlelist = new ArrayList<String>();
	// int length = seqs.length;
	// int size = items.size();
	// if (length > size) {
	// for (int o = size; o < length; o++) {
	// seqlist.add(seqs[o]);
	// titlelist.add(titles[o]);
	// }
	// }
	// for (int i = 0; i < size; i++) {
	// ExamTopicItem item = items.get(i);
	// String seq = items.get(i).getCItemSeq();
	// for (int j = 0; j < length; j++) {
	// if (seq == seqs[j] || seq.equals(seqs[j])) {
	// item.setCTopicItem(titles[j]);
	// examTopicItemDao.update(item);
	// }
	// }
	// }
	// int seq_size = seqlist.size();
	// ExamTopicItem item = null;
	// if (seq_size > 0) {
	// for (int k = 0; k < seq_size; k++) {
	// item = new ExamTopicItem();
	// item.setCItemSeq((String) seqlist.get(k));
	// item.setCTopicItem((String) titlelist.get(k));
	// item.setDCreateTime(new Date());
	// examTopicItemDao.save(item);
	// }
	// }
	// }

	@Override
	public TrainQuestion getQuestionById(String questionId) {
		return trainQuestionDao.get(questionId);
	}

	@Transactional
	@Override
	public void deleteQuestionById(String questionId) {
		TrainQuestion q = trainQuestionDao.get(questionId);
		trainQuestionDao.delete(q.getId());
		cache_test_paper.writePaperFromCache(q.getTrainId());
	}

	/**
	 * 保存试题
	 * 
	 * @param topic
	 * @param seqs
	 * @param titles
	 */
	@Transactional
	@Override
	public void saveQuestion(TrainQuestion question, String[] seqs, String[] titles) {
		int length = seqs.length;
		question.setCreateTime(new Date());
		trainQuestionDao.save(question);

		TrainQuestionOption option = null;
		for (int i = 0; i < length; i++) {
			option = new TrainQuestionOption();
			option.setQuestionId(question.getId());
			option.setOptionItem(titles[i]);
			option.setItemSeq(seqs[i]);
			option.setCreateTime(new Date());
			trainQuestionOptionDao.save(option);
		}

		cache_test_paper.writePaperFromCache(question.getTrainId());
	}

	/**
	 * 更新问题
	 */
	@Transactional
	@Override
	public void updateQuestion(TrainQuestion question, String[] seqs, String[] titles) {
		trainQuestionDao.update(question);
		List<String> seqlist = new ArrayList<String>();
		List<String> titlelist = new ArrayList<String>();

		List<TrainQuestionOption> options = trainQuestionOptionDao.loadQuestionOptions(question.getId());
		int length = seqs.length;
		int size = options.size();
		if (length > size) {
			for (int o = size; o < length; o++) {
				seqlist.add(seqs[o]);
				titlelist.add(titles[o]);
			}
		}

		for (int i = 0; i < size; i++) {
			TrainQuestionOption option = options.get(i);
			String seq = options.get(i).getItemSeq();
			for (int j = 0; j < length; j++) {
				if (seq == seqs[j] || seq.equals(seqs[j])) {
					option.setOptionItem(titles[j]);
					trainQuestionOptionDao.update(option);
				}
			}
		}

		int seq_size = seqlist.size();
		TrainQuestionOption option = null;
		if (seq_size > 0) {
			for (int k = 0; k < seq_size; k++) {
				option = new TrainQuestionOption();
				option.setItemSeq((String) seqlist.get(k));
				option.setOptionItem((String) titlelist.get(k));
				option.setQuestionId(question.getId());
				option.setCreateTime(new Date());
				trainQuestionOptionDao.save(option);
			}
		}

		cache_test_paper.writePaperFromCache(question.getTrainId());
	}

	@Override
	public List<TrainQuestionOption> loadQuestionOptions(String questionId) {
		return trainQuestionOptionDao.loadQuestionOptions(questionId);
	}

	@Override
	public String loadPaperFromCache(String trainId) {
		return cache_test_paper.loadPaperFromCache(trainId);
	}

	@Override
	public void writePaperFromCache(String trainId) {
		cache_test_paper.writePaperFromCache(trainId);
	}
}
