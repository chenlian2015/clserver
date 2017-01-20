package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiMemberFeeDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiMemberFee;

@Repository("FiMemberFeeDaoImpl")
public class FiMemberFeeDaoImpl extends RedisDaoSupportImpl<FiMemberFee, String> implements FiMemberFeeDao {

	@Override
	public FiMemberFee getMemberFeeByPaymentId(String paymentId) {
		String sql = "select t.id from FiMemberFee t where t.paymentId = ?";
		List<FiMemberFee> fees = super.findList(sql, new Object[] { paymentId });
		if (fees != null && fees.size() > 0) {
			return fees.get(0);
		}
		return null;
	}

}
