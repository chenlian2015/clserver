/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 健康会成员
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_MEMBER_HEALTHY")
public class MemberHealthy extends BaseEntity {
	private static final long serialVersionUID = 1L;
   //用户id
	@Column(name = "C_USER_ID")
	String userid;

	// 成员的姓名，冗余字段，与user表中的name一致。
	@Column(name = "C_NAME")
	String name;
	//用户编码
	@Column(name = "C_MEMBER_CODE")
	String memberCode;

	// 所在省id
	@Column(name = "C_PROVINCE_ID")
	String provinceId;

	// 所在省名称MemberHealthy
	@Column(name = "C_PROVINCE_NAME")
	String provinceName;

	// 所在市id
	@Column(name = "C_CITY_ID")
	String cityId;

	// 所在市名称
	@Column(name = "C_CITY_NAME")
	String cityName;

	// 关注的人
	@Column(name = "C_ATTENTION_MEMBERS")
	String attentionMembers;

	// 总粉丝数
	@Column(name = "N_FANS_AMOUNT")
	Integer fansAmount;

	// 总关注数
	@Column(name = "N_FOCUS_AMOUNT")
	Integer focusAmount;

	// 评价次数
	@Column(name = "N_COMMENT_AMOUNT")
	Integer commentAmount;

	// 累计消费金额
	@Column(name = "N_TOTAL_CONSUME")
	BigDecimal totalConsume;

	// 是否通过审核(-1 驳回 0 未审核 1 审核通过)
	@Column(name = "N_IS_AUDIT")
	Integer isAudit;

	// 是否有效
	@Column(name = "N_IS_VALID")
	Integer isValid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getFansAmount() {
		return fansAmount;
	}

	public void setFansAmount(Integer fansAmount) {
		this.fansAmount = fansAmount;
	}

	public Integer getFocusAmount() {
		return focusAmount;
	}

	public void setFocusAmount(Integer focusAmount) {
		this.focusAmount = focusAmount;
	}

	public Integer getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}

	public BigDecimal getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(BigDecimal totalConsume) {
		this.totalConsume = totalConsume;
	}

	public String getAttentionMembers() {
		return attentionMembers;
	}

	public void setAttentionMembers(String attentionMembers) {
		this.attentionMembers = attentionMembers;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
