package com.cnd.greencube.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cnd.greencube.server.dao.OrderDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.util.PageInfo;

@Repository("OrderDaoImpl")
public class OrderDaoImpl extends RedisDaoSupportImpl<Order, String> implements OrderDao {
	/**
	 * 取得用户订单
	 * 
	 * @param userid
	 */
	public List<Order> getUserOrders(String userid, String filter, PageInfo pageinfo) {
		String sql = "select t.id from Order t where t.userId = ?";
		String ctql = "select count(t) from Order t where t.userId = ?";

		if (!StringUtils.isEmpty(filter)) {
			if ("unpay".equals(filter)) {
				sql += " and t.status = 20";
				ctql += " and t.status = 20";
			} else if ("unconfirm".equals(filter)) {
				sql += " and t.status = 30";
				ctql += " and t.status = 30";
			} else if ("uncomment".equals(filter)) {
				sql += " and t.status = 40";
				ctql += " and t.status = 40";
			} else if ("complete".equals(filter)) {
				sql += " and t.status = 50";
				ctql += " and t.status = 50";
			}
		}
		sql += " order by t.orderTime desc";
		return super.findList(sql, ctql, new Object[] { userid }, pageinfo);
	}
	
	/**
	 * 根据单号查询订单
	 * 
	 * @param orderId
	 */
	@Override
	public List<Order> searchOrdersByOrderNum(String orderNum, PageInfo pageInfo) {
		String sql = "select t.id from Order t ";
		String ctql = "select count(t) from Order t ";
		
		List<Object> params = new ArrayList<Object> ();
		if(!StringUtils.isEmpty(orderNum)) {
			sql += " where t.orderNum like ?";
			ctql += " where t.orderNum like ?";
			params.add("%" + orderNum + "%");
		}
//		if(status != 0)
//		{
//			sql += " and t.status = ? order by t.orderTime";
//			ctql += " and t.status = ? order by t.orderTime";
//		}
		sql += "  order by t.orderTime desc";
		ctql += "  order by t.orderTime desc";
		List<Order> orders = super.findList(sql, ctql, params.toArray(), pageInfo);
		
		return orders;
	}
}
