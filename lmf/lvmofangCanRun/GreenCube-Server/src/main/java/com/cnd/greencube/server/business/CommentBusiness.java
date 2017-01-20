/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 评论服务实现类
 * 
 * @author huxg
 * 
 */
public interface CommentBusiness {

	/**
	 * 取得TopN评论数据
	 * 
	 * @param topN
	 * @return
	 */
	List<Comment> loadCommentsByForeignIdTopN(String foreignId, int number);

	/**
	 * 分页取得评论数据
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Comment> loadCommentsByForeignId(String foreignId, PageInfo pageInfo);

	/**
	 * 提交评论
	 * 
	 * @param userid
	 * @param content
	 * @param commentObjId
	 * @param commentObjName
	 * @param score
	 * @return @
	 */
	boolean submitComment(String userid, String content, String commentObjId, String commentObjName, int score);
}
