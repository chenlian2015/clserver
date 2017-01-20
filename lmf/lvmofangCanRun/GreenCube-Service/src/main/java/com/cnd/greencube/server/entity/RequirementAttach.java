package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 需求的附件
 * 
 * @author cndini
 * 
 */
@Entity
@Table(name = "T_REQUIREMENT_ATTACH")
public class RequirementAttach extends BaseEntity {
	private static final long serialVersionUID = 4251922014927188645L;

	// 需求id
	@Column(name = "C_REQUIREMENT_ID")
	String requirementId;

	// 小图路径
	@Column(name = "C_MINI_PATH")
	String miniPath;

	// 大图路径
	@Column(name = "C_BIG_PATH")
	String bigPath;

	// 小图宽度
	@Column(name = "N_MINI_WIDTH")
	Integer miniWidth;

	// 小图高度
	@Column(name = "N_MINI_HEIGHT")
	Integer miniHeight;

	// 大图宽度
	@Column(name = "N_BIG_WIDTH")
	Integer bigWidth;

	// 大图高度
	@Column(name = "N_BIG_HEIGHT")
	Integer bigHeight;

	// 类型 1-文字，2-图片,3-视频
	@Column(name = "N_ATTACH_TYPE")
	Integer attachType;

	// 长宽比例
	@Column(name = "C_SCALE_RATE")
	String scaleRate;

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getMiniPath() {
		return miniPath;
	}

	public void setMiniPath(String miniPath) {
		this.miniPath = miniPath;
	}

	public String getBigPath() {
		return bigPath;
	}

	public void setBigPath(String bigPath) {
		this.bigPath = bigPath;
	}

	public Integer getMiniWidth() {
		return miniWidth;
	}

	public void setMiniWidth(Integer miniWidth) {
		this.miniWidth = miniWidth;
	}

	public Integer getMiniHeight() {
		return miniHeight;
	}

	public void setMiniHeight(Integer miniHeight) {
		this.miniHeight = miniHeight;
	}

	public Integer getBigWidth() {
		return bigWidth;
	}

	public void setBigWidth(Integer bigWidth) {
		this.bigWidth = bigWidth;
	}

	public Integer getBigHeight() {
		return bigHeight;
	}

	public void setBigHeight(Integer bigHeight) {
		this.bigHeight = bigHeight;
	}

	public Integer getAttachType() {
		return attachType;
	}

	public void setAttachType(Integer attachType) {
		this.attachType = attachType;
	}

	public String getScaleRate() {
		return scaleRate;
	}

	public void setScaleRate(String scaleRate) {
		this.scaleRate = scaleRate;
	}

}
