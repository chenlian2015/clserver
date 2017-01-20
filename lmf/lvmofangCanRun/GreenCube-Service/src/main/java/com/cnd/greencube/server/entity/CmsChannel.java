/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 栏目信息（信息发布：栏目管理）
 * 
 * @author huxg
 */
@Entity
@Table(name = "T_CMS_CHANNEL")
public class CmsChannel extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 栏目名称
	@Column(name = "C_NAME")
	String name;

	// 栏目描述
	@Column(name = "C_DESC")
	String desc;

	// 添加时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 字父节点描述
	@Column(name = "C_CLASS_CODE")
	String classCode;

	// 是否可以删除
	@Column(name = "N_CAN_DELETE")
	Integer canDelete;

	// 图片尺寸要求
	@Column(name = "C_PIC_SIZE")
	String picSize;

	// 模板
	@Column(name = "C_TEMPLATE_NAME")
	String templateName;

	// 排序
	@Column(name = "N_ORDER")
	Integer order;
	@Transient
	List<CmsChannel> sub = new ArrayList<CmsChannel>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;	
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Integer canDelete) {
		this.canDelete = canDelete;
	}

	public String getPicSize() {
		return picSize;
	}

	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<CmsChannel> getSub() {
		return sub;
	}

	public void setSub(List<CmsChannel> sub) {
		this.sub = sub;
	}
	public void addSub(CmsChannel sub) {
		this.sub.add(sub);
	}
}
