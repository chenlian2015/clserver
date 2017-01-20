package com.cnd.greencube.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 培训类别
 * 
 * @author huxg
 */
@Entity
@Table(name = "T_TRAIN_CATEGORY")
public class TrainCategory extends BaseEntity {
	private static final long serialVersionUID = 7566968484905610915L;

	// 类别名称
	@Column(name = "C_NAME")
	String name;

	// 类别名称
	@Column(name = "N_ORDER")
	Integer order;

	// 创建时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	@Transient
	List<Train> examlist = new ArrayList<Train>();

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

	public List<Train> getExamlist() {
		return examlist;
	}

	public void setExamlist(List<Train> examlist) {
		this.examlist = examlist;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}