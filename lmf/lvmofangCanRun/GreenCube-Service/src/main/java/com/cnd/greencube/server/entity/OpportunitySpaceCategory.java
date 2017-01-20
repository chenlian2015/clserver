package com.cnd.greencube.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 工作空间-分类管理
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_OPPORTUNITY_SPACE_CATEGORY")
public class OpportunitySpaceCategory extends BaseEntity {
	@Column(name = "C_CLASS_CODE")
	String classCode;

	@Column(name = "C_NAME")
	String name;

	@Column(name = "N_ORDER")
	Integer order;
	
	@Column(name = "N_RECOMMEND_STATUS")
	Integer recommendStatus;//是否推荐到首页

	@Transient
	List<OpportunitySpaceCategory> subCategory = new ArrayList<OpportunitySpaceCategory>();

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<OpportunitySpaceCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<OpportunitySpaceCategory> subCategory) {
		this.subCategory = subCategory;
	}
   
	public Integer getRecommendStatus() {
		return recommendStatus;
	}

	public void setRecommendStatus(Integer recommendStatus) {
		this.recommendStatus = recommendStatus;
	}

	public void addSubCategory(OpportunitySpaceCategory sub) {
		this.subCategory.add(sub);
	}

}
