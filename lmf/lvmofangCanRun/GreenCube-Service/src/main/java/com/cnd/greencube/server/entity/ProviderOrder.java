/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 供应商订单明细表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_PROVIDER_ORDER")
public class ProviderOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 订单ID
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 供应商ID
	@Column(name = "C_PROVIDER_ID")
	String providerId;

	// 供货商提供的商品的ID
	@Column(name = "C_PROVIDER_GOODS_ID")
	String providerGoodsId;

	// 供货商提供的商品的名称
	@Column(name = "C_PROVIDER_GOODS_NAME")
	String providerGoodsName;

	// 店铺ID
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 店铺名称
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 店家手机号
	@Column(name = "C_SHOP_PHONE")
	String shopPhone;

	// 店家手机号
	@Column(name = "C_SHOP_GOODS_ID")
	String shopGoodsId;

	// 用户ID
	@Column(name = "C_BUYER_ID")
	String buyerId;

	// 购买者姓名
	@Column(name = "C_BUYER_NAME")
	String buyerName;

	// 订单单号
	@Column(name = "C_ORDER_NUM")
	String orderNum;

	// 供产会为该商品提供的原价格
	@Column(name = "N_ORGINAL_FEE")
	BigDecimal orginalFee;

	// 供产会为该商品提供的实际价格
	@Column(name = "N_REAL_FEE")
	BigDecimal realFee;

	// 购买数量
	@Column(name = "N_COUNT")
	Integer count;

	// 总价
	@Column(name = "N_TOTAL_FEE")
	BigDecimal totalFee;

	// 下订单时间
	@Column(name = "D_ORDER_TIME")
	Date orderTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderGoodsId() {
		return providerGoodsId;
	}

	public void setProviderGoodsId(String providerGoodsId) {
		this.providerGoodsId = providerGoodsId;
	}

	public String getProviderGoodsName() {
		return providerGoodsName;
	}

	public void setProviderGoodsName(String providerGoodsName) {
		this.providerGoodsName = providerGoodsName;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
}
