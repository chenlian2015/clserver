package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 评论Dao
 * 
 * @author huxg
 * 
 */
public interface CommentDao extends BaseDao<Comment, String> {
	/**
	 * 取得TopN评论数据
	 * @param topN
	 * @return
	 */
	List<Comment> loadCommentsByForeignIdTopN(String foreignId, int topN);

	/**
	 * 分页取得评论数据
	 * @param pageInfo
	 * @return
	 */
	List<Comment> loadCommentsByForeignId(String foreignId, PageInfo pageInfo);
}
