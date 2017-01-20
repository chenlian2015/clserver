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
 * 店铺-商品信息
 * 
 * @author huxg
 */
@Entity
@Table(name = "T_SHOP_GOODS")
public class ShopGoods extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 店铺编号
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 店铺名称
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 商品名称
	@Column(name = "C_NAME")
	String name;

	// 编号
	@Column(name = "C_NUM")
	String num;

	// 原价格
	@Column(name = "N_ORIGINAL_FEE")
	BigDecimal orginalFee;

	// 实际价格
	@Column(name = "N_REAL_FEE")
	BigDecimal realFee;

	// 商品分类ID
	@Column(name = "C_CATEGORY_ID")
	String categoryId;

	// 商品分类名称
	@Column(name = "C_CATEGORY_NAME")
	String categoryName;

	// 商品简介
	@Column(name = "C_INTRODUCTION")
	String introduction;

	// 商品详细描述
	@Column(name = "C_DESC")
	String desc;

	// 商品图片
	@Column(name = "C_PIC_MAIN")
	String picMain;

	// 商品图片1
	@Column(name = "C_PIC1")
	String pic1;

	// 商品图片2
	@Column(name = "C_PIC2")
	String pic2;

	// 商品图片3
	@Column(name = "C_PIC3")
	String pic3;

	// 商品图片4
	@Column(name = "C_PIC4")
	String pic4;

	// 服务分数
	@Column(name = "C_SERVICE_SCOPE")
	String serviceScope;

	// 所在省ID
	@Column(name = "C_PROVINCE_ID")
	String provinceId;

	// 所在市ID
	@Column(name = "C_CITY_ID")
	String cityId;

	// 所在省
	@Column(name = "C_PROVINCE_NAME")
	String provinceName;

	// 所在市
	@Column(name = "C_CITY_NAME")
	String cityName;

	// 商品服务范围 - 经度
	@Column(name = "C_LOC_LON")
	String locLon;

	// 商品服务范围 - 纬度
	@Column(name = "C_LOC_LAT")
	String locLat;

	// 手机购买该商品的二维码
	@Column(name = "C_QR_IMAGE1")
	String qrImage;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 开始时间
	@Column(name = "D_BEGIN_DATE")
	Date beginDate;

	// 结束时间
	@Column(name = "D_END_DATE")
	Date endDate;

	// 显示顺序
	@Column(name = "N_ORDER")
	Integer order;

	// 库存数量
	@Column(name = "N_AMOUNT")
	Integer amount;

	// 总销量
	@Column(name = "N_SALES_COUNT")
	Integer salesCount;

	// 商品类型，1-产品，2-服务，3-其他
	@Column(name = "N_TYPE")
	Integer type;

	// 上架状态 0-未上架， 1-已上架
	@Column(name = "N_STATUS_SHELF")
	Integer statusShelf;

	// 审核状态 0-未审核， 1-审核通过
	@Column(name = "N_STATUS_AUDIT")
	Integer statusAudit;

	// 销售状态 0-原价销售， 1-折扣中， 2-促销中
	@Column(name = "N_STATUS_SALE")
	Integer statusSale;

	// 删除状态， 1为已删除
	@Column(name = "N_STATUS_DELETE")
	Integer statusDelete;

	// 删除状态， 0 - 未推荐， 1-已推荐
	@Column(name = "N_IS_RECOMMEND")
	Integer isRecommend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPicMain() {
		return picMain;
	}

	public void setPicMain(String picMain) {
		this.picMain = picMain;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {
		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStatusShelf() {
		return statusShelf;
	}

	public void setStatusShelf(Integer statusShelf) {
		this.statusShelf = statusShelf;
	}

	public Integer getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(Integer statusAudit) {
		this.statusAudit = statusAudit;
	}

	public Integer getStatusSale() {
		return statusSale;
	}

	public void setStatusSale(Integer statusSale) {
		this.statusSale = statusSale;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getServiceScope() {
		return serviceScope;
	}

	public void setServiceScope(String serviceScope) {
		this.serviceScope = serviceScope;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLocLon() {
		return locLon;
	}

	public void setLocLon(String locLon) {
		this.locLon = locLon;
	}

	public String getLocLat() {
		return locLat;
	}

	public void setLocLat(String locLat) {
		this.locLat = locLat;
	}

	public Integer getStatusDelete() {
		return statusDelete;
	}

	public void setStatusDelete(Integer statusDelete) {
		this.statusDelete = statusDelete;
	}

	public String getQrImage() {
		return qrImage;
	}

	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
