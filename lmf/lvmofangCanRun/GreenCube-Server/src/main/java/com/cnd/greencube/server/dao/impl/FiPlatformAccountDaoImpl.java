package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiPlatformAccountDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiPlatformAccount;

/**
 * 平台账户金额实现
 * 
 * @author huxg
 * 
 */
@Repository("FiPlatformAccountDaoImpl")
public class FiPlatformAccountDaoImpl extends RedisDaoSupportImpl<FiPlatformAccount, String> implements FiPlatformAccountDao {

	/**
	 * 取得平台账户
	 */
	@Override
	public FiPlatformAccount getAccount() {
		String sql = "select t.id from FiPlatformAccount t";
		List<FiPlatformAccount> as = super.findList(sql);
		if (as != null && as.size() > 0) {
			return as.get(0);
		}
		return null;
	}

}
