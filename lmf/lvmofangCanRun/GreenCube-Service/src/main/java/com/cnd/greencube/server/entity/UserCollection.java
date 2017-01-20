/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 用户收藏实体
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_USER_COLLECTION")
public class UserCollection extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public enum CollectionType {
		TYPE_GOODS("商品"), TYPE_KNOWLEDGE("知识"), TYPE_VIDEOS("视频"), TYPE_HOSPITAL("医院"), TYPE_PROFESSOR("专家"), TYPE_INFO("资讯");
		String name;

		CollectionType(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

	// 用户ID
	@Column(name = "C_USER_ID")
	String userId;

	// 链接地址
	@Column(name = "C_URL")
	String url;

	// 外键ID
	@Column(name = "C_FOREIGN_ID")
	String foreignId;

	// 类型
	@Enumerated(EnumType.STRING)
	@Column(name = "C_TYPE")
	CollectionType type;

	// 收藏时间
	@Column(name = "D_TIME")
	Date time;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public CollectionType getType() {
		return type;
	}

	public void setType(CollectionType type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}