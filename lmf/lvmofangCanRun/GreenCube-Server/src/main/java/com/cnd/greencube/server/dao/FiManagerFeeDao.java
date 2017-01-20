package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.FiManagerFee;

public interface FiManagerFeeDao extends BaseDao<FiManagerFee, String> {
	FiManagerFee getManagerFeeByPaymentId(String paymentId);
}
