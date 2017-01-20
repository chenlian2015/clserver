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

import com.cnd.greencube.server.business.CommentBusiness;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.CommentService;
import com.cnd.greencube.server.util.PageInfo;
/**
 * 评论服务实现类
 * 
 * @author huxg
 * 
 */
public class CommentServiceImpl extends AbstractServiceImpl implements CommentService.Iface {
	private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

	@Resource(name = "CommentBusinessImpl")
	protected CommentBusiness commentBusiness;

	/**
	 * 取得TopN评论数据
	 * 
	 * @param topN
	 * @return
	 */
	@Override
	public String loadCommentsByForeignIdTopN(String foreignId, int topN) throws TException {
		try {
			List<Comment> comments = commentBusiness.loadCommentsByForeignIdTopN(foreignId, topN);
			return Message.okMessage(comments);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页取得评论数据
	 * 
	 * @param pageInfo
	 * @return
	 */
	@Override
	public String loadCommentsByForeignId(String foreignId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Comment> comments = commentBusiness.loadCommentsByForeignId(foreignId, pageInfo);
			return Message.okMessage(comments, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String submitComment(String userid, String content, String commentObjId, String commentObjName, int score) throws TException {
		try {
			commentBusiness.submitComment(userid, content, commentObjId, commentObjName, score);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
}
