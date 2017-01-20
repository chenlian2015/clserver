/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训问题实体
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_TRAIN_QUESTION")
public class TrainQuestion extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 题目名称
	@Column(name = "C_NAME")
	String name;

	// 试卷Id
	@Column(name = "C_TRAIN_ID")
	String trainId;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 试题答案
	@Column(name = "C_ANSWER")
	String answer;

	// 类型
	@Column(name = "N_TYPE")
	Integer type;

	@Transient
	List<TrainQuestionOption> options = new ArrayList<TrainQuestionOption>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<TrainQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<TrainQuestionOption> options) {
		this.options = options;
	}

	public void addOption(TrainQuestionOption option) {
		if (this.options == null)
			this.options = new ArrayList<TrainQuestionOption>();
		this.options.add(option);
	}
}
