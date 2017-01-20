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
 * 站内短信
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_LETTER")
public class Letter extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final int BOX_UNREAD = 1;
	public static final int BOX_SEND = 2;
	public static final int BOX_DRAFT = 3;
	public static final int BOX_TRASH = 4;

	public static final int READ_READ = 1;
	public static final int READ_UNREAD = 2;

	public enum LetterType {
		// 平台给予店铺的通知
		TYPE_PT_DPTZ("平台通知", 1),

		// 店铺给予消费者的通知
		TYPE_QXDD("店铺通知", 2),

		// 关爱信息等
		TYPE_FKCG("温馨关怀", 3),

		// 充值成功提醒
		TYPE_CZCG("充值提醒", 7),

		// 发货提醒
		TYPE_BOOK_FHTZ("发货通知", 8);

		int value;
		String name;

		LetterType(String name, int v) {
			this.name = name;
			this.value = v;
		}

		public int getValue() {
			return this.value;
		}

		public String getName() {
			return this.name;
		}
	}
	// 标题
	@Column(name = "C_TITLE")
	String title;

	// 内容
	@Column(name = "C_CONTENT")
	String content;

	// 发信人id
	@Column(name = "C_SENDER_ID")
	String senderId;

	// 发信人姓名
	@Column(name = "C_SENDER_NAME")
	String senderName;

	// 收信人id
	@Column(name = "C_RECEIVER_ID")
	String receiverId;

	// 收信人姓名
	@Column(name = "C_RECEIVER_NAME")
	String receiverName;

	// 发信时间
	@Column(name = "D_SEND_TIME")
	Date sendTime;

	// 发信状态，分别为：BOX_或Read_的值
	@Column(name = "N_STATUS")
	Integer status;

	// 发信类型，取值：LetterType
	@Enumerated(EnumType.STRING)
	@Column(name = "C_LETTER_TYPE")
	LetterType letterType;

	// 位置 1：发件箱；2：收件箱；3：草稿箱；4：回收站
	@Column(name = "N_MAIL_BOX")
	Integer mailbox;

	// 该信是谁的
	@Column(name = "C_OWNER_ID")
	String ownerId;

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

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LetterType getLetterType() {
		return letterType;
	}

	public void setLetterType(LetterType letterType) {
		this.letterType = letterType;
	}

	public Integer getMailbox() {
		return mailbox;
	}

	public void setMailbox(Integer mailbox) {
		this.mailbox = mailbox;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
