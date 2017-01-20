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
 * 意见反馈
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_FEEDBACK")
public class Feedback extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 反馈者ID
	@Column(name = "C_USER_ID")
	String userId;

	// 反馈者姓名
	@Column(name = "C_USER_NAME")
	String userName;

	// 标题
	@Column(name = "C_TITLE")
	String title;

	// 反馈的意见
	@Column(name = "C_CONTENT")
	String content;

	// 反馈时间
	@Column(name = "D_PUBLISH_TIME")
	Date publishTime;

	// 反馈者QQ
	@Column(name = "C_PHONE")
	String phone;

	// 反馈者QQ
	@Column(name = "C_QQ")
	String qq;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

}