/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Requirement;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 需求大厅业务类
 * 
 * @author huxg
 * 
 */
public interface RequirementBusiness {

	/**
	 * 提交需求
	 * 
	 * @param title
	 *            -- 标题
	 * @param content
	 *            -- 内容
	 * @param parentRequirementId
	 *            -- 父需求id
	 * @param posterId
	 *            -- 发布者id
	 * @param posterIp
	 *            -- 发布者ip地址
	 * @param requirementType
	 *            -- 需求类别， 1-我要， 2-我有
	 * 
	 * @param title
	 * @param content
	 * @param posterId
	 * @param posterIp
	 * @param requirementType
	 */
	public Requirement submitText(String title, String content, String posterId, String posterIp, int requirementType);

	public Requirement submitPhoto(String title, String content, String multipart, String posterId, String posterIp, int requirementType);

	public Requirement submitVideo(String title, String content, String multipart, String posterId, String posterIp, int requirementType);

	/**
	 * 回复需求
	 * 
	 * @param parentRequirementId
	 *            -- 需求id
	 * @param title
	 *            -- 标题
	 * @param content
	 *            -- 内容
	 * @param posterId
	 *            -- 发布者id
	 * @param posterIp
	 *            -- 发布者ip地址
	 * @param requirementType
	 *            -- 类型
	 * 
	 * @param parentRequirementId
	 * @param title
	 * @param content
	 * @param posterId
	 * @param posterIp
	 */
	public Requirement replyRequirement(String parentRequirementId, String title, String content, String posterId, String posterIp);

	/**
	 * 删除需求
	 * 
	 * @param requirementId
	 *            -- 需求id
	 * @return 返回成功与否
	 * 
	 * @param requirementId
	 */
	public void deleteRequirement(String requirementId);

	/**
	 * 赞一个需求
	 * 
	 * @param requirementId
	 * @return 成功与否标志
	 * 
	 * @param requirementId
	 */
	public void supportRequirement(String requirementId);

	/**
	 * 分页返回需求json数组
	 * 
	 * @param requirementType
	 *            1-我要，2-我有
	 * @param pageNum
	 *            -- 页数
	 * 
	 * @param requirementType
	 * @param pageNum
	 */
	public List<Requirement> loadRequirement(int requirementType, PageInfo pageInfo);

	/**
	 * 根据id读取一条需求
	 * 
	 * @param requireimentId
	 *            -- 需求id
	 * @return 返回需求json对象
	 * 
	 * @param requirementId
	 */
	public Requirement getRequirement(String requirementId);

	/**
	 * 根据主键编号获取需求，本方法会增加requirement的点击数
	 * 
	 * @param requirementId
	 *            -- 需求id
	 * @return 返回需求json对象
	 * 
	 * @param requirementId
	 */
	public void readRequirement(String requirementId);
	
	/**
	 * 返回需求恢复列表
	 * @param rid
	 * @param pageInfo
	 * @return
	 */
	List<Requirement> loadReplies(String rid, PageInfo pageInfo);
	/**
	 * 分页获取需求（依据类别）
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 */
	List<Requirement> loadRequirementByCategoryId(String categoryId,PageInfo pageInfo);
	/**
	 * 依据类别得到需求数量
	 * @param categoryId
	 * @return
	 */
	int getRequirementCount(String categoryId);
	
}
