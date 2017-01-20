package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 百万家庭-医院科室表
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_CODE_HOSPITAL_DEPARTMENT")
public class FadocHospitalDeparement extends BaseEntity {
	@Column(name = "C_NAME")
	String name;// 科室名称

	@Column(name = "C_CLASS_CODE")
	String classCode;// 分级标识

	@Column(name = "N_ORDER")
	Integer order;// 排序标识

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
