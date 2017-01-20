package com.cnd.greencube.server.business.impl.payment;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeRefundCollection;

public class MyCharge extends Charge {
	@Override
	public ChargeRefundCollection getRefunds() {
		return null;
	}
}
