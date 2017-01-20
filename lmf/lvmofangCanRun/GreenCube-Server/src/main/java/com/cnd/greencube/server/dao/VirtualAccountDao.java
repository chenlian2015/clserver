package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.VirtualAccount;

public interface VirtualAccountDao extends BaseDao<VirtualAccount, String> {
	VirtualAccount getUserVirtualAccount(String userid);
}
