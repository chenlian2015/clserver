package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.UserCollectionDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.UserCollection;

/**
 * Dao - 货品
 * 
 * @author Top Team（ ）
 * @version 1.0
 */
@Repository("UserCollectionDaoImpl")
public class UserCollectionDaoImpl extends RedisDaoSupportImpl<UserCollection, String> implements UserCollectionDao {
	public static final String CACHE_USER_STORES = POOL + "UserStoreStr";

	public List<Object[]> getStoreIds(String userId) {
		String sql = "select t.foreignId, type from UserCollection t where t.userId = ?";

		List<Object[]> stores = (List<Object[]>) super.findListFromDB(sql, new Object[] { userId });
		return stores;
	}

}