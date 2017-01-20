package com.cnd.greencube.server.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.dao.TrainCategoryDao;
import com.cnd.greencube.server.dao.TrainDao;
import com.cnd.greencube.server.dao.TrainQuestionDao;
import com.cnd.greencube.server.dao.TrainQuestionOptionDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisActionNoResult;
import com.cnd.greencube.server.entity.Train;
import com.cnd.greencube.server.entity.TrainQuestion;
import com.cnd.greencube.server.entity.TrainQuestionOption;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 地区Redis缓存类
 * 
 * @author huxg
 * 
 */
@Service("CACHE_TEST_PAPER")
public class CACHE_TEST_PAPER {
	private static final String CACHE_KEY_TRAIN_TEST_PAPER = "objpool/train/testpaper";

	@Resource(name = "jedisTemplate")
	protected JedisTemplate jedisTemplate;

	@Resource(name = "TrainCategoryDaoImpl")
	protected TrainCategoryDao trainCategoryDao;

	@Resource(name = "TrainDaoImpl")
	protected TrainDao trainDao;

	@Resource(name = "TrainQuestionDaoImpl")
	protected TrainQuestionDao trainQuestionDao;

	@Resource(name = "TrainQuestionOptionDaoImpl")
	protected TrainQuestionOptionDao trainQuestionOptionDao;

	/**
	 * 取得地区在缓存中得字符串数据，直接返回给前端，如果不存在，则生成
	 * 
	 * @return
	 */
	public String loadPaperFromCache(final String trainId) {
		return jedisTemplate.execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				String str = jedis.hget(CACHE_KEY_TRAIN_TEST_PAPER, trainId);
				if (str == null) {
					str = writePaperFromCache(trainId);
				}
				return str;
			}
		});
	}

	public String writePaperFromCache(final String trainId) {
		if (StringUtils.isEmpty(trainId))
			return null;

		Train train = trainDao.get(trainId);
		if (train != null) {
			List<TrainQuestion> question = getQuestion(trainId);
			List<TrainQuestionOption> options = getOptions(trainId);

			final String str = make(train, question, options);
			jedisTemplate.execute(new JedisActionNoResult() {
				@Override
				public void action(Jedis jedis) {
					jedis.hset(CACHE_KEY_TRAIN_TEST_PAPER, trainId, str);
				}
			});
			return str;
		}
		return null;
	}

	public void removeFromCache(final String trainId) {
		jedisTemplate.execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.hdel(CACHE_KEY_TRAIN_TEST_PAPER, trainId);
			}
		});
	}

	List<TrainQuestion> getQuestion(String trainId) {
		String sql = "select t1.id from TrainQuestion t1 where t1.trainId = ?";
		List<TrainQuestion> questions = trainQuestionDao.findList(sql, new Object[] { trainId });
		return questions;
	}

	List<TrainQuestionOption> getOptions(String trainId) {
		String sql = "select t1.id from TrainQuestionOption t1 , TrainQuestion t2 where t1.questionId = t2.id and t2.trainId = ?";
		List<TrainQuestionOption> options = trainQuestionOptionDao.findList(sql, new Object[] { trainId });
		return options;
	}

	String make(Train train, List<TrainQuestion> questions, List<TrainQuestionOption> options) {
		for (TrainQuestion q : questions) {
			for (TrainQuestionOption option : options) {
				if (q.getId().equals(option.getQuestionId())) {
					q.addOption(option);
				}
			}
		}
		train.setQuestions(questions);
		return JsonUtils.bean2String(train);
	}
}
