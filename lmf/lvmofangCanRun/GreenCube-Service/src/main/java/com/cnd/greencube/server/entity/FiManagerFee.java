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
 * 会费缴纳记录表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_FI_MANAGER_FEE")
public class FiManagerFee extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "C_USER_ID")
	String userid;

	// 用户名
	@Column(name = "C_USER_NAME")
	String userName;

	// 店铺id，为哪个店铺交的会费
	@Column(name = "C_SHOP_ID")
	String shopId;

	// 缴费时间
	@Column(name = "D_PAY_TIME")
	Date payTime;

	// 结束时间
	@Column(name = "D_END_TIME")
	Date endTime;

	// 缴费金额
	@Column(name = "N_FEE")
	Integer fee;

	// 支付id
	@Column(name = "C_PAYMENT_ID")
	String paymentId;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
