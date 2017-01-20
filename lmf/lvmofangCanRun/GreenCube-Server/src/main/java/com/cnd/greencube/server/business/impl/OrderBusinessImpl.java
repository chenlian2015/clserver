/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.OrderBusiness;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.CouponDao;
import com.cnd.greencube.server.dao.OrderDao;
import com.cnd.greencube.server.dao.OrderInvoiceDao;
import com.cnd.greencube.server.dao.OrderItemDao;
import com.cnd.greencube.server.dao.OrderMailDao;
import com.cnd.greencube.server.dao.ProductOrderDao;
import com.cnd.greencube.server.dao.ShopOrderDao;
import com.cnd.greencube.server.dao.ShoppingItemDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Coupon;
import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.entity.OrderInvoice;
import com.cnd.greencube.server.entity.OrderItem;
import com.cnd.greencube.server.entity.OrderMail;
import com.cnd.greencube.server.entity.ProductOrder;
import com.cnd.greencube.server.entity.ShopOrder;
import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.CurrencyUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.uuid.Key;

/**
 * 订单服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("OrderBusinessImpl")
public class OrderBusinessImpl extends BaseBusinessImpl implements OrderBusiness {
	@Resource(name = "OrderDaoImpl")
	private OrderDao orderDao;

	@Resource(name = "AuditDaoImpl")
	private AuditDao auditDao;

	@Resource(name = "ShopOrderDaoImpl")
	private ShopOrderDao shopOrderDao;

	@Resource(name = "ProductOrderDaoImpl")
	private ProductOrderDao productOrderDao;

	@Resource(name = "OrderItemDaoImpl")
	private OrderItemDao orderItemDao;

	@Resource(name = "ShoppingItemDaoImpl")
	private ShoppingItemDao shoppingItemDao;

	@Resource(name = "CouponDaoImpl")
	private CouponDao couponDao;

	@Resource(name = "OrderMailDaoImpl")
	private OrderMailDao orderMailDao;

	@Resource(name = "OrderInvoiceDaoImpl")
	private OrderInvoiceDao orderInvoiceDao;

	@Resource(name = "UserDaoImpl")
	protected UserDao userDao;

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
	public List<Order> loadBuierOrders(String userid, String filter, PageInfo pageInfo) {
		return orderDao.getUserOrders(userid, filter, pageInfo);
	}

	/**
	 * 取得一个订单的详细信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单的json数据
	 */
	@Override
	public Order getBuierOrder(String orderid) {
		return orderDao.get(orderid);
	}

	/**
	 * 删除订单
	 * 
	 * @param --
	 *            订单id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean deleteBuierOrder(String orderid) {
		orderDao.delete(orderid);
		return true;
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
	@Transactional
	@Override
	public boolean createOrder(String userid, List<ShoppingItem> shoppingItems, String couponId, String receiverId) {
		Order order = new Order();
		order.setUserId(userid);

		User user = userDao.get(userid);
		if (user != null) {
			order.setUserName(user.getName());
			order.setMobile(user.getMobile());
		}

		order.setOrderNum(OrderUtils.generateOrderNum());
		order.setIsNeedInvoice(0);
		order.setOrderTime(new Date());
		order.setStatus(Order.ORDER_STATUS_YXD);
		orderDao.save(order);

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		// 设置订单子表
		// 订单-商品信息
		// 原始金额
		BigDecimal total = new BigDecimal(0);

		StringBuffer orderName = new StringBuffer();

		if (shoppingItems != null) {
			// 保存订单商品表
			OrderItem item = null;
			for (ShoppingItem cartItem : shoppingItems) {
				item = new OrderItem();
				item.setOrderId(order.getId());
				item.setUserId(order.getUserId());
				item.setShopGoodsId(cartItem.getGoodsId());
				item.setShopGoodsName(cartItem.getGoodsName());
				item.setShopId(cartItem.getShopId());
				item.setShopName(cartItem.getShopName());
				item.setShopOrginalFee(cartItem.getOrginalPrice());
				item.setShopRealFee(cartItem.getRealPrice());
				item.setCount(cartItem.getCount());

				orderName.append(cartItem.getGoodsName() + ",");
				BigDecimal b = item.getShopRealFee();

				if (item.getCount() != null && item.getCount().intValue() > 0) {
					b = CurrencyUtils.cheng(b, item.getCount());
				}

				total = CurrencyUtils.jia(total, b);

				// 设置小计
				item.setTotal(b);
				orderItemDao.save(item);
				orderItems.add(item);

				// 清除掉购物车中的商品
				shoppingItemDao.delete(cartItem.getId());
			}
		}

		order.setTotalFee(total);

		// 计算优惠券
		if (!StringUtils.isEmpty(couponId)) {
			Coupon card = couponDao.get(couponId);
			BigDecimal kyhje = OrderUtils.computeCoupon(card, orderItems);
			order.setCouponFee(kyhje);

			// 将优惠券设置冻结
			order.setCouponId(card.getId());
		}

		// 设置订单名称
		order.setOrderName(orderName.toString());
		if (order.getOrderName().length() > 120) {
			order.setOrderName(orderName.substring(0, 120) + "...");
		}

		orderDao.update(order);
		return true;
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
	@Transactional
	@Override
	public boolean submitOrderInvoiceApply(String orderid, OrderInvoice orderInvoice) {
		if (StringUtils.isEmpty(orderInvoice.getId())) {
			orderInvoice.setId(null);
			orderInvoice.setApplyTime(new Date());
			orderInvoice.setStatusDeal(1);
			orderInvoiceDao.save(orderInvoice);
		} else {
			orderInvoiceDao.update(orderInvoice);
		}
		return true;

	}

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Transactional
	@Override
	public boolean auditOrderInvoice(String invoiceId, String userid) {
		OrderInvoice invoice = orderInvoiceDao.get(invoiceId);
		invoice.setStatusDeal(2);
		orderInvoiceDao.update(invoice);
		auditDao.audit(invoiceId, OrderInvoice.class, userid, 2);
		return true;
	}

	/**
	 * 审核发票申请信息
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Transactional
	@Override
	public boolean unAuditOrderInvoice(String invoiceId, String userid) {
		OrderInvoice invoice = orderInvoiceDao.get(invoiceId);
		invoice.setStatusDeal(1);
		orderInvoiceDao.update(invoice);
		auditDao.audit(invoiceId, OrderInvoice.class, userid, 1);
		return true;
	}

	/**
	 * 发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Transactional
	@Override
	public boolean publishOrderInvoice(String invoiceId, String publisherId) {
		OrderInvoice invoice = orderInvoiceDao.get(invoiceId);
		invoice.setStatusDeal(4);
		orderInvoiceDao.update(invoice);
		auditDao.audit(invoiceId, OrderInvoice.class, publisherId, 4);
		return true;
	}

	/**
	 * 发布发票
	 * 
	 * @param invoiceId
	 *            -- 发票id
	 * @return -- 成功与否标志
	 */
	@Transactional
	@Override
	public boolean unPublishOrderInvoice(String invoiceId, String publisherId) {
		OrderInvoice invoice = orderInvoiceDao.get(invoiceId);
		invoice.setStatusDeal(1);
		orderInvoiceDao.update(invoice);

		auditDao.audit(invoiceId, OrderInvoice.class, publisherId, 1);
		return true;
	}

	/**
	 * 取得订单的发票信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 订单发票json数据
	 */
	@Override
	public OrderInvoice getOrderInvoice(String orderid) {
		return orderInvoiceDao.getOrderInvoice(orderid);
	}

	/**
	 * 取得一个订单的快递信息
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回订单的快递信息json数据
	 */
	@Override
	public OrderMail getOrderMailing(String orderid) {
		return orderMailDao.getOrderMailing(orderid);
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
	public List<ShopOrder> loadShopOrdersForPagelit(String shopid, PageInfo pageInfo) {
		String sql = "select t.id from ShopOrder t where t.shopId = ? order by t.orderTime desc";
		String ctql = "select t.id from ShopOrder t where t.shopId = ? order by t.orderTime desc";
		return shopOrderDao.findList(sql, ctql, new Object[] { shopid }, pageInfo);
	}

	/**
	 * 取得店铺订单
	 * 
	 * @param shopOrderId
	 * @return 返回店铺订单json对象
	 */
	@Override
	public ShopOrder getShopOrder(String shopOrderId) {
		return shopOrderDao.get(shopOrderId);
	}

	/**
	 * 返回已申请的发票列表
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 */
	@Override
	public List<OrderInvoice> loadApplyOrderInvoice(PageInfo pageInfo) {
		String sql = "select t.id from OrderInvoice t where t.statusDeal = 1 order by applyTime desc";
		String ctql = "select count(t) from OrderInvoice t where t.statusDeal = 1";
		return orderInvoiceDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 返回已完结的发票
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 订单发票json数组
	 */
	@Override
	public List<OrderInvoice> loadPublishedOrderInvoice(PageInfo pageInfo) {
		String sql = "select t.id from OrderInvoice t where t.statusDeal = 4 order by applyTime desc";
		String ctql = "select count(t) from OrderInvoice t where t.statusDeal = 4";
		return orderInvoiceDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 删除订单
	 * 
	 * @param shopOrderId
	 *            -- 店铺订单id
	 * @return 返回成功标志
	 */
	@Transactional
	@Override
	public boolean deleteShopOrder(String shopOrderId) {
		shopOrderDao.delete(shopOrderId);
		return true;
	}

	/**
	 * 取得供应商订单列表
	 * 
	 * @param productId
	 *            -- 供应商id
	 * @param pageNum
	 *            -- 页数
	 * @return 返回供应商订单json数组
	 */
	@Override
	public List<ProductOrder> loadProductOrdersForPagelit(String shopOrdeId, PageInfo pageInfo) {
		String sql = "select t.id from ProductOrder t where t.userId = ? order by t.orderTime desc";
		String ctql = "select t.id from ProductOrder t where t.userId = ? order";
		return productOrderDao.findList(sql, ctql, new Object[] { shopOrdeId }, pageInfo);
	}

	/**
	 * 取得供应商订单
	 * 
	 * @param productOrderId
	 *            -- 供应商id
	 * @return 返回供应商订单json对象
	 */
	@Override
	public ProductOrder getProductOrder(String productOrderId) {
		return productOrderDao.get(productOrderId);
	}

	/**
	 * 删除供应商订单
	 * 
	 * @param productOrderId
	 *            -- 供应商订单id
	 * @return 返回成功与否标志
	 */
	@Transactional
	@Override
	public boolean deleteProductOrder(String productOrderId) {
		productOrderDao.delete(productOrderId);
		return true;
	}

	/**
	 * 依据订单号查询订单列表，如果订单号为空，则查询所有
	 * 
	 * @param orderId
	 */
	@Override
	public List<Order> searchOrdersByOrderNum(String orderNum, PageInfo pageInfo) {
		return orderDao.searchOrdersByOrderNum(orderNum, pageInfo);
	}

	@Override
	public List<OrderItem> loadOrderItemsByOrderId(String orderId) {
		String sql = "select t.id from OrderItem t where t.orderId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		return orderItemDao.findList(sql, params.toArray());
	}

	@Override
	public List<OrderItem> loadOrderItemsByOrderIdForPageLit(String orderId, PageInfo pageInfo) {
		String sql = "select t.id from OrderItem t where t.orderId = ?";
		String ctql = "select t.id from OrderItem t where t.orderId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(orderId);
		return orderItemDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

}

class OrderUtils {
	/**
	 * 生成一个订单号
	 * 
	 * @return
	 */
	public static final String generateOrderNum() {
		long time = System.currentTimeMillis();
		String key = Key.key();

		Random random = new Random();
		int r1 = random.nextInt(31);
		int r2 = random.nextInt(31);
		int r3 = random.nextInt(31);

		String str1 = String.valueOf(time).substring(4);
		str1 = str1 + key.charAt(r1) + key.charAt(r2) + key.charAt(r3);

		return str1;
	}

	/**
	 * 计算可以优惠的金额
	 * 
	 * @return 可优惠的金额
	 */
	public static BigDecimal computeCoupon(Coupon card, List<OrderItem> items) {
		BigDecimal zero = new BigDecimal(0);
		if (card == null)
			return zero;

		// 如果当前总额大于优惠券规定的总额
		if (card.getValue() <= 0) {
			return zero;
		} else {
			return new BigDecimal(card.getValue());
		}
	}
}
