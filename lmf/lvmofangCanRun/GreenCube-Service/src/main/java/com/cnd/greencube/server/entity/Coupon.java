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
 * 卡券
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_COUPON")
public class Coupon extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 有效
	public static final int VALID_VALID = 1;
	public static final int VALID_INVALID = 0;
	public static final int VALID_FROZEN = 20;

	// 告警记录值
	public static final int ALERT_VALUE = 500;

	// 发布者ID
	@Column(name = "C_PUBLISHER_ID")
	String publisherId;

	// 发布者名称
	@Column(name = "C_PUBLISHER_NAME")
	String publisherName;

	// 卡号
	@Column(name = "C_SERIAL")
	String serial;

	// 密码
	@Column(name = "C_PASSWORD")
	String password;

	// 订单id，哪个订单用了这个卡了，也用于当结算完毕后销卡
	@Column(name = "C_ORDER_ID")
	String orderId;

	// 订单id，哪个订单用了这个卡了，也用于当结算完毕后销卡
	@Column(name = "C_ORDER_NO")
	String orderNo;

	// 用户ID，哪个用户使用了这个卡了
	@Column(name = "C_USER_ID")
	String userId;

	// 用户姓名，使用者姓名
	@Column(name = "C_USER_NAME")
	String userName;

	// 面值
	@Column(name = "N_VALUE")
	Integer value;

	// 类型，暂无用处，用来表示卡片的种类
	@Column(name = "N_TYPE")
	Integer type;

	// 起始日期
	@Column(name = "D_BEGIN_DATE")
	Date beginDate;

	// 截止日期
	@Column(name = "D_END_DATE")
	Date endDate;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 领卡时间
	@Column(name = "D_RECEIVE_DATE")
	Date receiveDate;

	// 使用时间
	@Column(name = "D_USE_TIME")
	Date useTime;

	// 是否有效
	@Column(name = "N_IS_VALID")
	Integer valid;

	// 是否被领取了，0 - 未领取， 1-已领取 ，但是尚未使用， 2-已领取，已使用
	@Column(name = "N_IS_ASSIGNED")
	Integer assigned;

	// 当前这张卡是否可以使用
	@Transient
	boolean canUse = false;

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

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAssigned() {
		return assigned;
	}

	public void setAssigned(Integer assigned) {
		this.assigned = assigned;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public boolean isCanUse() {
		return canUse;
	}

	public void setCanUse(boolean canUse) {
		this.canUse = canUse;
	}

}