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
 * 用户账户统计明细
 * 
 * @author lff
 * 
 */
@Entity
@Table(name = "T_STAT_VIRTUAL_ACCOUNT_DETAIL")
public class StatVirtualAccountDetail extends BaseEntity {
	private static final long serialVersionUID = -833153522153649059L;

	// 交易人id
	@Column(name = "C_USER_ID")
	String userId;

	// 为年+月的一个int类型
	@Column(name = "N_MONTHY")
	Integer monthy;

	// 收入
	@Column(name = "N_INCOME")
	Integer income;

	// 支出
	@Column(name = "N_EXPEND")
	Integer expend;

	// 收益
	@Column(name = "N_REVENUE")
	Integer revenue;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getMonthy() {
		return monthy;
	}

	public void setMonthy(Integer monthy) {
		this.monthy = monthy;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getExpend() {
		return expend;
	}

	public void setExpend(Integer expend) {
		this.expend = expend;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

}
