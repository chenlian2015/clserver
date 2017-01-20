/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.cnd.greencube.server.business.OrderBusiness;
import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.entity.OrderInvoice;
import com.cnd.greencube.server.entity.OrderItem;
import com.cnd.greencube.server.entity.ProductOrder;
import com.cnd.greencube.server.entity.ShopOrder;
import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.OrderService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
/**
 * 订单服务实现类
 * 
 * @author huxg
 * 
 */
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService.Iface {
	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Resource(name = "OrderBusinessImpl")
	protected OrderBusiness orderBusiness;

	/**
	 * 取得我的订单
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param filter
	 *            -- 订单类型
	 * @param pageNum
	 *            -- 页数
	 */
	@Override
	public String loadBuierOrders(String userid, String filter, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Order> orders = orderBusiness.loadBuierOrders(userid, filter, pageInfo);
			return Message.okMessage(orders, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得一个订单的详细信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单的json数据
	 */
	@Override
	public String getBuierOrder(String orderid) throws TException {
		try {
			Order order = orderBusiness.getBuierOrder(orderid);
			return Message.okMessage(order);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除订单
	 * 
	 * @param -- 订单id
	 * @return 成功与否标志
	 */
	@Override
	public String deleteBuierOrder(String orderid) throws TException {
		try {
			orderBusiness.deleteBuierOrder(orderid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

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
	 */
	@Override
	public String createOrder(String userid, String shoppingItemsJson, String couponId, String receiverId) throws TException {
		try {
			List<ShoppingItem> items = JsonUtils.String2List(shoppingItemsJson, ShoppingItem.class);
			orderBusiness.createOrder(userid, items, couponId, receiverId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 提交发票申请
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @param invoiceJson
	 *            -- 订单json数据
	 * @return -- 成功与否标志
	 */
	@Override
	public String submitOrderInvoiceApply(String orderid, String invoiceJson) throws TException {
		try {
			OrderInvoice orderInvoice = (OrderInvoice) JSON.parseObject(invoiceJson, OrderInvoice.class);
			if (orderInvoice.getType() == null || orderInvoice.getType() <= 0) {
				return Message.errorMessage("发票申请有误，必须指定orderInvoice.type（发票类型）");
			}
			if (orderInvoice.getTitleType() == null || orderInvoice.getType() <= 0) {
				return Message.errorMessage("发票申请有误，必须指定orderInvoice.titleType（发票抬头类型）");
			}
			if (StringUtils.isEmpty(orderInvoice.getTitle())) {
				return Message.errorMessage("发票申请有误，必须指定orderInvoice.titleType（发票抬头）");
			}
			if (StringUtils.isEmpty(orderInvoice.getOrderId())) {
				return Message.errorMessage("发票申请有误，必须指定orderInvoice.orderid（订单id）");
			}

			orderBusiness.submitOrderInvoiceApply(orderid, orderInvoice);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Override
	public String auditOrderInvoice(String invoiceId, String userid) {
		try {
			orderBusiness.auditOrderInvoice(invoiceId, userid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Override
	public String unAuditOrderInvoice(String invoiceId, String userid) {
		try {
			orderBusiness.unAuditOrderInvoice(invoiceId, userid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Override
	public String publishOrderInvoice(String invoiceId, String publisherId) {
		try {
			orderBusiness.publishOrderInvoice(invoiceId, publisherId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 不发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Override
	public String unPublishOrderInvoice(String invoiceId, String publisherId) {
		try {
			orderBusiness.unPublishOrderInvoice(invoiceId, publisherId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得订单的发票信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单发票json数据
	 */
	@Override
	public String getOrderInvoice(String orderid) throws TException {
		try {
			return Message.okMessage(orderBusiness.getOrderInvoice(orderid));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改订单发票信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @param invoiceJson
	 *            -- 发票json数据
	 * @return 成功与否标志
	 */
	@Override
	public String updateOrderInvoice(String orderid, String invoiceJson) throws TException {
		return submitOrderInvoiceApply(orderid, invoiceJson);
	}

	/**
	 * 取得一个订单的快递信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回订单的快递信息json数据
	 */
	@Override
	public String getOrderMailing(String orderid) throws TException {
		try {
			orderBusiness.getOrderMailing(orderid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得店铺订单
	 * 
	 * @param shopid
	 *            -- 店铺id
	 * @param pageNum
	 *            -- 页数
	 * @return 返回店铺订单列表
	 */
	@Override
	public String loadShopOrdersForPagelit(String shopid, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopOrder> orders = orderBusiness.loadShopOrdersForPagelit(shopid, pageInfo);
			return Message.okMessage(orders, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得店铺订单
	 * 
	 * @param shopOrderId
	 * @return 返回店铺订单json对象
	 */
	@Override
	public String getShopOrder(String shopOrderId) throws TException {
		try {
			return Message.okMessage(orderBusiness.getShopOrder(shopOrderId));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 返回已申请的发票列表
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 */
	@Override
	public String loadApplyOrderInvoice(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<OrderInvoice> invoices = orderBusiness.loadApplyOrderInvoice(pageInfo);
			return Message.okMessage(invoices, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 返回已完结的发票
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 */
	@Override
	public String loadPublishedOrderInvoice(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<OrderInvoice> invoices = orderBusiness.loadPublishedOrderInvoice(pageInfo);
			return Message.okMessage(invoices, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除订单
	 * 
	 * @param shopOrderId
	 *            -- 店铺订单id
	 * @return 返回成功标志
	 */
	@Override
	public String deleteShopOrder(String shopOrderId) throws TException {
		try {
			orderBusiness.deleteShopOrder(shopOrderId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得供应商订单列表
	 * 
	 * @param userId
	 *            -- 供应商id
	 * @param pageNum
	 *            -- 页数
	 * @return 返回供应商订单json数组
	 */
	@Override
	public String loadProductOrdersForPagelit(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ProductOrder> orders = orderBusiness.loadProductOrdersForPagelit(userId, pageInfo);
			return Message.okMessage(orders, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得供应商订单
	 * 
	 * @param productorderId
	 *            -- 供应商id
	 * @return 返回供应商订单json对象
	 */
	@Override
	public String getProductOrder(String productOrderId) throws TException {
		try {
			return Message.okMessage(orderBusiness.getProductOrder(productOrderId));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除供应商订单
	 * 
	 * @param productOrderId
	 *            -- 供应商订单id
	 * @return 返回成功与否标志
	 */
	@Override
	public String deleteProductOrder(String productOrderId) throws TException {
		try {
			orderBusiness.deleteProductOrder(productOrderId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
  /**
   * 依据订单号取得订单列表（若订单号为空，则取得所有订单）
   * @param orderId 订单号
   * @param pageNum 页数
   */
	@Override
	public String searchOrdersByOrderNum(String orderNum, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Order> orders = orderBusiness.searchOrdersByOrderNum(orderNum, pageInfo);
			return Message.okMessage(orders, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
	/**
	 * 依据订单id得到订单条目（店主会商品列表）
	 * @param orderId
	 * @return 订单商品列表
	 */
@Override
public String loadOrderItemsByOrderId(String orderId) throws TException {
	try {
		List<OrderItem> orderItems = orderBusiness.loadOrderItemsByOrderId(orderId);
		return Message.okMessage(orderItems);
	} catch (Exception e) {
		logger.error(e);
		return Message.error();
	}
}
/**
 * 依据订单id分页得到订单条目（店主会商品列表）
 * @param orderId
 * @return 订单商品列表
 */
@Override
public String loadOrderItemsByOrderIdForPageLit(String orderId, int pageNum) throws TException {
	try {
		PageInfo pageInfo = initPageInfo(pageNum);
		List<OrderItem> orders = orderBusiness.loadOrderItemsByOrderIdForPageLit(orderId, pageInfo);
		return Message.okMessage(orders, pageInfo);
	} catch (Exception e) {
		logger.error(e);
		return Message.error();
	}
}
	

}