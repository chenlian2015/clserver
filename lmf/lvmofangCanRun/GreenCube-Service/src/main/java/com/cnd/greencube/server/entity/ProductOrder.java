/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 产品的订单表
 * 
 * @author huxg update by dongyl
 */
@Entity
@Table(name = "T_PRODUCT_ORDER")
public class ProductOrder extends BaseEntity {
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

	// 店铺id
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 店铺的名称
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 销售店铺的电话
	@Column(name = "C_SHOP_PHONE")
	String shopPhone;

	// 销售店铺的商品id
	@Column(name = "C_SHOP_GOODS_ID")
	String shopGoodsId;

	// 购买者ID
	@Column(name = "C_BUYER_ID")
	String buyerId;

	// 购买者姓名
	@Column(name = "C_BUYER_NAME")
	String buyerName;

	// 订单号
	@Column(name = "C_ORDER_NUM")
	String orderNum;

	// 商品原价
	@Column(name = "N_ORGINAL_FEE")
	Integer orginalFee;

	// 商品实际价格
	@Column(name = "N_REAL_FEE")
	Integer realFee;

	// 下订单时间
	@Column(name = "D_ORDER_TIME")
	Date orderTime;

	// 数量
	@Column(name = "N_COUNT")
	Integer count;

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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopGoodsId() {
		return shopGoodsId;
	}

	public void setShopGoodsId(String shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOrginalFee() {
		return orginalFee;
	}

	public void setOrginalFee(Integer orginalFee) {
		this.orginalFee = orginalFee;
	}

	public Integer getRealFee() {
		return realFee;
	}

	public void setRealFee(Integer realFee) {
		this.realFee = realFee;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
