/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.business.TrainBusiness;
import com.cnd.greencube.server.entity.Train;
import com.cnd.greencube.server.entity.TrainCategory;
import com.cnd.greencube.server.entity.TrainQuestion;
import com.cnd.greencube.server.entity.TrainQuestionOption;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.TrainService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 培训服务实现类
 * 
 * @author huxg
 * 
 */
public class TrainServiceImpl implements TrainService.Iface {
	private static final Logger logger = Logger.getLogger(TrainServiceImpl.class);

	@Resource(name = "TrainBusinessImpl")
	protected TrainBusiness trainBusiness;

	@Override
	public String deleteQuestionById(String arg0) throws TException {
		try {
			trainBusiness.deleteQuestionById(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String deleteTrainById(String arg0) throws TException {
		try {
			trainBusiness.deleteTrainById(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String deleteTrainCategoryById(String arg0) throws TException {
		try {
			trainBusiness.deleteTrainCategoryById(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getQuestionById(String arg0) throws TException {
		try {
			return Message.okMessage(trainBusiness.getQuestionById(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String saveQuestion(String arg0, String arg1, String arg2) throws TException {
		try {
			TrainQuestion q = JsonUtils.String2Bean(arg0, TrainQuestion.class);
			String[] p1 = StringUtils.isEmpty(arg1) ? new String[] {} : arg1.split(",");
			String[] p2 = StringUtils.isEmpty(arg2) ? new String[] {} : arg2.split(",");
			trainBusiness.saveQuestion(q, p1, p2);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateQuestion(String arg0, String arg1, String arg2) throws TException {
		try {
			TrainQuestion q = JsonUtils.String2Bean(arg0, TrainQuestion.class);
			String[] p1 = StringUtils.isEmpty(arg1) ? new String[] {} : arg1.split(",");
			String[] p2 = StringUtils.isEmpty(arg2) ? new String[] {} : arg2.split(",");
			trainBusiness.updateQuestion(q, p1, p2);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getTrainById(String arg0) throws TException {
		try {
			return Message.okMessage(trainBusiness.getTrainById(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 前端-取得一个培训的所有题、选项，压缩返回
	 * 
	 * @return
	 * @throws TException
	 */
	@Override
	public String frt_getTrainById(String trainId) throws TException {
		try {
			String json = trainBusiness.loadPaperFromCache(trainId);
			JSONObject jo = JsonUtils.String2JSONObject(json);
			return Message.okMessage(jo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getTrainCategoryById(String arg0) throws TException {
		try {
			return Message.okMessage(trainBusiness.getTrainCategoryById(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadQuestionOptions(String arg0) throws TException {
		try {
			List<TrainQuestionOption> questions = trainBusiness.loadQuestionOptions(arg0);
			return Message.okMessage(questions);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadQuestions(String arg0) throws TException {
		try {
			List<TrainQuestion> questions = trainBusiness.loadQuestions(arg0);
			return Message.okMessage(questions);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadTrainCategories() throws TException {
		try {
			List<TrainCategory> questions = trainBusiness.loadTrainCategories();
			return Message.okMessage(questions);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadTrains(String arg0) throws TException {
		try {
			List<Train> questions = trainBusiness.loadTrains(arg0);
			return Message.okMessage(questions);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String saveTrain(String arg0) throws TException {
		try {
			Train train = JsonUtils.String2Bean(arg0, Train.class);
			trainBusiness.saveTrain(train);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String saveTrainCategory(String arg0) throws TException {
		try {
			TrainCategory category = JsonUtils.String2Bean(arg0, TrainCategory.class);
			category.setCreateTime(new Date());
			trainBusiness.saveTrainCategory(category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String searchQuestions(String arg0) throws TException {
		try {
			List<TrainQuestion> questions = trainBusiness.searchQuestions(arg0);
			return Message.okMessage(questions);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateTrain(String arg0) throws TException {
		try {
			Train category = JsonUtils.String2Bean(arg0, Train.class);
			trainBusiness.updateTrain(category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateTrainCategory(String arg0) throws TException {
		try {
			TrainCategory category = JsonUtils.String2Bean(arg0, TrainCategory.class);
			trainBusiness.updateTrainCategory(category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
