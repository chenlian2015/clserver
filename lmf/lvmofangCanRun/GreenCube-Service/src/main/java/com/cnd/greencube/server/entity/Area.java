/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 区域
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_CODE_AREA")
public class Area extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 分级代码
	@Column(name = "C_CLASS_CODE")
	String classCode;

	// 名称
	@Column(name = "C_NAME")
	String name;

	// 首字母
	@Column(name = "C_FIRST_CHAR")
	String firstChar;

	// 是否是直辖市
	@Column(name = "N_IS_PROVINCE_CITY")
	Integer isProvinceCity;

	// 类型，1 - 市， 2-省
	@Column(name = "N_TYPE")
	Integer type;

	@Transient
	private List<Area> subAreas = new ArrayList<Area>();

	public List<Area> getSubAreas() {
		return subAreas;
	}

	public void setSubAreas(List<Area> subAreas) {
		this.subAreas = subAreas;
	}

	public void addSubArea(Area area) {
		this.subAreas.add(area);
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	public Integer getIsProvinceCity() {
		return isProvinceCity;
	}

	public void setIsProvinceCity(Integer isProvinceCity) {
		this.isProvinceCity = isProvinceCity;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}