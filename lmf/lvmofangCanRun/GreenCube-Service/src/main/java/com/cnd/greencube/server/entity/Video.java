/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 视频
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_VIDEO")
public class Video extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 视频名称
	@Column(name = "C_NAME")
	String name;

	// 视频描述
	@Column(name = "C_DESC")
	String desc;

	// 视频图片
	@Column(name = "C_UPLOAD_PATH")
	String uploadPath;

	// 上传时间
	@Column(name = "D_UPLOAD_TIME")
	Date uploadTime;

	// 上传者ID
	@Column(name = "C_UPLOADER_ID")
	String uploaderId;

	// 上传者姓名
	@Column(name = "C_UPLOADER_NAME")
	String uploaderName;

	// 上传者姓名
	@Column(name = "C_SELLER_ID")
	String sellerId;

	// 上传者姓名
	@Column(name = "C_SELLER_NAME")
	String sellerName;

	// 点击次数
	@Column(name = "N_CLICK_COUNT")
	Integer clickCount;

	// 点赞次数
	@Column(name = "N_PRAISE_COUNT")
	Integer praiseCount;

	// 评价次数
	@Column(name = "N_EVAL_COUNT")
	Integer evalCount;

	// 视频id
	@Column(name = "C_POLV_VIDEO_ID")
	String videoId;

	// 视频url
	@Column(name = "C_POLV_VIDEO_URL")
	String videoUrl;

	// swf url
	@Column(name = "C_POLV_SWF_URL")
	String swfUrl;

	// 视频首图
	@Column(name = "C_POLV_VIDEO_PIC")
	String videoPic;

	// 视频时长
	@Column(name = "C_VIDEO_DURATION")
	String duration;

	// 是否有效
	@Column(name = "N_STATUS_AUDIT")
	Integer statusAudit;

	// 是否有效
	@Column(name = "N_IS_VALID")
	Integer isValid;

	// 对应T_RESOURCE_CATEGORY主键
	@Column(name = "C_CATEGORY_ID")
	String categoryId;

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

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getEvalCount() {
		return evalCount;
	}

	public void setEvalCount(Integer evalCount) {
		this.evalCount = evalCount;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getSwfUrl() {
		return swfUrl;
	}

	public void setSwfUrl(String swfUrl) {
		this.swfUrl = swfUrl;
	}

	public String getVideoPic() {
		return videoPic;
	}

	public void setVideoPic(String videoPic) {
		this.videoPic = videoPic;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(Integer statusAudit) {
		this.statusAudit = statusAudit;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
