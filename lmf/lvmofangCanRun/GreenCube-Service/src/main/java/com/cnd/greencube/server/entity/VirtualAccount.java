package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 虚拟账户实体
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_VIRTUAL_ACCOUNT")
public class VirtualAccount extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 账户余额（现金金额）
	@Column(name = "N_BALANCE")
	Integer balance;

	// 魔方币余额
	@Column(name = "N_CBIT_BALANCE")
	Integer cbitBalance;

	// 总收入
	@Column(name = "N_TOTAL_INCOME")
	Integer totalInCome;

	// 总支出
	@Column(name = "N_TOTAL_PAID")
	Integer totalPaid;

	// 账户密码
	@Column(name = "C_ACCOUNT_PASSWORD")
	String accountPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getCbitBalance() {
		return cbitBalance;
	}

	public void setCbitBalance(Integer cbitBalance) {
		this.cbitBalance = cbitBalance;
	}

	public Integer getTotalInCome() {
		return totalInCome;
	}

	public void setTotalInCome(Integer totalInCome) {
		this.totalInCome = totalInCome;
	}

	public Integer getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Integer totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

}
