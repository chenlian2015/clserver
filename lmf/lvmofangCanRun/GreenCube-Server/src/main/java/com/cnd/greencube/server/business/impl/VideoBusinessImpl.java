/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.VideoBusiness;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.VideoDao;
import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 视频服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("VideoBusinessImpl")
public class VideoBusinessImpl extends BaseBusinessImpl implements VideoBusiness {
	@Resource(name = "VideoDaoImpl")
	VideoDao videoDao;

	@Resource(name = "AuditDaoImpl")
	AuditDao auditDao;

	@Override
	public List<Video> loadVideosForPagelit(String seller, boolean strictAudited, PageInfo pageInfo) {
		String sql = "select t.id from Video t where sellerId = ?";
		String ctql = "select count(t) from Video t where sellerId = ?";

		List<Object> params = new ArrayList<Object>();
		params.add(seller);

		if (strictAudited == true) {
			sql += "and statusAudit = 1";
			ctql += "and statusAudit = 1";
		}
		sql += " order by uploadTime";

		return videoDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 根据分类获取视频
	 */
	@Override
	public List<Video> loadVideosByCategoryId(String categoryId, boolean strictAudited, PageInfo pageInfo) {
		String sql = "select t.id from Video t where isValid = 1 ";
		String ctql = "select count(t) from Video t where isValid = 1 ";

		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(categoryId)) {
			sql += " and t.categoryId = ? ";
			ctql += " and t.categoryId = ? ";
			params.add(categoryId);
		}
		if (strictAudited == true) {
			sql += "and statusAudit = 1";
			ctql += "and statusAudit = 1";
		}

		sql += " order by uploadTime";

		return videoDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	@Override
	public Video getVideoById(String videoId) {
		return videoDao.get(videoId);
	}

	/**
	 * 上传视频
	 */
	@Transactional
	@Override
	public void updateVideo(Video video) {
		videoDao.save(video);
	}

	/**
	 * 删除视频
	 */
	@Transactional
	@Override
	public void deleteVideo(String videoId) {
		videoDao.delete(videoId);
	}

	/**
	 * 视频审核
	 */
	@Transactional
	@Override
	public void approveVideo(String videoId, String userid, String username) {
		Video video = videoDao.get(videoId);
		video.setStatusAudit(1);
		videoDao.update(video);

		// 创建审核记录
		auditDao.audit(videoId, Video.class, userid, 1);
	}

	/**
	 * 驳回审核
	 */
	@Transactional
	@Override
	public void rejectVideo(String videoId, String userid, String username) {
		Video video = videoDao.get(videoId);
		video.setStatusAudit(0);
		videoDao.update(video);

		// 创建审核记录
		auditDao.audit(videoId, Video.class, userid, 0);
	}
}
