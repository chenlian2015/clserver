/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.CommentBusiness;
import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 评论服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("CommentBusinessImpl")
public class CommentBusinessImpl extends BaseBusinessImpl implements CommentBusiness {
	@Resource(name = "CommentDaoImpl")
	protected CommentDao commentDao;

	@Resource(name = "UserDaoImpl")
	protected UserDao userDao;

	/**
	 * 取得TopN评论数据
	 * 
	 * @param topN
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Comment> loadCommentsByForeignIdTopN(String foreignId, int number) {
		return commentDao.loadCommentsByForeignIdTopN(foreignId, number);
	}

	/**
	 * 分页取得评论数据
	 * 
	 * @param pageInfo
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Comment> loadCommentsByForeignId(String foreignId, PageInfo pageInfo) {
		return commentDao.loadCommentsByForeignId(foreignId, pageInfo);
	}

	@Transactional
	@Override
	public boolean submitComment(String userid, String content, String commentObjId, String commentObjName, int score) {
		Comment c = new Comment();
		c.setComment(content);
		c.setCommentTime(new Date());
		c.setForeignId(commentObjId);
		c.setForeignTable(commentObjName);
		c.setUserId(userid);

		User user = userDao.get(userid);
		if (user != null) {
			c.setUserName(user.getName());
			c.setUserPhoto(user.getPhoto());
		}

		commentDao.save(c);
		return true;
	}

}
