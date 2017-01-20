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
 * 店铺-商品信息-细目
 * 
 * @author huxg
 */
@Entity
@Table(name = "T_SHOP_GOODS_ITEM")
public class ShopGoodsItem extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 店铺ID
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 商品ID
	@Column(name = "C_SHOP_GOODS_ID")
	String shopGoodsId;

	// 店铺商品名称
	@Column(name = "C_SHOP_GOODS_NAME")
	String shopGoodsName;

	// 店铺设定的商品原价格
	@Column(name = "N_SHOP_ORIGINAL_FEE")
	BigDecimal shopOrginalFee;

	// 店铺设定的商品实际价格
	@Column(name = "N_SHOP_REAL_FEE")
	BigDecimal shopRealFee;

	// 供应商提供的商品ID
	@Column(name = "C_PROVIDER_GOODS_ID")
	String providerGoodsId;

	// 供应商提供的商品名称
	@Column(name = "C_PROVIDER_GOODS_NAME")
	String providerGoodsName;

	// 供应商提供的商品图片
	@Column(name = "C_PROVIDER_GOODS_IMAGE")
	String providerGoodsImage;

	// 供应商设定的商品原价格
	@Column(name = "N_PROVIDER_ORIGINAL_FEE")
	BigDecimal providerOrginalFee;

	// 供应商设定的商品实际价格
	@Column(name = "N_PROVIDER_REAL_FEE")
	BigDecimal providerRealFee;

	// 商品类型，1-产品，2-服务
	@Column(name = "N_TYPE")
	Integer type;

	// 排序
	@Column(name = "N_ORDER")
	Integer order;

	// 是否已被删除
	@Column(name = "N_IS_DELETE")
	Integer isDelete;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
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

	public String getProviderGoodsImage() {
		return providerGoodsImage;
	}

	public void setProviderGoodsImage(String providerGoodsImage) {
		this.providerGoodsImage = providerGoodsImage;
	}

	public BigDecimal getProviderOrginalFee() {
		return providerOrginalFee;
	}

	public void setProviderOrginalFee(BigDecimal providerOrginalFee) {
		this.providerOrginalFee = providerOrginalFee;
	}

	public BigDecimal getProviderRealFee() {
		return providerRealFee;
	}

	public void setProviderRealFee(BigDecimal providerRealFee) {
		this.providerRealFee = providerRealFee;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
