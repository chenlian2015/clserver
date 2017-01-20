/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户快递信息
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_USER_ADDRESS_BOOK")
public class UserAddressBook extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 收货人姓名
	@Column(name = "C_RECEIVER_NAME")
	String receiverName;

	// 所在省
	@Column(name = "C_LOC_PROVINCE_ID")
	String provinceId;

	// 所在市
	@Column(name = "C_LOC_CITY_ID")
	String cityId;

	// 所在省名称
	@Column(name = "C_LOC_PROVINCE")
	String provinceName;

	// 所在市名称
	@Column(name = "C_LOC_CITY")
	String cityName;

	// 手机号码
	@Column(name = "C_MOBILE")
	String mobile;

	// 电话
	@Column(name = "C_PHONE")
	String phone;

	// 用户地址
	@Column(name = "C_ADDRESS")
	String address;

	// 邮编
	@Column(name = "N_ZIPCODE")
	Integer zipCode;

	// 是否默认
	@Column(name = "N_ISDEFAULT")
	Integer isDefault;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	
}
