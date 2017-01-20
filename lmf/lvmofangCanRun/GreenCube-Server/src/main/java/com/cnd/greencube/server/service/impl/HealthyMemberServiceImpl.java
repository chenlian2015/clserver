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

import com.cnd.greencube.server.business.HealthyMemberBusiness;
import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.entity.MemberHealthy;
import com.cnd.greencube.server.entity.MemberHealthyDetail;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.HealthyMemberService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
/**
 * 健康会会员服务实现类
 * 
 * @author huxg
 * 
 */
public class HealthyMemberServiceImpl extends AbstractServiceImpl implements HealthyMemberService.Iface {
	private static final Logger logger = Logger.getLogger(HealthyMemberServiceImpl.class);

	@Resource(name = "HealthyMemberBusinessImpl")
	protected HealthyMemberBusiness healthyMemberBusiness;

	/**
	 * 更新( 存在就修改，否则就新增)健康会信息
	 * 
	 * @param applyJson
	 * @Transactional public void save(T entity) { dao.save(entity); } --
	 *                健康会json
	 * @return 成功与否标志
	 */
	@Override
	public String submitApply(String userJson, String memberJson, String detailJson) throws TException {
		try {
			User user = JsonUtils.String2Bean(userJson, User.class);
			MemberHealthy member = JsonUtils.String2Bean(memberJson, MemberHealthy.class);
			MemberHealthyDetail detail = JsonUtils.String2Bean(detailJson, MemberHealthyDetail.class);

			if (user == null)
				return Message.errorMessage("Error[001]提交失败，参数有误");

			// 检查店铺信息是否都填写了
			if (member == null)
				return Message.errorMessage("Error[004]提交失败，参数有误");

			if (detail == null)
				return Message.errorMessage("Error[005]提交失败，参数有误");

			if (StringUtils.isEmptyAfterTrim(user.getId()))
				throw new NotifyException("Error[002]提交失败，参数有误");

			if (StringUtils.isEmptyAfterTrim("nickname"))
				throw new NotifyException("Error[003]提交失败，参数有误");

			if (StringUtils.isEmptyAfterTrim(member.getName()))
				return Message.errorMessage("Error[006]提交失败，参数有误");

			if (StringUtils.isEmptyAfterTrim(detail.getIdentityNum()))
				return Message.errorMessage("Error[009]提交失败，参数有误");

			healthyMemberBusiness.submitApply(user, member, detail);
			return Message.okMessage();
		} catch (NotifyException ne) {
			return Message.errorMessage(ne.getMessage());
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改 健康会信息
	 * 
	 * @param applyJson
	 *            健康会json
	 * @return 成功与否标志
	 */
	@Override
	public String updateHealthy(String userJson, String memberJson, String detailJson) throws TException {
		return submitApply(userJson, memberJson, detailJson);
	}

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
	@Override
	public String auditHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus) {
		try {
			healthyMemberBusiness.auditHealthyMemberApply(healthyUserId, auditUserId, auditStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

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
	@Override
	public String rejectHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus) {
		try {
			healthyMemberBusiness.rejectHealthyMemberApply(healthyUserId, auditUserId, auditStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页获取待审核健康会
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组t.emberHealthy
	 */
	@Override
	public String loadAppliedHealthyMembersForPagelit(int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<MemberHealthy> members = healthyMemberBusiness.loadAppliedHealthyMembersForPagelit(pageInfo);
			return Message.okMessage(members, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public String loadApprovedHealthyMembersForPagelit(int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<MemberHealthy> members = healthyMemberBusiness.loadApprovedHealthyMembersForPagelit(pageInfo);
			return Message.okMessage(members, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 给健康会成员发内部信
	 * 
	 * @param healthyUserId
	 *            健康会成员id
	 * @param title
	 *            主题
	 * @param content
	 */
	@Override
	public String sendInnerLetter(String healthyUserId, String title, String content) {
		try {
			healthyMemberBusiness.sendInnerLetter(healthyUserId, title, content);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 得到健康会成员明细
	 */
	@Override
	public String getHealthyDetail(String healthyId) {
		try {
			MemberHealthyDetail d = healthyMemberBusiness.getHealthyDetail(healthyId);
			return Message.okMessage(d);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据id获得健康会
	 * 
	 * @param healthyId
	 *            -- 健康会id
	 * @return 健康会的json对象
	 */
	@Override
	public String getHealthyById(String healthyId) {
		try {
			MemberHealthy m = healthyMemberBusiness.getHealthyById(healthyId);
			return Message.okMessage(m);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
