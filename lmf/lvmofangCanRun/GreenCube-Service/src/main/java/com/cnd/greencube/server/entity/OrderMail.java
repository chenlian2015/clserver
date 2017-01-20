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
 * 用户订单-快递信息
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_ORDER_MAIL")
public class OrderMail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 订单编号
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 省名称
	@Column(name = "C_PROVINCE_NAME")
	String provinceName;

	// 所属市名称
	@Column(name = "C_CITY_NAME")
	String cityName;

	// 地址
	@Column(name = "C_ADDRESS")
	String address;

	// 邮编
	@Column(name = "N_POST_CODE")
	Integer postCode;

	// 电话
	@Column(name = "C_MOBILE")
	String mobile;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
