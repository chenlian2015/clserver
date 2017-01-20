/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.VideoBusiness;
import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.VideoService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
/**
 * 视频服务实现类
 * 
 * @author huxg
 * 
 */
public class VideoServiceImpl extends AbstractServiceImpl implements VideoService.Iface {
	private static final Logger logger = Logger.getLogger(VideoServiceImpl.class);

	@Resource(name = "VideoBusinessImpl")
	VideoBusiness videoBusiness;

	/**
	 * 取得商家发布的视频
	 */
	@Override
	public String loadVideosForPagelit(String seller, boolean strictAudited, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Video> videos = videoBusiness.loadVideosForPagelit(seller, strictAudited, pageInfo);
			return Message.okMessage(videos, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据分类获取视频
	 */
	@Override
	public String loadVideosByCategoryId(String categoryId, boolean strictAudited, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Video> videos = videoBusiness.loadVideosByCategoryId(categoryId, strictAudited, pageInfo);
			return Message.okMessage(videos, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据Id取得视频
	 */
	@Override
	public String getVideoById(String videoId) throws TException {
		try {
			Video v = videoBusiness.getVideoById(videoId);
			return Message.okMessage(v);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String approveVideo(String videoId, String userid, String username) throws TException {
		try {
			videoBusiness.approveVideo(videoId, userid, username);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String deleteVideo(String videoId) throws TException {
		try {
			videoBusiness.deleteVideo(videoId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String rejectVideo(String videoId, String userid, String username) throws TException {
		try {
			videoBusiness.rejectVideo(videoId, userid, username);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateVideo(String videoJson) throws TException {
		try {
			Video video = JsonUtils.String2Bean(videoJson, Video.class);
			videoBusiness.updateVideo(video);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
