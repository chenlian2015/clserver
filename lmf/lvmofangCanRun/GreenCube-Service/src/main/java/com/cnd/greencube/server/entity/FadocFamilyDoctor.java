package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 百万家庭-家庭医生表
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_FADOC_FAMILY_DOCTOR")
public class FadocFamilyDoctor extends BaseEntity {
	@Column(name = "C_FAMILY_ID")
	String familyId;// 家庭id（与T_FADOC_FAMILY表关联）

	@Column(name = "C_FAMILY_NAME")
	String familyName;// 姓名

	@Column(name = "C_DOCTOR_ID")
	String doctotId;// 医生id（与T_FADOC_DOCTOR表关联）

	@Column(name = "C_DOCTOR_NAME")
	String doctorName;// 医生姓名

	@Column(name = "D_START_TIME")
	Date startTime;// 开始时间

	@Column(name = "D_END_TIME")
	Date endTime;// 结束时间

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDoctotId() {
		return doctotId;
	}

	public void setDoctotId(String doctotId) {
		this.doctotId = doctotId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
