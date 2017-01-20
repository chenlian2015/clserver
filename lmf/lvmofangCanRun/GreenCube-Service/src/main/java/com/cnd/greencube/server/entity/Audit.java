package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 审核实体
 * @author huxg
 * 注释 dongyali 修改 2016-2-18
 */
@Entity
@Table(name = "T_AUDIT")
public class Audit extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 分级代码
	@Column(name = "C_AUDITOR_ID")
	String auditorId;

	// 审核者名称
	@Column(name = "C_AUDITOR_NAME")
	String auditorName;

	// 被审核者id
	@Column(name = "C_FOREIGN_ID")
	String foreignId;

	// 被审核者实体类
	@Column(name = "C_FOREIGN_OBJECT")
	String foreignObject;

	// 审核时间
	@Column(name = "D_AUDIT_TIME")
	Date auditTime;

	// 审核结果
	@Column(name = "N_AUDIT_RESULT")
	Integer auditResult;

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getForeignObject() {
		return foreignObject;
	}

	public void setForeignObject(String foreignObject) {
		this.foreignObject = foreignObject;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

}
