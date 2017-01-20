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

/**
 * 购物车表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_SHOPPING_ITEM")
public class ShoppingItem extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final int TYPE_PRODUCT = 1;
	public static final int TYPE_SERVICE = 2;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 店铺id
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 店铺id
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 商品id
	@Column(name = "C_GOODS_ID")
	String goodsId;

	// 商品名称
	@Column(name = "C_GOODS_NAME")
	String goodsName;

	@Column(name = "N_ORGINAL_PRICE")
	BigDecimal orginalPrice;

	// 价格
	@Column(name = "N_REAL_PRICE")
	BigDecimal realPrice;

	// 商品分类
	@Column(name = "C_CATEGORY_ID")
	String categoryId;

	// 商品类型 TYPE_PRODUCT-产品， TYPE_SERVICE-服务
	@Column(name = "N_GOODS_TYPE")
	Integer goodsType;

	// 数量
	@Column(name = "N_COUNT")
	Integer count;

	// 小计
	@Column(name = "N_TOTAL")
	BigDecimal total;

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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getOrginalPrice() {
		return orginalPrice;
	}

	public void setOrginalPrice(BigDecimal orginalPrice) {
		this.orginalPrice = orginalPrice;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
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
}
