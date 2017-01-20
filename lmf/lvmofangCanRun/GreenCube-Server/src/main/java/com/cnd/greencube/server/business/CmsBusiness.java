/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.CmsChannel;
import com.cnd.greencube.server.util.PageInfo;

/**
 * cms服务实现
 * 
 * @author huxg
 * 
 */
public interface CmsBusiness {

	/**
	 * 获取文章列表
	 */
	List<CmsArticle> loadArticlesByChannelIdForPaglit(String channelId, boolean onlyReleased, PageInfo pageInfo);

	/**
	 * 查询文章
	 */
	List<CmsArticle> searchArticlesByKeyword(String keyword, boolean onlyReleased, PageInfo pageInfo);

	/**
	 * 通过Id获取文章对象
	 */
	CmsArticle getArticleById(String articleId);

	/**
	 * 更新文章信息
	 */
	boolean updateArticle(CmsArticle article, String channelId);

	/**
	 * 删除文章信息
	 */
	boolean deleteArticle(String articleId);

	/**
	 * 发布文章
	 */
	boolean publishArticle(String articleId);

	/**
	 * 取消发布文章
	 */
	boolean unPublishArticle(String articleId);

	/**
	 * 获取栏目列表(分页)
	 */
	List<CmsChannel> loadChannelsForPagelit(PageInfo pageInfo);
	/**
	 * 获取栏目列表（不分页）
	 */
	List<CmsChannel> loadChannels();

	/**
	 * 取得栏目
	 */
	CmsChannel getChannelById(String channelId);

	/**
	 * 更新栏目(没有就新增，有就修改)
	 */
	boolean updateChannel(String channelJson);

	/**
	 * 删除栏目
	 */
	boolean deleteChannel(String channelId);
}
