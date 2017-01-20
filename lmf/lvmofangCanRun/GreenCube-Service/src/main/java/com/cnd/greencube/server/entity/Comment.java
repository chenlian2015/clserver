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
 * 评论表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_COMMENT")
public class Comment extends BaseEntity  {
	private static final long serialVersionUID = 1L;

	// 外键ID
	@Column(name = "C_FOREIGN_ID")
	String foreignId;

	// 外键对应的表
	@Column(name = "C_FOREIGN_TABLE")
	String foreignTable;

	// 评论者ID
	@Column(name = "C_USER_ID")
	String userId;

	// 评论者姓名
	@Column(name = "C_USER_NAME")
	String userName;

	// 评论者头像
	@Column(name = "C_USER_PHOTO")
	String userPhoto;

	// 评论内容
	@Column(name = "C_COMMENT")
	String comment;

	// 评论时间
	@Column(name = "D_COMMENT_TIME")
	Date commentTime;

	// 评论总得分
	@Column(name = "N_SCORE")
	Integer score;

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getForeignTable() {
		return foreignTable;
	}

	public void setForeignTable(String foreignTable) {
		this.foreignTable = foreignTable;
	}

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

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
