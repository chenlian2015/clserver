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
 * 用户账户信息明细
 * 
 * @author lff
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_VIRTUAL_ACCOUNT_DETAIL")
public class VirtualAccountDetail extends BaseEntity {

	// 交易人id
	@Column(name = "C_USER_ID")
	String userId;

	// 支付方式，支付渠道，支付宝、微信、银行卡
	@Column(name = "C_PAYMENT_METHOD")
	String paymentMethod;

	// 收支类型，1-收入，2-支出
	@Column(name = "N_PAYMENT_TYPE")
	String paymentType;

	// 收支金额
	@Column(name = "N_AMOUNT")
	Integer amount;

	// 交易时间
	@Column(name = "D_TRAN_TIME")
	Date tranTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getTranTime() {
		return tranTime;
	}

	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}

}
