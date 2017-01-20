/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户订单-订单细目
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItem extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 订单ID
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 商家ID
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 商家名称
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 商品id
	@Column(name = "C_SHOP_GOODS_ID")
	String shopGoodsId;

	// 商品名称
	@Column(name = "C_SHOP_GOODS_NAME")
	String shopGoodsName;

	// 供应商提供的商品的ID
	@Column(name = "C_PROVIDER_GOODS_ID")
	String providerGoodsId;

	// 供应商提供的商品的名称
	@Column(name = "C_PROVIDER_GOODS_NAME")
	String providerGoodsName;

	// 店主提供的商品原价
	@Column(name = "N_SHOP_ORGINAL_FEE")
	BigDecimal shopOrginalFee;

	// 店主提供的商品的实际价格
	@Column(name = "N_SHOP_REAL_FEE")
	BigDecimal shopRealFee;

	// 商品类型， 1-产品， 2-服务
	@Column(name = "N_TYPE")
	String type;

	// 数量
	@Column(name = "N_COUNT")
	Integer count;

	// 小计
	@Column(name = "N_TOTAL")
	BigDecimal total;

	// 备注信息
	@Column(name = "C_REMARK")
	String remark;

	// 商品图片
	@Transient
	String goodsPic;
   //店主会商品
	@Transient
	ShopGoods goods;
   //订单
	@Transient
	Order order;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getShopGoodsId() {
		return shopGoodsId;
	}

	public void setShopGoodsId(String shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
	}

	public String getShopGoodsName() {
		return shopGoodsName;
	}

	public void setShopGoodsName(String shopGoodsName) {
		this.shopGoodsName = shopGoodsName;
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

	public BigDecimal getShopOrginalFee() {
		return shopOrginalFee;
	}

	public void setShopOrginalFee(BigDecimal shopOrginalFee) {
		this.shopOrginalFee = shopOrginalFee;
	}

	public BigDecimal getShopRealFee() {
		return shopRealFee;
	}

	public void setShopRealFee(BigDecimal shopRealFee) {
		this.shopRealFee = shopRealFee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	public ShopGoods getGoods() {
		return goods;
	}

	public void setGoods(ShopGoods goods) {
		this.goods = goods;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
