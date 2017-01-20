package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 百万家庭-家庭表
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_FADOC_FAMILY")
public class FadocFamily extends BaseEntity {
	@Column(name = "C_NAME")
	String name;// 姓名

	@Column(name = "C_FAMILY_MEMBER")
	String familyMember;// 家庭成员

	@Column(name = "C_MEDICAL_HISTORY")
	String medicalHistory;// 家族病史

	@Column(name = "C_IMG_URL")
	String imgUrl;// 图片

	@Column(name = "C_DESC")
	String desc;// 描述

	@Column(name = "C_USER_ID")
	String userId;// 用户id（与T_USER表关联）

	@Column(name = "C_MEMBER_ID")
	String memberId;// 成员id（T_MEMBER表关联）

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(String familyMember) {
		this.familyMember = familyMember;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
