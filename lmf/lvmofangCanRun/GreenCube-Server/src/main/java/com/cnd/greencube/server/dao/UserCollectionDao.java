package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.UserCollection;

public interface UserCollectionDao extends BaseDao<UserCollection, String> {
	public List<Object[]> getStoreIds(String userId);
}
