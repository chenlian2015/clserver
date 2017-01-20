package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.util.PageInfo;

public interface OrderDao extends BaseDao<Order, String> {
	/**
	 * 取得用户订单
	 * 
	 * @param userid
	 */
	public List<Order> getUserOrders(String userid, String filter, PageInfo pageInfo);

	/**
	 * 依据订单号查询订单列表，如果订单号为空，则查询所有
	 * 
	 * @param orderNum 订单号
	 * @param status 订单状态
	 */
	public List<Order> searchOrdersByOrderNum(String orderNum, PageInfo pageInfo);

}
