package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.OrderInvoiceDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.OrderInvoice;

/**
 * 订单数据访问实现类
 * 
 * @author huxg
 * 
 */
@Repository("OrderInvoiceDaoImpl")
public class OrderInvoiceDaoImpl extends RedisDaoSupportImpl<OrderInvoice, String> implements OrderInvoiceDao {

	/**
	 * 取得订单发票
	 */
	@Override
	public OrderInvoice getOrderInvoice(String orderid) {
		String sql = "select t.id from OrderInvoice t where t.orderId = ?";
		List<OrderInvoice> invoices = super.findList(sql);
		if (invoices.size() > 0) {
			return invoices.get(0);
		}
		return null;
	}

}
