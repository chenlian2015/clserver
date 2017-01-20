package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.VirtualAccount;

@Repository("VirtualAccountDaoImpl")
public class VirtualAccountDaoImpl extends RedisDaoSupportImpl<VirtualAccount, String> implements VirtualAccountDao {

	@Override
	public VirtualAccount getUserVirtualAccount(String userid) {
		String sql = "select t.id from VirtualAccount t where t.userId = ?";
		List<VirtualAccount> as = super.findList(sql, new Object[] { userid });
		if (as != null && as.size() > 0) {
			return as.get(0);
		}
		return null;
	}

}
