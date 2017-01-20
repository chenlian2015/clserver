package com.cnd.greencube.server.business.impl.payment.charge;

import com.pingplusplus.model.Charge;

/**
 * 支付渠道接口
 * 
 * @author huxg
 * 
 */
public interface IChannel  {
	Charge createCharge () throws Exception ;
}
