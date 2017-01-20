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
import javax.persistence.Transient;

/**
 * 账号表
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_USER")
public class User extends BaseEntity {
	public static final int USERTYPE_ADMIN = 90;

	// 店主
	public static final int USERTYPE_SHOP = 0x1000;

	// 缴费会员
	public static final int USERTYPE_MEMBER = 0x0100;

	// 健康会会员
	public static final int USERTYPE_HEATHY = 0x0010;

	// 普通用户
	public static final int USERTYPE_USER = 0x0001;

	// 登录账号
	@Column(name = "C_LOGIN_NAME")
	String loginName;

	// 登录密码
	@Column(name = "C_PASSWORD")
	String password;

	// 姓名
	@Column(name = "C_NAME")
	String name;

	// 昵称
	@Column(name = "C_NICK_NAME")
	String nickname;

	// 照片路径
	@Column(name = "C_PHOTO")
	String photo;

	// 上次登录时间
	@Column(name = "D_LAST_LOGIN_TIME")
	Date lastLoginTime;

	// QQ登录用户的唯一标识
	@Column(name = "C_QQ_OPEN_ID")
	String qqOpenId;

	// 该用户是否有效
	@Column(name = "N_VALID")
	Integer valid;

	// 手机
	@Column(name = "C_MOBILE")
	String mobile;

	// 邮箱
	@Column(name = "C_EMAIL")
	String email;

	// 用户类型
	@Column(name = "C_USER_TYPE")
	String userType;

	// token
	@Transient
	String token;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
