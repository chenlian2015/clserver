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
 * 订单-发票申请表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_ORDER_INVOICE")
public class OrderInvoice extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 关联的订单ID
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 发票类型， 1- 机打发票, 2- 增值税发票
	@Column(name = "N_TYPE")
	Integer type;

	// 发票抬头类型 1 -个人， 2-单位
	@Column(name = "N_TITLE_TYPE")
	Integer titleType;

	// 发票抬头
	@Column(name = "C_TITLE")
	String title;

	// 单位名称
	@Column(name = "C_CORP_NAME")
	String corpName;

	// 纳税人识别码
	@Column(name = "C_TAXPAYER_IDENTIFICATION_CODE")
	String taxpayerIdentificationCode;

	// 注册地址
	@Column(name = "C_REGIST_ADDRESS")
	String registAddress;

	// 注册电话
	@Column(name = "C_REGIST_PHONE")
	String registPhone;

	// 开户银行
	@Column(name = "C_BANK_NAME")
	String bankName;

	// 开户账号
	@Column(name = "C_BANK_ACCOUNT")
	String bankAccount;

	// 发票接受者姓名
	@Column(name = "C_RECEIVER_NAME")
	String receiverName;

	// 发票接受者电话
	@Column(name = "C_RECEIVER_MOBILE")
	String receiverMobile;

	// 省
	@Column(name = "C_RECEIVER_PROVINCE")
	String receiverProvince;

	// 市
	@Column(name = "C_RECEIVER_CITY")
	String receiverCity;

	// 详细地址
	@Column(name = "C_RECEIVER_ADDRESS")
	String receiverAddress;

	// 邮编
	@Column(name = "C_RECEIVER_ZIPCODE")
	String receiverZipCode;

	// 发票申请处理状态 1-已经申请，2-审核通过，-3-驳回，4-已邮寄
	@Column(name = "N_STATUS_DEAL")
	Integer statusDeal;

	// 快递登记时间
	@Column(name = "D_APPLY_TIME")
	Date applyTime;

	// 发票审核人id
	@Column(name = "C_AUDIT_USER_ID")
	String auditUserId;

	// 发票审核人姓名
	@Column(name = "C_AUDIT_USER_NAME")
	String auditUserName;

	// 发票审核时间
	@Column(name = "D_AUDIT_TIME")
	Date auditTime;

	//  发票快递完成人ID
	@Column(name = "C_COMPLETE_USER_ID")
	String completeUserId;

	// 发票快递完成人
	@Column(name = "C_COMPLETE_USER_NAME")
	String completeUserName;

	// 发票快递完成时间
	@Column(name = "D_COMPLETE_TIME")
	Date completeTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getTaxpayerIdentificationCode() {
		return taxpayerIdentificationCode;
	}

	public void setTaxpayerIdentificationCode(String taxpayerIdentificationCode) {
		this.taxpayerIdentificationCode = taxpayerIdentificationCode;
	}

	public String getRegistAddress() {
		return registAddress;
	}

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	public String getRegistPhone() {
		return registPhone;
	}

	public void setRegistPhone(String registPhone) {
		this.registPhone = registPhone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZipCode() {
		return receiverZipCode;
	}

	public void setReceiverZipCode(String receiverZipCode) {
		this.receiverZipCode = receiverZipCode;
	}

	public Integer getStatusDeal() {
		return statusDeal;
	}

	public void setStatusDeal(Integer statusDeal) {
		this.statusDeal = statusDeal;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getCompleteUserId() {
		return completeUserId;
	}

	public void setCompleteUserId(String completeUserId) {
		this.completeUserId = completeUserId;
	}

	public String getCompleteUserName() {
		return completeUserName;
	}

	public void setCompleteUserName(String completeUserName) {
		this.completeUserName = completeUserName;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

}
