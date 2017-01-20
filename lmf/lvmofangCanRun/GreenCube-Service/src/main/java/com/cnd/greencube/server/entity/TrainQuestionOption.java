package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * @Author 程辉
 * 题目项实体类
 * 
 */
@Entity
@Table(name = "T_TRAIN_QUESTION_OPTION")
public class TrainQuestionOption extends BaseEntity {
	private static final long serialVersionUID = -3540306705321992845L;

	// 题目选项
	@Column(name = "C_OPTION_ITEM")
	String optionItem;

	// 题目Id
	@Column(name = "C_QUESTION_ID")
	String questionId;

	// 是否正确
	@Column(name = "N_IS_RIGHT")
	Integer isRight;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 选项序列号
	@Column(name = "C_ITEM_SEQ")
	String itemSeq;

	// 选项序列号
	@Transient
	boolean sel;

	public String getOptionItem() {
		return optionItem;
	}

	public void setOptionItem(String optionItem) {
		this.optionItem = optionItem;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public Integer getIsRight() {
		return isRight;
	}

	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getItemSeq() {
		return itemSeq;
	}

	public void setItemSeq(String itemSeq) {
		this.itemSeq = itemSeq;
	}

	public boolean isSel() {
		return sel;
	}

	public void setSel(boolean sel) {
		this.sel = sel;
	}

}
