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
 * 培训实体
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_TRAIN")
public class Train extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 培训名称
	@Column(name = "C_NAME")
	String name;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 描述
	@Column(name = "C_DESC")
	String desc;

	// 试卷类型 0 普通组卷 1 智能组卷
	@Column(name = "N_COMPOSE_METHOD")
	Integer composeMethod;

	// 题目总数
	@Column(name = "N_QUESTION_COUNT")
	Integer topicCount;

	// 类别ID
	@Column(name = "C_TRAIN_CATEGORY_ID")
	String trainCategoryId;

	// 类别名称
	@Column(name = "C_TRAIN_CATEGORY_NAME")
	String categoryName;

	@Transient
	List<TrainQuestion> questions = new ArrayList<TrainQuestion>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getComposeMethod() {
		return composeMethod;
	}

	public void setComposeMethod(Integer composeMethod) {
		this.composeMethod = composeMethod;
	}

	public Integer getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTrainCategoryId() {
		return trainCategoryId;
	}

	public void setTrainCategoryId(String trainCategoryId) {
		this.trainCategoryId = trainCategoryId;
	}

	public List<TrainQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TrainQuestion> questions) {
		if (null == this.questions)
			this.questions = new ArrayList<TrainQuestion>();
		this.questions = questions;
	}

}
