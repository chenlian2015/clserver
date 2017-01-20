/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cnd.greencube.server.business.CmsBusiness;
import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.CmsChannel;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.CmsService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
/**
 * cms服务实现
 * 
 * @author huxg
 * 
 */
public class CmsServiceImpl extends AbstractServiceImpl implements CmsService.Iface {
	private static final Logger logger = Logger.getLogger(CmsServiceImpl.class);

	@Resource(name = "CmsBusinessImpl")
	protected CmsBusiness cmsBusiness;

	/**
	 * 取得一个栏目下的文章列表
	 */
	@Override
	public String loadArticlesByChannelIdForPaglit(String channelId, boolean onlyReleased, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = cmsBusiness.loadArticlesByChannelIdForPaglit(channelId, onlyReleased, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 搜索文章
	 */
	@Override
	public String searchArticles(String keyword, boolean onlyReleased, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = cmsBusiness.loadArticlesByChannelIdForPaglit(keyword, onlyReleased, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 获取栏目列表
	 */
	@Override
	public String loadChannelsForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsChannel> channels = cmsBusiness.loadChannelsForPagelit(pageInfo);

			// 将子数组存入result
			List<CmsChannel> result = new ArrayList<CmsChannel>();
			Map<String, CmsChannel> mapChannel = new HashMap<String, CmsChannel>();
			for (CmsChannel c : channels) {
				String fjdm = c.getClassCode();
				if (StringUtils.isEmpty(fjdm) || !fjdm.contains(".")) {
					result.add(c);
				} else {
					// 取得父亲的id
					String[] p = fjdm.split("\\.");

					String parentId = p[p.length - 2];
					CmsChannel parent = mapChannel.get(parentId);
					if (null != parent) {
						parent.addSub(c);
					}
				}

				mapChannel.put(c.getId(), c);
			}
			return Message.okMessage(result, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 通过Id获取文章对象
	 */
	@Override
	public String getArticleById(String articleId) throws TException {
		try {
			CmsArticle a = cmsBusiness.getArticleById(articleId);
			return Message.okMessage(a);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新文章信息
	 */
	@Override
	public String updateArticle(String articleJson, String channelId) throws TException {
		try {
			CmsArticle article = JsonUtils.String2Bean(articleJson, CmsArticle.class);
			cmsBusiness.updateArticle(article, channelId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除文章信息
	 */
	@Override
	public String deleteArticle(String articleId) throws TException {
		try {
			cmsBusiness.deleteArticle(articleId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 发布文章
	 */
	@Override
	public String publishArticle(String articleId) throws TException {
		try {
			cmsBusiness.publishArticle(articleId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取消发布文章
	 */
	@Override
	public String unPublishArticle(String articleId) throws TException {
		try {
			cmsBusiness.unPublishArticle(articleId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得栏目
	 */
	@Override
	public String getChannelById(String channelId) throws TException {
		try {
			CmsChannel channel = cmsBusiness.getChannelById(channelId);
			return Message.okMessage(channel);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新栏目
	 */
	@Override
	public String updateChannel(String channelJson) throws TException {
		try {
			cmsBusiness.updateChannel(channelJson);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除栏目
	 */
	@Override
	public String deleteChannel(String channelId) throws TException {
		try {
			cmsBusiness.deleteChannel(channelId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadChannels() throws TException {
		try {
			List<CmsChannel> channels = cmsBusiness.loadChannels();

			// 将子数组存入result
			List<CmsChannel> result = new ArrayList<CmsChannel>();
			Map<String, CmsChannel> mapChannel = new HashMap<String, CmsChannel>();
			for (CmsChannel c : channels) {
				String fjdm = c.getClassCode();
				if (StringUtils.isEmpty(fjdm) || !fjdm.contains(".")) {
					result.add(c);
				} else {
					// 取得父亲的id
					String[] p = fjdm.split("\\.");

					String parentId = p[p.length - 2];
					CmsChannel parent = mapChannel.get(parentId);
					if (null != parent) {
						parent.addSub(c);
					}
				}

				mapChannel.put(c.getId(), c);
			}
			JSONArray ja = JsonUtils.String2JSONArray(JSON.toJSONString(result));
			return Message.okMessage(ja);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
}
