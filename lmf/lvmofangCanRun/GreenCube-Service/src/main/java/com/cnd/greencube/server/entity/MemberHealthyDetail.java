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
 * 健康会成员-详细信息
 * 
 * @author huxg
 * dongyali 修改注释 2016-2-18
 */
@Entity
@Table(name = "T_MEMBER_HEALTHY_DETAIL")
public class MemberHealthyDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
   //健康会会员id
	@Column(name = "C_HEALTHY_ID")
	String healthyId;

	// 性别
	@Column(name = "N_SEX")
	Integer sex;

	// 通信地址
	@Column(name = "C_ADDRESS")
	String address;

	// 邮编
	@Column(name = "N_ZIP_CODE")
	Integer zipCode;

	// 证件类型
	@Column(name = "N_IDENTITY_TYPE")
	Integer identityType;

	// 证件号码
	@Column(name = "C_IDENTITY_NUM")
	String identityNum;

	// 注册时间
	@Column(name = "D_REGIST_TIME")
	Date registTime;

	public String getHealthyId() {
		return healthyId;
	}

	public void setHealthyId(String healthyId) {
		this.healthyId = healthyId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
}
