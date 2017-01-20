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
 * 支付记录表
 * 
 * 该类记录了每一笔支付均记录 该表也作为交易日志表
 * 
 * @author 胡晓光
 * 
 */
@Entity
@Table(name = "T_FI_PAYMENT")
public class FiPayment extends BaseEntity {
	private static final long serialVersionUID = 2699730528059773312L;

	// 会费
	public static final String SUBJECT_MEMBER_FEE = "member_fee";

	// 管理费
	public static final String SUBJECT_MANAGER_FEE = "manager_fee";

	// 用户充值
	public static final String SUBJECT_USER_CHARGE = "user_charge";

	// 商品交易
	public static final String SUBJECT_GOODS_TRANSACTION = "goods";

	// 完成支付
	public static final int PAY_STATUS_FINISH = 1;

	// 待支付
	public static final int PAY_STATUS_PAYING = 2;

	// 取消交易
	public static final int PAY_STATUS_CANCELD = 5;

	// 支付金额
	@Column(name = "N_FEE")
	Integer fee;

	// 创建支付记录的时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 支付完成时间
	@Column(name = "D_FINISH_TIME")
	Date finishTime;

	// 支付渠道
	@Column(name = "C_METHOD")
	String method;

	// 银行名称
	@Column(name = "C_BANK_NAME")
	String bankName;

	// 卡号
	@Column(name = "C_CARD_NUM")
	String cardNum;

	// 关联的订单编号
	@Column(name = "C_REMARK")
	String remark;

	// 支付者id
	@Column(name = "C_EXPEND_USER_ID")
	String expendUserId;

	// 支付者姓名
	@Column(name = "C_EXPEND_USER_NAME")
	String expendUserName;

	// 收入者用户id
	@Column(name = "C_INCOME_USER_ID")
	String incomeUserId;

	// 收入者用户名
	@Column(name = "C_INCOME_USER_NAME")
	String incomeUserName;

	// 关联的订单编号
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 交易状态
	@Column(name = "N_STATUS")
	Integer status;
  //类型
	@Column(name = "C_TRAN_TYPE")
	String tranType;

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpendUserId() {
		return expendUserId;
	}

	public void setExpendUserId(String expendUserId) {
		this.expendUserId = expendUserId;
	}

	public String getExpendUserName() {
		return expendUserName;
	}

	public void setExpendUserName(String expendUserName) {
		this.expendUserName = expendUserName;
	}

	public String getIncomeUserId() {
		return incomeUserId;
	}

	public void setIncomeUserId(String incomeUserId) {
		this.incomeUserId = incomeUserId;
	}

	public String getIncomeUserName() {
		return incomeUserName;
	}

	public void setIncomeUserName(String incomeUserName) {
		this.incomeUserName = incomeUserName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

}