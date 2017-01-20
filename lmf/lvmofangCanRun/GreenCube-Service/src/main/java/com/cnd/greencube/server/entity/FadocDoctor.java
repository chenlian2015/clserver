package com.cnd.greencube.server.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 百万家庭-医生表
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_FADOC_DOCTOR")
public class FadocDoctor extends BaseEntity {
	@Column(name = "C_NAME")
	String name;// 医生姓名

	@Column(name = "C_SEX")
	String sex;// 医生性别

	@Column(name = "C_HOSPITAL_ID")
	String hospitalId;// 医院id（与T_FADOC_HOSPITAL表关联）

	@Column(name = "C_HOSPITAL_NAME")
	String hospitalName;// 医院名称

	@Column(name = "C_IMG_URL")
	String imgUrl;// 图片

	@Column(name = "C_JOB_TITLE")
	String jobTitle;// 职称

	@Column(name = "C_JOB_POST")
	String jobPost;// 职务

	@Column(name = "C_HOSPITAL_DEPARTMENT_ID")
	String hospitalDepartmentId;// 科室id（与T_CODE_HOSPITAL_DEPARTMENT表关联）

	@Column(name = "C_HOSPITAL_DEPARTMENT_NAME")
	String hospitalDepartmentName;// 科室名称

	@Column(name = "C_DESC")
	String desc;// 医生描述（简介）

	@Column(name = "C_GOOD_TREATMENT_DESEASES")
	String goodTreatmentDiseases;// 擅长治疗疾病

	@Column(name = "C_VISITS_TIME")
	Date visitsTime;// 出诊时间

	@Column(name = "N_REGISTRATION_FEE")
	BigDecimal registrationFee;// 专家挂号费

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobPost() {
		return jobPost;
	}

	public void setJobPost(String jobPost) {
		this.jobPost = jobPost;
	}

	public String getHospitalDepartmentId() {
		return hospitalDepartmentId;
	}

	public void setHospitalDepartmentId(String hospitalDepartmentId) {
		this.hospitalDepartmentId = hospitalDepartmentId;
	}

	public String getHospitalDepartmentName() {
		return hospitalDepartmentName;
	}

	public void setHospitalDepartmentName(String hospitalDepartmentName) {
		this.hospitalDepartmentName = hospitalDepartmentName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGoodTreatmentDiseases() {
		return goodTreatmentDiseases;
	}

	public void setGoodTreatmentDiseases(String goodTreatmentDiseases) {
		this.goodTreatmentDiseases = goodTreatmentDiseases;
	}

	public Date getVisitsTime() {
		return visitsTime;
	}

	public void setVisitsTime(Date visitsTime) {
		this.visitsTime = visitsTime;
	}

	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
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
