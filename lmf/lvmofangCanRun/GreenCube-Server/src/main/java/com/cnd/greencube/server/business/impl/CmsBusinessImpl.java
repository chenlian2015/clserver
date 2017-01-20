/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.business.CmsBusiness;
import com.cnd.greencube.server.dao.CmsArticleDao;
import com.cnd.greencube.server.dao.CmsChannelDao;
import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.CmsChannel;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;

/**
 * cms服务实现
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("CmsBusinessImpl")
public class CmsBusinessImpl extends BaseBusinessImpl implements CmsBusiness {
	@Resource(name = "CmsArticleDaoImpl")
	protected CmsArticleDao articleDao;

	@Resource(name = "CmsChannelDaoImpl")
	protected CmsChannelDao channelDao;

	/**
	 * 取得一个栏目下的文章列表
	 */
	@Transactional(readOnly = true)
	@Override
	public List<CmsArticle> loadArticlesByChannelIdForPaglit(String channelId, boolean onlyReleased, PageInfo pageInfo) {
		String sql = "select t.id from CmsArticle t where 1 = 1 ";
		String ctql = "select count(t) from CmsArticle t where 1 = 1 ";

		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(channelId)) {
			sql += " and t.channelId = ? ";
			ctql += " and t.channelId = ? ";
			params.add(channelId);
		}

		if (onlyReleased == true) {
			sql += " and isRelease = 50";
			ctql += " and isRelease = 50";
		}
		sql += " order by isTop , order,releaseTime desc";

		return articleDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 搜索文章
	 */
	@Transactional(readOnly = true)
	@Override
	public List<CmsArticle> searchArticlesByKeyword(String keyword, boolean onlyReleased, PageInfo pageInfo) {
		String sql = "select t.id from CmsArticle t where 1 = 1 ";
		String ctql = "select count(t) from CmsArticle t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(keyword)) {
			sql += " and title like ?";
			ctql += " and title like ?";
			params.add("%" + keyword + "%");
		}

		if (onlyReleased == true) {
			sql += " and isRelease = 50";
			ctql += " and isRelease = 50";
		}
		sql += " order by isTop, order,releaseTime desc";

		return articleDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 通过Id获取文章对象
	 */
	@Transactional(readOnly = true)
	@Override
	public CmsArticle getArticleById(String articleId) {
		return articleDao.get(articleId);
	}

	/**
	 * 获取栏目列表
	 */
	@Transactional(readOnly = true)
	@Override
	public List<CmsChannel> loadChannelsForPagelit(PageInfo pageInfo) {
		if (pageInfo == null) {
			String sql = "select t.id from CmsChannel t order by classCode, order ";
			return channelDao.findList(sql);

		} else {
			String sql = "select t.id from CmsChannel t order by classCode , order ";
			String ctql = "select count(t) from CmsChannel t order by classCode , order";
			return channelDao.findList(sql, ctql, null, pageInfo);
		}
	}
	
	@Transactional
	@Override
	public boolean updateArticle(CmsArticle article, String channelId) {
		if (!StringUtils.isEmpty(article.getId())) {
			if (!StringUtils.isEmpty(channelId) && !channelId.equals(article.getChannelId())) {
				CmsChannel channel = channelDao.get(channelId);
				if (channel != null) {
					article.setChannelName(channel.getName());
				}
			}
			articleDao.update(article);
		} else {
			article.setId(null);
			article.setBrowseCount(0);
			article.setCreateTime(new Date());
			article.setIsRelease(CmsArticle.RELEASE_STATUS_UNAUDIT);

			if (!StringUtils.isEmpty(channelId)) {
				CmsChannel channel = channelDao.get(channelId);
				if (channel != null) {
					article.setChannelName(channel.getName());
				}
				article.setChannelId(channelId);
			}
			articleDao.save(article);
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean deleteArticle(String articleId) {
		articleDao.delete(articleId);
		return true;
	}
	
	@Transactional
	@Override
	public boolean publishArticle(String articleId) {
		CmsArticle a = articleDao.get(articleId);
		if (null != a) {
			a.setIsRelease(CmsArticle.RELEASE_STATUS_AUDITED);
			a.setReleaseTime(new Date());
			articleDao.update(a);
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public boolean unPublishArticle(String articleId) {
		CmsArticle a = articleDao.get(articleId);
		if (null != a) {
			a.setIsRelease(CmsArticle.RELEASE_STATUS_UNAUDIT);
			a.setReleaseTime(null);
			articleDao.update(a);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public CmsChannel getChannelById(String channelId) {
		return channelDao.get(channelId);
	}
	
	@Transactional
	@Override
	public boolean updateChannel(String channelJson) {
		JSONArray ja = (JSONArray) JSONArray.parse(channelJson);
		int size = ja.size();
		for (int i = 0; i < size; i++) {
			JSONObject jo = ja.getJSONObject(i);
			CmsChannel channel = (CmsChannel) JSONObject.parseObject(jo.toString(), CmsChannel.class);

			if (StringUtils.isEmpty(jo.getString("name")))
				continue;

			if (!StringUtils.isEmpty(channel.getId())) {
				channel.setClassCode(channel.getId());
				if (channel.getOrder() == null)
					channel.setOrder(99);
				channelDao.update(channel);
			} else {
				channel.setId(null);
				if (channel.getOrder() == null)
					channel.setOrder(99);
				channelDao.save(channel);

				channel.setClassCode(channel.getId());
				channelDao.update(channel);
			}

			JSONArray sub = jo.getJSONArray("sub");
			if (sub != null && sub.size() > 0) {
				// 更新子节点
				for (int j = 0; j < sub.size(); j++) {
					updateChildNode(channel, sub.getJSONObject(j));
				}
			}
		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteChannel(String channelId) {
		channelDao.delete(channelId);
		return true;
	}
	
	void updateChildNode(CmsChannel parent, JSONObject child) {
		CmsChannel childChannel = (CmsChannel) JSONObject.parseObject(child.toString(), CmsChannel.class);
		if (!StringUtils.isEmpty(childChannel.getId())) {
			childChannel.setClassCode(parent.getClassCode() + "." + childChannel.getId());
			if (childChannel.getOrder() == null)
				childChannel.setOrder(99);
			channelDao.update(childChannel);
		} else {
			childChannel.setId(null);
			if (childChannel.getOrder() == null)
				childChannel.setOrder(99);
			channelDao.save(childChannel);

			childChannel.setClassCode(parent.getClassCode() + "." + childChannel.getId());
			channelDao.update(childChannel);
		}

		JSONArray sub = child.getJSONArray("sub");
		if (sub != null && sub.size() > 0) {
			// 更新子节点
			for (int j = 0; j < sub.size(); j++) {
				updateChildNode(childChannel, sub.getJSONObject(j));
			}
		}
	}

	@Override
	public List<CmsChannel> loadChannels() {
			String sql = "select t.id from CmsChannel t order by classCode, order ";
			return channelDao.findList(sql);

		}

}
