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

import com.cnd.greencube.server.business.RequirementBusiness;
import com.cnd.greencube.server.dao.RequirementDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Requirement;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 地区服务类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("RequirementBusinessImpl")
public class RequirementBusinessImpl extends BaseBusinessImpl implements RequirementBusiness {
	@Resource(name = "RequirementDaoImpl")
	private RequirementDao requirementDao;

	@Resource(name = "UserDaoImpl")
	private UserDao userDao;

	/**
	 * 提交一个需求
	 */
	@Transactional
	@Override
	public Requirement submitText(String title, String content, String posterId, String posterIp, int requirementType) {
		Requirement r = new Requirement();
		r.setTitle(title);
		User user = userDao.get(posterId);
		r.setPosterId(posterId);
		r.setPosterName(user.getName());
		r.setPosterIp(posterIp);
		r.setPostTime(new Date());
		r.setContent(content);
		r.setReqType(requirementType);
		r.setPosterPhoto(user.getPhoto());
		r.setReplyAmount(0);
		r.setPraiseAmount(0);
		r.setContentType(Requirement.CONTENT_TYPE_TEXT);
		requirementDao.save(r);
		return r;
	}

	/**
	 * 提交一张照片
	 */
	@Transactional
	@Override
	public Requirement submitPhoto(String title, String content, String multipart, String posterId, String posterIp, int requirementType) {
		Requirement r = new Requirement();
		r.setTitle(title);
		User user = userDao.get(posterId);
		r.setPosterId(posterId);
		r.setPosterName(user.getName());
		r.setPosterIp(posterIp);
		r.setPostTime(new Date());
		r.setContent(content);
		r.setMultipart(multipart);
		r.setReqType(requirementType);
		r.setPosterPhoto(user.getPhoto());
		r.setReplyAmount(0);
		r.setPraiseAmount(0);
		r.setContentType(Requirement.CONTENT_TYPE_PHOTO);
		requirementDao.save(r);
		return r;
	}

	/**
	 * 提交一个视频
	 */
	@Transactional
	@Override
	public Requirement submitVideo(String title, String content, String multipart, String posterId, String posterIp, int requirementType) {
		Requirement r = new Requirement();
		r.setTitle(title);

		User user = userDao.get(posterId);
		r.setPosterId(posterId);
		r.setPosterName(user.getName());
		r.setPosterIp(posterIp);
		r.setPostTime(new Date());
		r.setContent(content);
		r.setMultipart(multipart);
		r.setReqType(requirementType);
		r.setPosterPhoto(user.getPhoto());
		r.setReplyAmount(0);
		r.setPraiseAmount(0);
		r.setContentType(Requirement.CONTENT_TYPE_VIDEO);
		requirementDao.save(r);
		return r;
	}

	/**
	 * 回复一个需求
	 */
	@Transactional
	@Override
	public Requirement replyRequirement(String parentRequirementId, String title, String content, String posterId, String posterIp) {
		Requirement r = new Requirement();
		r.setTitle(title);
		r.setParentReqId(parentRequirementId);
		User user = userDao.get(posterId);
		r.setPosterId(posterId);
		r.setPosterName(user.getName());
		r.setPosterIp(posterIp);
		r.setPostTime(new Date());
		r.setContent(content);
		r.setContentType(Requirement.CONTENT_TYPE_VIDEO);
		r.setReplyAmount(r.getReplyAmount() == null ? 1 : r.getReplyAmount().intValue() + 1);
		requirementDao.save(r);
		return r;
	}

	@Override
	public void deleteRequirement(String requirementId) {
		requirementDao.delete(requirementId);
	}

	/**
	 * 赞
	 */
	@Transactional
	@Override
	public void supportRequirement(String requirementId) {
		Requirement req = requirementDao.get(requirementId);
		req.setPraiseAmount(req.getPraiseAmount() == null ? 1 : req.getPraiseAmount().intValue() + 1);
		requirementDao.update(req);
	}

	/**
	 * 列表
	 * 
	 * @param requirementType
	 * @param pageNum
	 * @return
	 */
	@Override
	public List<Requirement> loadRequirement(int requirementType, PageInfo pageInfo) {
		String sql = "select t.id from Requirement t ";
		String ctql = "select count(t) from Requirement t ";

		List<Object> params = new ArrayList<Object>();
		if (requirementType > 0) {
			sql += " where t.reqType = ? ";
			ctql += " where t.reqType = ? ";
			params.add(requirementType);
		}
		sql += " order by postTime desc";
		return requirementDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	@Override
	public Requirement getRequirement(String requirementId) {
		return requirementDao.get(requirementId);
	}

	@Override
	public void readRequirement(String requirementId) {
		// Requirement req = requirementDao.get(requirementId);
		// requirementDao.update(req);
	}

	@Override
	public List<Requirement> loadReplies(String rid, PageInfo pageInfo) {
		String sql = "select t.id from Requirement t where t.parentReqId = ? order by postTime desc";
		String ctql = "select count(t) from Requirement t where t.parentReqId = ?";
		return requirementDao.findList(sql, ctql, new Object[] { rid }, pageInfo);
	}
	/**
	 * 分页获取需求（依据类别）
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 */
	@Override
	public List<Requirement> loadRequirementByCategoryId(String categoryId, PageInfo pageInfo) {
		String sql = "select t.id from Requirement t where categoryId = ? order by postTime desc";
		String ctql = "select count(t) from Requirement t where categoryId = ? order by postTime desc";
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		List<Requirement> list =  requirementDao.findList(sql, ctql, params.toArray(), pageInfo);
		return list;
	}
	/**
	 * 依据类别得到需求数量
	 * @param categoryId
	 * @return
	 */
	@Override
	public int getRequirementCount(String categoryId) {
		String sql = "select t.id from Requirement t where categoryId = ? order by postTime desc";
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		List<Requirement> list =  requirementDao.findList(sql, params.toArray());
		int count = list.size();
		return count;
	}

}
