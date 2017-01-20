package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 百万家庭-医院表
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_FADOC_HOSPITAL")
public class FadocHospital extends BaseEntity {
	@Column(name = "C_NAME")
	String name;// 医院名称

	@Column(name = "C_AREA_ID")
	String areaId;// 地区id（与T_CODE_AREA表关联）

	@Column(name = "C_DESC")
	String desc;// 描述

	@Column(name = "C_IMG_URL")
	String imaUrl;// 图片

	@Column(name = "C_LEVEL")
	String level;// 等级（比如二甲，三甲）

	@Column(name = "C_REMARK")
	String remark;// 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImaUrl() {
		return imaUrl;
	}

	public void setImaUrl(String imaUrl) {
		this.imaUrl = imaUrl;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
