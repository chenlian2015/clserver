package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.OrderMail;

public interface OrderMailDao extends BaseDao<OrderMail, String> {
	/**
	 * 取得一个订单的快递信息
	 * @param orderid
	 * @return
	 */
	OrderMail getOrderMailing(String orderid) ;
}
