package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 提现实体类
 * 
 * @author 胡晓光
 * 
 */
@Entity
@Table(name = "T_FI_WITHDRAW")
public class FiWithdraw extends BaseEntity {
	private static final long serialVersionUID = -883976442098681391L;
	// 状态，1-已经申请，待审核， 2-正在转账， 3-已转账 ， 4 -驳回转账， 5- 转账失败
	public static final int STATUS_WAITING = 1;
	public static final int STATUS_PENDING = 2;
	public static final int STATUS_FINISHED = 3;
	public static final int STATUS_REJECT = 4;
	public static final int STATUS_FAILED = 5;

	// 微信转账
	public static final int METHOD_WX = 1;

	// 转账到银行卡
	public static final int METHOD_BANK = 2;

	// 提现者id
	@Column(name = "C_USER_ID")
	String userId;

	// 提现者姓名
	@Column(name = "C_USER_NAME")
	String userName;

	// 银行卡id
	@Column(name = "C_BANK_ID")
	String bankId;

	// 微信转账时的openid
	@Column(name = "C_OPEN_ID")
	String openId;

	// 转账方式, 1-微信转账，2-转账到银行卡
	@Column(name = "N_TRANSFER_METHOD")
	Integer transferMethod;

	// 转账金额，单位：分
	@Column(name = "N_AMOUNT")
	Integer amount;

	// 申请转账的时间
	@Column(name = "D_APPLY_TIME")
	Date applyTime;

	// 状态，1-已经申请，待审核， 2-正在转账， 3-已转账 ， 4 -驳回转账， 5- 转账失败
	@Column(name = "N_STATUS")
	Integer status;

	// 审核人id
	@Column(name = "C_AUDITOR_ID")
	String auditorId;

	// 审核人姓名
	@Column(name = "C_AUDITOR_NAME")
	String auditorName;

	// 审核时间
	@Column(name = "D_AUDIT_TIME")
	Date auditTime;

	// 实际交易时间
	@Column(name = "D_WITHDRAW_TIME")
	Date withdrawTime;

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

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getWithdrawTime() {
		return withdrawTime;
	}

	public void setWithdrawTime(Date withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getTransferMethod() {
		return transferMethod;
	}

	public void setTransferMethod(Integer transferMethod) {
		this.transferMethod = transferMethod;
	}

}
