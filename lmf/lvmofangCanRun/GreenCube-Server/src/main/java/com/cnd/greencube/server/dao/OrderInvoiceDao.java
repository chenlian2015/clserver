package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.OrderInvoice;

/**
 * 订单数据访问接口
 * 
 * @author huxg
 * 
 */
public interface OrderInvoiceDao extends BaseDao<OrderInvoice, String> {
	/**
	 * 通过订单id取得订单发票
	 * @param orderid -- 订单id
	 * @return 订单发票对象
	 */
	OrderInvoice getOrderInvoice(String orderid);
}
