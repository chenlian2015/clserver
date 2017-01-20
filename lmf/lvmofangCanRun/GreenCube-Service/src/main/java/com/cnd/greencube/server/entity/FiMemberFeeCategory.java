package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 会员费类别实体
 * 
 * @author 胡晓光
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_FI_MEMBER_FEE_CATEGORY")
public class FiMemberFeeCategory extends BaseEntity {
	// 费用名称
	@Column(name = "C_NAME")
	String name;

	// 金额
	@Column(name = "N_FEE")
	Integer fee;

	// 使用用户类别
	@Column(name = "N_SUIT_USER_TYPE")
	Integer suitUserType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getSuitUserType() {
		return suitUserType;
	}

	public void setSuitUserType(Integer suitUserType) {
		this.suitUserType = suitUserType;
	}

}