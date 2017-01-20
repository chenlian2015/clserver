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
 * 充值记录表 充值指的是从用户银行卡向本系统虚拟账户中充入金额
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_FI_CHARGE")
public class FiCharge extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "C_USER_ID")
	String userid;

	// 用户名
	@Column(name = "C_USER_NAME")
	String userName;

	// 卡号
	@Column(name = "C_CARD_NO")
	String cardNo;

	// 充值金额
	@Column(name = "N_FEE")
	BigDecimal fee;

	// 充值时间
	@Column(name = "D_TRANS_TIME")
	Date transTime;

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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
}
