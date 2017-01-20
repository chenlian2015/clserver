package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.FiMemberFee;

public interface FiMemberFeeDao extends BaseDao<FiMemberFee, String> {
	FiMemberFee getMemberFeeByPaymentId(String paymentId);
}
