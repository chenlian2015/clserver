/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.entity.OrderInvoice;
import com.cnd.greencube.server.entity.OrderItem;
import com.cnd.greencube.server.entity.OrderMail;
import com.cnd.greencube.server.entity.ProductOrder;
import com.cnd.greencube.server.entity.ShopOrder;
import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 订单服务实现类
 * 
 * @author huxg
 * 
 */
public interface OrderBusiness {

	/**
	 * 取得我的订单
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param filter
	 *            -- 订单类型
	 * @param pageNum
	 *            -- 页数
	 * 
	 * @param userid
	 * @param filter
	 * @param pageNum
	 */
	List<Order> loadBuierOrders(String userid, String filter, PageInfo pageInfo);

	/**
	 * 依据订单号查询订单列表，如果订单号为空，则查询所有
	 * 
	 * @param orderId
	 */
	public List<Order> searchOrdersByOrderNum(String orderNum, PageInfo pageInfo);

	/**
	 * 取得一个订单的详细信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单的json数据
	 * 
	 * @param orderid
	 */
	Order getBuierOrder(String orderid);

	/**
	 * 删除订单
	 * 
	 * @param --
	 *            订单id
	 * @return 成功与否标志
	 * 
	 * @param orderid
	 */
	boolean deleteBuierOrder(String orderid);

	/**
	 * 结账操作
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param shoppingItemsJson
	 *            -- 购物车中的订单项json数据
	 * @param needInvoice
	 *            -- 是否需要发票
	 * @return -- 成功与否标志
	 * 
	 * @param userid
	 * @param shoppingItemsJson
	 * @param couponId
	 * @param receiverId
	 */
	boolean createOrder(String userid, List<ShoppingItem> shoppingItems, String couponId, String receiverId);

	/**
	 * 提交发票申请
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @param invoiceJson
	 *            -- 订单json数据
	 * @return -- 成功与否标志
	 * 
	 * @param orderid
	 * @param invoiceJson
	 */
	boolean submitOrderInvoiceApply(String orderid, OrderInvoice invoice);

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 * 
	 * @param invoiceId
	 * @param auditorId
	 */
	boolean auditOrderInvoice(String invoiceId, String auditorId);

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 * 
	 * @param invoiceId
	 * @param auditorId
	 */
	boolean unAuditOrderInvoice(String invoiceId, String auditorId);

	/**
	 * 发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @param publisherId
	 *            -- 操作人
	 * @return -- 成功与否标志
	 * 
	 * @param invoiceId
	 * @param publisherId
	 */
	boolean publishOrderInvoice(String invoiceId, String publisherId);

	/**
	 * 取消发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @param publisherId
	 *            -- 操作人
	 * @return 成功与否标志
	 * 
	 * @param invoiceId
	 * @param publisherId
	 */
	boolean unPublishOrderInvoice(String invoiceId, String publisherId);

	/**
	 * 取得订单的发票信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单发票json数据
	 * 
	 * @param orderid
	 */
	OrderInvoice getOrderInvoice(String orderid);

	/**
	 * 返回已申请的发票列表
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 * 
	 * @param pageNum
	 */
	List<OrderInvoice> loadApplyOrderInvoice(PageInfo pageInfo);

	/**
	 * 返回已完结的发票
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 * 
	 * @param pageNum
	 */
	List<OrderInvoice> loadPublishedOrderInvoice(PageInfo pageInfo);

	/**
	 * 取得一个订单的快递信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回订单的快递信息json数据
	 * 
	 * @param orderid
	 */
	OrderMail getOrderMailing(String orderid);

	/**
	 * 取得店铺订单
	 * 
	 * @param shopid
	 *            -- 店铺id
	 * @param pageNum
	 *            -- 页数
	 * @return 返回店铺订单列表
	 * 
	 * @param shopid
	 * @param pageNum
	 */
	List<ShopOrder> loadShopOrdersForPagelit(String shopid, PageInfo pageInfo);

	/**
	 * 取得店铺订单
	 * 
	 * @param shopOrderId
	 * @return 返回店铺订单json对象
	 * 
	 * @param shopOrdeId
	 */
	ShopOrder getShopOrder(String shopOrdeId);

	/**
	 * 删除订单
	 * 
	 * @param shopOrderId
	 *            -- 店铺订单id
	 * @return 返回成功标志
	 * 
	 * @param shopOrderId
	 */
	boolean deleteShopOrder(String shopOrderId);

	/**
	 * 取得供应商订单列表
	 * 
	 * @param userId
	 *            -- 供应商id
	 * @param pageNum
	 *            -- 页数
	 * @return 返回供应商订单json数组
	 * 
	 * @param pageNum
	 */
	List<ProductOrder> loadProductOrdersForPagelit(String userId, PageInfo pageInfo);

	/**
	 * 取得供应商订单
	 * 
	 * @param userId
	 *            -- 供应商id
	 * @return 返回供应商订单json对象
	 * 
	 * @param productOrderId
	 */
	ProductOrder getProductOrder(String productOrderId);

	/**
	 * 删除供应商订单
	 * 
	 * @param productOrderId
	 *            -- 供应商订单id
	 * @return 返回成功与否标志
	 * 
	 */
	boolean deleteProductOrder(String productOrderId);

	/**
	 * 依据订单id得到订单条目（店主会商品列表）
	 * 
	 * @param orderId
	 * @return 订单商品列表
	 */
	List<OrderItem> loadOrderItemsByOrderId(String orderId);

	/**
	 * 依据订单id分页得到订单条目（店主会商品列表）
	 * 
	 * @param orderNum
	 * @param status
	 * @return 订单商品列表
	 */
	List<OrderItem> loadOrderItemsByOrderIdForPageLit(String orderId, PageInfo pageInfo);

}
