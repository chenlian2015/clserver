/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.MemberHealthy;
import com.cnd.greencube.server.entity.MemberHealthyDetail;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;


/**
 * 健康会会员服务实现类
 * 
 * @author huxg
 * 
 */
public interface HealthyMemberBusiness {
	/**
	 * 更新( 存在就修改，否则就新增)健康会信息
	 * 
	 * @param applyJson
	 * @Transactional void save(T entity) { dao.save(entity); } --
	 *                健康会json
	 * @return 成功与否标志
	 */
	boolean submitApply(User user, MemberHealthy memberHealthy, MemberHealthyDetail detail);

	/**
	 * 审核通过一个健康会
	 * 
	 * @param healthyUserId
	 *            -- 健康会id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditStatus
	 *            -- 审核状态
	 * @return 成功与否标识
	 */
	boolean auditHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus);

	/**
	 * 审核驳回一个健康会
	 * 
	 * @param healthyUserId
	 *            -- 健康会id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditStatus
	 *            -- 审核状态
	 * @return 成功与否标识
	 */
	boolean rejectHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus);

	/**
	 * 分页获取待审核健康会
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组t.idMemberHealthy
	 */
	List<MemberHealthy> loadAppliedHealthyMembersForPagelit(PageInfo pageInfo);

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	List<MemberHealthy> loadApprovedHealthyMembersForPagelit(PageInfo pageInfo);

	/**
	 * 给健康会成员发内部信
	 * 
	 * @param healthyUserId
	 *            健康会成员id
	 * @param title
	 *            主题
	 * @param content
	 */
	boolean sendInnerLetter(String healthyUserId, String title, String content);

	/**
	 * 得到健康会成员明细
	 */
	MemberHealthyDetail getHealthyDetail(String healthyId);

	/**
	 * 根据id获得健康会
	 * 
	 * @param healthyId
	 *            -- 健康会id
	 * @return 健康会的json对象
	 */
	MemberHealthy getHealthyById(String healthyId);
}
