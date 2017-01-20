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
 * 用户行为日志 本日志用来记录用户异常行为， 异常行为包括：重复登录异常检测、账户金额快速变化异常检测
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_LOG_USER_BEHAVIOUR")
public class LogUserBehaviour extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 用户ID
	@Column(name = "C_USER_ID")
	String userid;

	// 用户姓名
	@Column(name = "C_USER_NAME")
	String username;

	// IP地址
	@Column(name = "C_IP_ADDR")
	String ip;

	// 操作时间
	@Column(name = "D_HAPPEN_TIME")
	Date happenTime;

	// 事件
	@Column(name = "C_EVENT")
	String event;

	// 警告报警级别
	@Column(name = "N_ALERT_LEVEL")
	Integer altertLevel;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getAltertLevel() {
		return altertLevel;
	}

	public void setAltertLevel(Integer altertLevel) {
		this.altertLevel = altertLevel;
	}

	
}
