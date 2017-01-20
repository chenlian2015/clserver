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
 * 店铺
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_SHOP")
public class Shop extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 用户编号
	@Column(name = "C_USER_ID")
	String userid;

	// 店铺名称
	@Column(name = "C_SHOP_NAME")
	String shopName;

	// 店铺首图
	@Column(name = "C_PHOTO")
	String photo;

	// 店铺编码
	@Column(name = "C_MEMBER_CODE")
	String memberCode;

	// 所在省id
	@Column(name = "C_PROVINCE_ID")
	String provinceId;

	// 所在省名称
	@Column(name = "C_PROVINCE_NAME")
	String provinceName;

	// 所在市id
	@Column(name = "C_CITY_ID")
	String cityId;

	// 所在市名称
	@Column(name = "C_CITY_NAME")
	String cityName;

	// 定位-精度
	@Column(name = "C_LOC_LON")
	String locLon;

	// 定位-纬度
	@Column(name = "C_LOC_LAT")
	String locLat;

	// 店铺有效时间-起，当店铺缴费并开店后，该字段用于表示起止时间
	@Column(name = "D_VALID_BEGIN")
	Date validBegin;

	// 店铺有效时间-止，当店铺缴费并开店后，该字段用于表示起止时间
	@Column(name = "D_VALID_END")
	Date validEnd;

	// 是否支付了管理费
	@Column(name = "N_IS_PAID_MANAGER_FEE")
	Integer paidManagerFee;

	// 店铺电话
	@Column(name = "C_SHOP_PHONE")
	String shopPhone;

	// 业务分类ID
	@Column(name = "C_BUSINESS_CATEGORY_ID")
	String businessCategoryId;

	// 业务分类名称
	@Column(name = "C_BUSINESS_CATEGORY_NAME")
	String businessCategoryName;

	// 店铺评级，或者称为店铺星级，5星，5个级别，反馈前端表示几个星星
	@Column(name = "N_LEVEL")
	Integer level;

	// 关注的人
	@Column(name = "C_ATTENTION_MEMBERS")
	String attentionMembers;

	// 待售商品总数目
	@Column(name = "N_GOODS_AMOUNT_ON_SALE")
	Integer goodsAmountOnSale;

	// 总销售数
	@Column(name = "N_SALES_AMOUNT")
	Integer salesAmount;

	// 总赞数
	@Column(name = "N_PRAISE_AMOUNT")
	Integer praiseAmount;

	// 总粉丝数
	@Column(name = "N_FANS_AMOUNT")
	Integer fansAmount;

	// 总关注数
	@Column(name = "N_FOCUS_AMOUNT")
	Integer focusAmount;

	// 评价次数
	@Column(name = "N_COMMENT_AMOUNT")
	Integer commentAmount;

	// 店铺总评分
	@Column(name = "N_COMMENT_SCORE")
	Integer commentScore;

	// 累计消费金额
	@Column(name = "N_TOTAL_CONSUME")
	BigDecimal totalConsume;

	// 是否通过审核
	@Column(name = "N_IS_AUDIT")
	Integer isAudit;

	// 是否有效，店铺到达有效期并未续费时，为无效状态
	@Column(name = "N_IS_VALID")
	Integer isValid;

	// 是否上架、是否在前端显示，本字段仅用于调试器使用，上线后是没有用处的。
	@Column(name = "N_IS_FRONT_SHOW")
	Integer isFrontShow;

	// 是否推荐店铺
	@Column(name = "N_IS_RECOMMEND")
	Integer isRecommend;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void setBusinessCategoryId(String businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public String getBusinessCategoryName() {
		return businessCategoryName;
	}

	public void setBusinessCategoryName(String businessCategoryName) {
		this.businessCategoryName = businessCategoryName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getAttentionMembers() {
		return attentionMembers;
	}

	public void setAttentionMembers(String attentionMembers) {
		this.attentionMembers = attentionMembers;
	}

	public Integer getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Integer getPraiseAmount() {
		return praiseAmount;
	}

	public void setPraiseAmount(Integer praiseAmount) {
		this.praiseAmount = praiseAmount;
	}

	public Integer getFansAmount() {
		return fansAmount;
	}

	public void setFansAmount(Integer fansAmount) {
		this.fansAmount = fansAmount;
	}

	public Integer getFocusAmount() {
		return focusAmount;
	}

	public void setFocusAmount(Integer focusAmount) {
		this.focusAmount = focusAmount;
	}

	public Integer getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}

	public Integer getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(Integer commentScore) {
		this.commentScore = commentScore;
	}

	public BigDecimal getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(BigDecimal totalConsume) {
		this.totalConsume = totalConsume;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsFrontShow() {
		return isFrontShow;
	}

	public void setIsFrontShow(Integer isFrontShow) {
		this.isFrontShow = isFrontShow;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getGoodsAmountOnSale() {
		return goodsAmountOnSale;
	}

	public void setGoodsAmountOnSale(Integer goodsAmountOnSale) {
		this.goodsAmountOnSale = goodsAmountOnSale;
	}

	public Date getValidBegin() {
		return validBegin;
	}

	public void setValidBegin(Date validBegin) {
		this.validBegin = validBegin;
	}

	public Date getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	public Integer getPaidManagerFee() {
		return paidManagerFee;
	}

	public void setPaidManagerFee(Integer paidManagerFee) {
		this.paidManagerFee = paidManagerFee;
	}
}
