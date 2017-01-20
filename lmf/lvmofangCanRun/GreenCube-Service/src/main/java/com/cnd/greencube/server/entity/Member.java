package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 缴费会员表
 * 
 * @author cndini
 * 
 */
@Entity
@Table(name = "T_MEMBER")
public class Member extends BaseEntity {
	private static final long serialVersionUID = -1445151060401507151L;

	// 用户id
	@Column(name = "C_USER_ID")
	String userId;

	// 姓名
	@Column(name = "C_REAL_NAME")
	String realName;

	// 性别
	@Column(name = "N_SEX")
	Integer sex;

	// 所在地址
	@Column(name = "C_ADDRESS")
	String address;

	// 邮编
	@Column(name = "N_ZIP_CODE")
	Integer zipCode;

	// 证件类型
	@Column(name = "C_IDENTITY_TYPE")
	String identityType;

	// 证件号码
	@Column(name = "C_IDENTITY_NUM")
	String identityNum;

	// 成为会员的时间
	@Column(name = "D_REGIST_TIME")
	Date registTime;

	// 会员资格开始时间
	@Column(name = "D_BEGIN_TIME")
	Date beginTime;

	// 会员资格结束时间
	@Column(name = "D_END_TIME")
	Date endTime;

	// 是否支付了会员费
	@Column(name = "N_IS_PAID_MEMBER_FEE")
	Integer paidMemberFee;

	// 会员身份是否有效
	@Column(name = "N_IS_VALID")
	Integer isValid;

	// 组织类型，1-个人，2-单位
	@Column(name = "N_ORGAN_TYPE")
	Integer memberType;

	// 单位地址
	@Column(name = "C_CORP_ADDR")
	String corpAddr;

	// 执照照片
	@Column(name = "C_CORP_LICENSE_PIC")
	String corpLicensePic;

	// 企业名称
	@Column(name = "C_CORP_NAME")
	String corpName;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getCorpAddr() {
		return corpAddr;
	}

	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}

	public String getCorpLicensePic() {
		return corpLicensePic;
	}

	public void setCorpLicensePic(String corpLicensePic) {
		this.corpLicensePic = corpLicensePic;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPaidMemberFee() {
		return paidMemberFee;
	}

	public void setPaidMemberFee(Integer paidMemberFee) {
		this.paidMemberFee = paidMemberFee;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

}