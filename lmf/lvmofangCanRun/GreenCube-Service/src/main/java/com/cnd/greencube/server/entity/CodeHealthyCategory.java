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
 * 业务类别，是指商品分类、店铺分类中的类别
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_CODE_HEALTHY_CATEGORY")
public class CodeHealthyCategory extends BaseEntity {
	@Column(name = "C_CLASS_CODE")
	String classCode;

	@Column(name = "C_NAME")
	String name;

	@Column(name = "N_ORDER")
	Integer order;

	@Transient
	List<CodeHealthyCategory> subCategory = new ArrayList<CodeHealthyCategory>();

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<CodeHealthyCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<CodeHealthyCategory> subCategory) {
		this.subCategory = subCategory;
	}

	public void addSubCategory(CodeHealthyCategory sub) {
		this.subCategory.add(sub);
	}
}
