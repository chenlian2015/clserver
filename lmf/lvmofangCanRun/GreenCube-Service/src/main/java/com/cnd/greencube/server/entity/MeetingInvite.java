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
 * 会议邀请
 * @author huxg
 *
 */
@Entity
@Table(name = "T_MEETING_INVITE")
public class MeetingInvite extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 会议ID
	@Column(name = "C_MEETING_ID")
	String meetingId;

	// 主贴ID
	@Column(name = "C_INVITER_ID")
	String inviterId;

	// 发出者ID
	@Column(name = "C_INVITER_NAME")
	String inviterName;

	// 发出者姓名
	@Column(name = "D_INVITE_TIME")
	Date inviteTime;

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getInviterId() {
		return inviterId;
	}

	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}

	public String getInviterName() {
		return inviterName;
	}

	public void setInviterName(String inviterName) {
		this.inviterName = inviterName;
	}

	public Date getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}
}