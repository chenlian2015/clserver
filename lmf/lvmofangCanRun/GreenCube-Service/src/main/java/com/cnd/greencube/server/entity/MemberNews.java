package com.cnd.greencube.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 卖家资讯/通知表
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_MEMBER_NEWS")
public class MemberNews extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 咨询标题
	@Column(name = "C_TITLE")
	String title;

	// 咨询内容
	@Column(name = "C_CONTENT")
	String content;

	// 咨询类别 1-新闻，2-通知
	@Column(name = "N_TYPE")
	Integer type;

	// 店铺id
	@Column(name = "C_SELLER_ID")
	String sellerId;

	// 店铺名称
	@Column(name = "C_SELLER_NAME")
	String sellerName;

	// 发布时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 咨询图片
	@Column(name = "C_PIC_PATH")
	String pic;

	// 点击量
	@Column(name = "N_ORDER")
	Integer order;

	// 点击量
	@Column(name = "N_CLICK_COUNT")
	Integer clickCount;

	// 是否有效, 0 无效， 1-有效
	@Column(name = "N_IS_VALID")
	Integer isValid;

	// 置顶状态,0-不置顶， 1-置顶
	@Column(name = "N_IS_TOP")
	Integer isTop;

	// 审核状态,0-未审核， 1-已审核
	@Column(name = "N_STATUS_AUDIT")
	Integer statusAudit;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(Integer statusAudit) {
		this.statusAudit = statusAudit;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

}