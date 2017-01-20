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
 * 会议消息实体
 * @author huxg
 *
 */
@Entity
@Table(name = "T_MEETING_MESSAGE")
public class MeetingMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 会议ID
	@Column(name = "C_MEETING_ID")
	String meetingId;

	// 主贴ID
	@Column(name = "C_PARENT_MESSAGE_ID")
	String parentMessageId;

	// 发出者ID
	@Column(name = "C_POSTER_ID")
	String posterId;

	// 发出者姓名
	@Column(name = "C_POSTER_NAME")
	String posterName;

	// 发出时间
	@Column(name = "D_POST_TIME")
	Date postTime;

	// 1-文字消息，2-图文消息，3-语音消息，4-视频消息
	@Column(name = "N_MESSAGE_TYPE")
	Integer messageType;

	// 赞次数
	@Column(name = "N_PRAISE_AMOUNT")
	Integer praiseAmount;

	public String getParentMessageId() {
		return parentMessageId;
	}

	public void setParentMessageId(String parentMessageId) {
		this.parentMessageId = parentMessageId;
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

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getPraiseAmount() {
		return praiseAmount;
	}

	public void setPraiseAmount(Integer praiseAmount) {
		this.praiseAmount = praiseAmount;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

}