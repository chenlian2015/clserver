package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Train;

public interface TrainDao extends BaseDao<Train, String> {
	List<Train> loadTrains(String categoryId);
}
