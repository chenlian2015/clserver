package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 评论数据访问类实现
 * 
 * @author huxg
 * 
 */
@Repository("CommentDaoImpl")
public class CommentDaoImpl extends RedisDaoSupportImpl<Comment, String> implements CommentDao {

	/**
	 * TopN获得评论
	 */
	@Override
	public List<Comment> loadCommentsByForeignIdTopN(String foreignId, int topN) {
		String sql = "select t.id from Comment t where t.foreignId = ? order by commentTime desc";
		return super.findList(sql, new Object[]{foreignId}, 0, topN);
	}

	/**
	 * 分页取得评论数据
	 */
	@Override
	public List<Comment> loadCommentsByForeignId(String foreignId, PageInfo pageInfo) {
		String sql = "select t.id from Comment t where t.foreignId = ? order by commentTime desc";
		String ctql = "select t.id from Comment t where t.foreignId = ?";
		return super.findList(sql, ctql, new Object[]{foreignId}, pageInfo);
	}
}
