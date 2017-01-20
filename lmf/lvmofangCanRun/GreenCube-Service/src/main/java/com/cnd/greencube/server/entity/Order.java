/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 订单表（买家订单表）
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_ORDER")
public class Order extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 20 - 已下单，未付款，21 - 付款中， 22 - 已完成支付，待处理
	public static final int ORDER_STATUS_YXD = 20;
	public static final int ORDER_STATUS_FKZ = 21;

	// 完成支付状态，处于待确认状态
	public static final int ORDER_STATUS_WCZF = 22;

	// 评价状态
	public static final int ORDER_FEEDBACK_DQR = 30;
	public static final int ORDER_FEEDBACK_DPJ = 40;
	public static final int ORDER_FEEDBACK_YWC = 50;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 用户名
	@Column(name = "C_USER_NAME")
	String userName;

	// 订单号
	@Column(name = "C_ORDER_NUM")
	String orderNum;

	// 订单名称
	@Column(name = "C_ORDER_NAME")
	String orderName;

	// 订单原价
	@Column(name = "N_ORGINAL_FEE")
	BigDecimal orginalFee;

	// 订单实际价格
	@Column(name = "N_REAL_FEE")
	BigDecimal realFee;

	// 手机号
	@Column(name = "C_MOBILE")
	String mobile;

	// 是否需要发票
	@Column(name = "N_NEED_INVOICE")
	Integer isNeedInvoice;

	// 下订单时间
	@Column(name = "D_ORDER_TIME")
	Date orderTime;

	// 订单备注
	@Column(name = "C_REMARK")
	String remark;

	// 订单状态 20 - 已下单，未付款，21 - 付款中， 22 - 已完成支付，待处理，30 - 待确认，40-待评价， 50，已完成
	@Column(name = "N_STATUS")
	Integer status;

	// 对于该订单使用的优惠券ID
	@Column(name = "C_COUPON_ID")
	String couponId;

	// 快递费用
	@Column(name = "N_DELIVERY_FEE")
	BigDecimal deliveryFee;

	// 优惠券优惠的金额
	@Column(name = "N_COUPON_FEE")
	BigDecimal couponFee;

	// 优惠券总金额
	@Column(name = "N_TOTAL_FEE")
	BigDecimal totalFee;

	@Transient
	List<ShopOrder> shopOrders = new ArrayList<ShopOrder>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public BigDecimal getOrginalFee() {
		return orginalFee;
	}

	public void setOrginalFee(BigDecimal orginalFee) {
		this.orginalFee = orginalFee;
	}

	public BigDecimal getRealFee() {
		return realFee;
	}

	public void setRealFee(BigDecimal realFee) {
		this.realFee = realFee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Integer isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(BigDecimal couponFee) {
		this.couponFee = couponFee;
	}

	public List<ShopOrder> getShopOrders() {
		return shopOrders;
	}

	public void setShopOrders(List<ShopOrder> shopOrders) {
		this.shopOrders = shopOrders;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

}
