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

/**
 * 信息发布文章信息
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_CMS_ARTICLE")
public class CmsArticle extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 待审核
	public static final int RELEASE_STATUS_UNAUDIT = 10;

	// 已审核
	public static final int RELEASE_STATUS_AUDITED = 30;

	// 栏目ID
	@Column(name = "C_CHANNEL_ID")
	private String channelId;

	// 栏目名称
	@Column(name = "C_CHANNEL_NAME")
	String channelName;

	// 文章标题
	@Column(name = "C_TITLE")
	String title;

	// 文章内容
	@Column(name = "C_CONTENT")
	String content;

	// 首图
	@Column(name = "C_FIRST_PICTURE")
	String firstPicture;

	// 发布者名称
	@Column(name = "C_AUTHOR_ID")
	String authorId;

	// 发布者名称
	@Column(name = "C_AUTHOR_NAME")
	String authorName;

	// 状态 10 - 草稿， 20 - 待审核，50 - 已发布（审核通过）
	@Column(name = "N_IS_RELEASE")
	Integer isRelease;

	// 发布时间
	@Column(name = "D_RELEASE_TIME")
	Date releaseTime;

	// 添加时间
	@Column(name = "D_CREATE_TIME")
	Date createTime;

	// 浏览次数
	@Column(name = "N_BROWSE_COUNT")
	Integer browseCount;

	// 文章是否置顶
	@Column(name = "N_IS_TOP")
	Integer isTop;

	// 文章排序
	@Column(name = "N_ORDER")
	Integer order;

	// 模板名称
	@Column(name = "C_TEMPLATE_NAME")
	String templateName;

	// 编辑器类型，1 - kindeditor，2 - 普通的textarea
	@Column(name = "C_EDITOR_TYPE")
	String editorType;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

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

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(Integer isRelease) {
		this.isRelease = isRelease;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}

}
