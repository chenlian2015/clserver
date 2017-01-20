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
 * 会议实体
 * @author huxg
 *
 */
@Entity
@Table(name = "T_MEETING")
public class Meeting extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 会议室名称
	@Column(name = "C_NAME")
	String name;

	// 会议室密码
	@Column(name = "C_PASSWORD")
	String password;

	// 会议议题
	@Column(name = "C_TOPIC")
	String topic;

	// 最大会议人数
	@Column(name = "N_ATTENDANCE_MAX_COUNT")
	Integer attendanceMaxCount;

	// 会议模式：1-视频会议，2-传统会议模式
	@Column(name = "N_MODEL")
	Integer model;

	// 会议室类型：1-公共会议室，2-私人会议室
	@Column(name = "N_TYPE")
	Integer type;

	// 会议室当前状态：0-未开始，1-已开始，2-已结束
	@Column(name = "N_STATUS")
	Integer status;

	// 创建人ID
	@Column(name = "C_CREATER_ID")
	String createrId;

	// 创建人姓名
	@Column(name = "C_CREATER_NAME")
	String createrName;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 开始时间
	@Column(name = "D_BEGIN_TIME")
	Date beginTime;

	// 结束时间
	@Column(name = "D_END_TIME")
	Date endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getAttendanceMaxCount() {
		return attendanceMaxCount;
	}

	public void setAttendanceMaxCount(Integer attendanceMaxCount) {
		this.attendanceMaxCount = attendanceMaxCount;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}