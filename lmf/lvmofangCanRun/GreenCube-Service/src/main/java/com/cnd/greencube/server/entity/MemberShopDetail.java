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
 * 店铺成员-详细信息
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_MEMBER_SHOP_DETAIL")
public class MemberShopDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 店铺ID
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 姓名
	@Column(name = "C_OWNER_NAME")
	String ownerName;

	// 性别
	@Column(name = "N_OWNER_SEX")
	Integer ownerSex;

	// 银行类别ID
	@Column(name = "C_BANK_TYPE_ID")
	String bankTypeId;

	// 银行类别名称
	@Column(name = "C_BANK_TYPE_NAME")
	String bankTypeName;

	// 银行名称
	@Column(name = "C_BANK_NAME")
	String bankName;

	// 银行名称
	@Column(name = "C_CARD_HOLDER_NAME")
	String cardHolderName;

	// 银行名称
	@Column(name = "C_CARD_NUM")
	String cardNum;

	// 银行名称
	@Column(name = "C_CARD_HOLDER_PHONE")
	String cardHolderPhone;

	// 银行名称
	@Column(name = "C_ADDRESS")
	String address;

	// 邮编
	@Column(name = "N_ZIP_CODE")
	Integer zipCode;

	// 所在市
	@Column(name = "C_IDENTITY_TYPE")
	String identityType;

	// 证件号码
	@Column(name = "C_IDENTITY_NUM")
	String identityNum;

	// 证件号码
	@Column(name = "D_REGIST_TIME")
	Date registTime;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getOwnerSex() {
		return ownerSex;
	}

	public void setOwnerSex(Integer ownerSex) {
		this.ownerSex = ownerSex;
	}

	public String getBankTypeId() {
		return bankTypeId;
	}

	public void setBankTypeId(String bankTypeId) {
		this.bankTypeId = bankTypeId;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardHolderPhone() {
		return cardHolderPhone;
	}

	public void setCardHolderPhone(String cardHolderPhone) {
		this.cardHolderPhone = cardHolderPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

}
