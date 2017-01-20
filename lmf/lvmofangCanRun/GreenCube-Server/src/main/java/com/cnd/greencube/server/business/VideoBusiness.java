/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 视频服务实现类
 * 
 * @author huxg
 * 
 */
public interface VideoBusiness {
	/**
	 * 取得视频
	 * @param seller
	 * @param strictAudited
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<Video> loadVideosForPagelit(String seller, boolean strictAudited, PageInfo pageInfo) throws Exception;
	
	/**
	 * 根据分类获取视频
	 */
	public List<Video> loadVideosByCategoryId(String categoryId, boolean strictAudited, PageInfo pageInfo) throws Exception;

	
	/**
	 * 根据主键取得视频
	 * @param videoId
	 * @return
	 * @throws Exception
	 */
	public Video getVideoById(String videoId) throws Exception;

	/**
	 * 上传视频
	 */
	public void updateVideo(Video video) throws Exception;

	/**
	 * 删除视频
	 */
	public void deleteVideo(String videoId) throws Exception;

	/**
	 * 视频审核
	 */
	public void approveVideo(String videoId, String userid, String username) throws Exception;

	/**
	 * 驳回审核
	 */
	public void rejectVideo(String videoId, String userid, String username) throws Exception;

}
