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
 * 用户银行卡信息
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_USER_BANK_CARD")
public class UserBankCard extends BaseEntity {
	private static final long serialVersionUID = 4305178132063452147L;

	// 持卡人id
	@Column(name = "C_USER_ID")
	String userId;

	// 持卡人姓名
	@Column(name = "C_USER_NAME")
	String userName;

	// 持卡人身份证号
	@Column(name = "C_IDCARD")
	String idCard;

	// 发卡行名称
	@Column(name = "C_BANK_NAME")
	String bankName;

	// 持卡人姓名
	@Column(name = "C_CARD_HOLDER_NAME")
	String cardHolderName;

	// 卡号
	@Column(name = "C_CARD_NUM")
	String cardNum;

	// 预留手机号
	@Column(name = "C_PHONE")
	String phone;

	// 绑定日期
	@Column(name = "D_BIND_TIME")
	String bindTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
