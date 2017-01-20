package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.VirtualAccountDetailDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.VirtualAccountDetail;

/**
 * 虚拟账户明细数据访问类
 * 
 * @author huxg
 * 
 */
@Repository("VirtualAccountDetailDaoImpl")
public class VirtualAccountDetailDaoImpl extends RedisDaoSupportImpl<VirtualAccountDetail, String> implements VirtualAccountDetailDao {

	@Override
	public VirtualAccountDetail getUserVirtualAccountDetail(String userid) {
		String sql = "select t.id from VirtualAccountDetail t where t.userId = ?";
		List<VirtualAccountDetail> details = super.findList(sql, new Object[] { userid });
		if (details != null && details.size() > 0)
			return details.get(0);
		return null;
	}
}
