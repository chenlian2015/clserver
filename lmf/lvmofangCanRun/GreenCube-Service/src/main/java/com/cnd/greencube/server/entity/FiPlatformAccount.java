package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 平台账户
 * 
 * @author huxg
 */
@Entity
@Table(name = "T_FI_PLATFORM_ACCOUNT")
public class FiPlatformAccount extends BaseEntity {
	private static final long serialVersionUID = -7794786953837510253L;

	// 平台账户当前总金额，包括用户金额
	@Column(name = "N_BALANCE")
	Long balance;

	// 平台账户当前用户资金总额（冻结资金额）
	@Column(name = "N_USER_BALANCE")
	Long userBalance;

	// 平台总收入（指实际收入），可动金额，不包括用户冻结的金额
	@Column(name = "N_TOTAL_INCOME")
	Long totalIncome;

	// 平台总支出
	@Column(name = "N_TOTAL_PAID")
	Long totalPaid;

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Long getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Long totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Long getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Long userBalance) {
		this.userBalance = userBalance;
	}

}
