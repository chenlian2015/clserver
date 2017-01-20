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
 * QA
 * 
 * @author 胡晓光
 * 
 */
@Entity
@Table(name = "T_QA")
public class Qa extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 问题
	@Column(name = "C_QUESTION")
	String question;

	// 答案
	@Column(name = "C_ANSWER")
	String answer;

	// 顺序
	@Column(name = "N_ORDER")
	String order;

	// 发布者ID
	@Column(name = "C_PUBLISHER_ID")
	String publisherId;

	// 发布者姓名
	@Column(name = "C_PUBLISHER_NAME")
	String publisherName;

	// 发布者姓名
	@Column(name = "D_PUBLISHER_TIME")
	Date publisherTime;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Date getPublisherTime() {
		return publisherTime;
	}

	public void setPublisherTime(Date publisherTime) {
		this.publisherTime = publisherTime;
	}

}