package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.TrainCategory;

public interface TrainCategoryDao extends BaseDao<TrainCategory, String> {
	List<TrainCategory> loadTrainCategories();
}
