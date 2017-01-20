package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FiManagerFeeDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FiManagerFee;

@Repository("FiManagerFeeDaoImpl")
public class FiManagerFeeDaoImpl extends RedisDaoSupportImpl<FiManagerFee, String> implements FiManagerFeeDao {

	@Override
	public FiManagerFee getManagerFeeByPaymentId(String paymentId) {
		String sql = "select t.id from FiManagerFee t where t.paymentId = ?";
		List<FiManagerFee> fees = super.findList(sql, new Object[] { paymentId });
		if (fees != null && fees.size() > 0) {
			return fees.get(0);
		}
		return null;
	}

}
