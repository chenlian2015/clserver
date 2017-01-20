/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 需求
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_REQUIREMENT")
public class Requirement extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final int CONTENT_TYPE_TEXT = 1;
	public static final int CONTENT_TYPE_PHOTO = 2;
	public static final int CONTENT_TYPE_VIDEO = 3;
	public static final int CONTENT_TYPE_VOICE = 4;

	// 父需求id
	@Column(name = "C_PARENT_REQ_ID")
	String parentReqId;

	// 发布者id
	@Column(name = "C_POSTER_ID")
	String posterId;

	// 发布者姓名
	@Column(name = "C_POSTER_NAME")
	String posterName;

	// 发布者照片
	@Column(name = "C_POSTER_PHOTO")
	String posterPhoto;

	// 发布者ip
	@Column(name = "C_POSTER_IP")
	String posterIp;
	
	//类别id
	@Column(name = "C_CATEGORY_ID")
	String categoryId;
	
	//类别name
	@Column(name = "C_CATEGORY_NAME")
	String categoryName;

	// 发布时间
	@Column(name = "D_POST_TIME")
	Date postTime;

	// 标题
	@Column(name = "C_TITLE")
	String title;

	// 内容
	@Column(name = "C_CONTENT")
	String content;

	// 多媒体内容
	@Column(name = "C_MULTIPART")
	String multipart;

	// 需求类型（1-我要，2-我有，3-我发布）
	@Column(name = "N_REQ_TYPE")
	Integer reqType;

	// 关注数
	@Column(name = "N_PRAISE_AMOUNT")
	Integer praiseAmount;

	// 内容类型，1-文字，2-图片，3-视频
	@Column(name = "N_CONTENT_TYPE")
	Integer contentType;

	// 评论数
	@Column(name = "N_REPLY_AMOUNT")
	Integer replyAmount;

	// 附件，可以是图片、视频
	@Transient
	List<RequirementAttach> attaches;

	public String getPosterPhoto() {
		return posterPhoto;
	}

	public void setPosterPhoto(String posterPhoto) {
		this.posterPhoto = posterPhoto;
	}

	public String getParentReqId() {
		return parentReqId;
	}

	public void setParentReqId(String parentReqId) {
		this.parentReqId = parentReqId;
	}

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getPosterIp() {
		return posterIp;
	}

	public void setPosterIp(String posterIp) {
		this.posterIp = posterIp;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
	}

	public Integer getPraiseAmount() {
		return praiseAmount;
	}

	public void setPraiseAmount(Integer praiseAmount) {
		this.praiseAmount = praiseAmount;
	}

	public Integer getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(Integer replyAmount) {
		this.replyAmount = replyAmount;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public String getMultipart() {
		return multipart;
	}

	public void setMultipart(String multipart) {
		this.multipart = multipart;
	}

	public List<RequirementAttach> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<RequirementAttach> attaches) {
		this.attaches = attaches;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}