package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.TrainCategoryDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.TrainCategory;

/**
 * 培训分类数据访问类
 * @author huxg
 *
 */
@Repository("TrainCategoryDaoImpl")
public class TrainCategoryDaoImpl extends RedisDaoSupportImpl<TrainCategory, String> implements TrainCategoryDao {
	@Override
	public List<TrainCategory> loadTrainCategories() {
		String sql = "select t.id from TrainCategory t order by t.order asc";
		return super.findList(sql);
	}
}
