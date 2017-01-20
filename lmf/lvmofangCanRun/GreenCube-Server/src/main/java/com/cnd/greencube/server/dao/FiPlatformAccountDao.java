package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.FiPlatformAccount;

public interface FiPlatformAccountDao extends BaseDao<FiPlatformAccount, String> {
	FiPlatformAccount getAccount();
}
