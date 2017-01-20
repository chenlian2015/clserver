package com.cnd.greencube.server.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 好友关系
 * 
 * @author chenlian
 * 
 * */
@Entity
@Table( name="T_MEMBER_RELATION")
public class MemberRelation extends BaseEntity{

	//发起添加好友人的ID(用系统的登录名来唯一标识)
	@Column(name = "C_HOST_ID")
	private
	String hostId;
	
	//接受好友邀请人的ID
	@Column(name = "C_GUEST_ID")
	private
	String guestId;
		
	//成为好友的时间（以接受好友邀请为准）
	@Column(name = "D_CREATE_TIME")
	private
	Date createTime;
		
	//备注名
	@Column(name = "C_LABEL_NAME")
	private
	String labelName;
	
	//关系类型 ：备用
	@Column(name = "C_RELATION_TYPE")
	private
	String relationType;
	
	//删除标记
	@Column(name = "N_DELETE_FLAG")
	private
	Integer deleteFlag;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
