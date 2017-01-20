package com.cnd.greencube.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.TrainDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Train;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 培训数据访问类
 * 
 * @author huxg
 * 
 */
@Repository("TrainDaoImpl")
public class TrainDaoImpl extends RedisDaoSupportImpl<Train, String> implements TrainDao {
	@Override
	public List<Train> loadTrains(String categoryId) {
		String sql = "select t.id from Train t ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(categoryId)) {
			sql += " where t.trainCategoryId = ?";
			params.add(categoryId);
		}
		return super.findList(sql, params.toArray());
	}
}
