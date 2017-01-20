/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.api.MailAPI;
import com.cnd.greencube.server.business.HealthyMemberBusiness;
import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.LetterDao;
import com.cnd.greencube.server.dao.MemberHealthyDao;
import com.cnd.greencube.server.dao.MemberHealthyDetailDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.entity.Letter;
import com.cnd.greencube.server.entity.MemberHealthy;
import com.cnd.greencube.server.entity.MemberHealthyDetail;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 健康会会员服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("HealthyMemberBusinessImpl")
public class HealthyMemberBusinessImpl extends BaseBusinessImpl implements HealthyMemberBusiness {
	@Resource(name = "MemberHealthyDaoImpl")
	protected MemberHealthyDao memberHealthyDao;

	@Resource(name = "MemberHealthyDetailDaoImpl")
	protected MemberHealthyDetailDao memberHealthyDetailDao;

	@Resource(name = "AreaDaoImpl")
	protected AreaDao areaDao;

	@Resource(name = "AuditDaoImpl")
	AuditDao auditDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "LetterDaoImpl")
	LetterDao letterDao;

	/**
	 * 更新( 存在就修改，否则就新增)健康会信息
	 * 
	 * @param applyJson
	 * @Transactional public void save(T entity) { dao.save(entity); } --
	 *                健康会json
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean submitApply(User user, MemberHealthy memberHealthy, MemberHealthyDetail detail) {
		// 判断该用户是否已经提交过了申请
		User realUser = userDao.get(user.getId());
		if (realUser == null)
			throw new NotifyException("未注册用户无权申请健康会成员");

		if (StringUtils.isEmpty(realUser.getNickname()) || !realUser.getNickname().equals(user.getNickname())) {
			realUser.setNickname(user.getNickname());
			userDao.update(realUser);
		}

		if (!StringUtils.isEmpty(memberHealthy.getId())) {
			memberHealthy.setIsAudit(0);
			memberHealthyDao.update(memberHealthy);
		} else {
			memberHealthy.setId(null);
			memberHealthy.setUserid(realUser.getId());

			if (!StringUtils.isEmpty(memberHealthy.getProvinceId())) {
				Area area = areaDao.get(memberHealthy.getProvinceId());
				memberHealthy.setProvinceName(area.getName());
			}
			if (!StringUtils.isEmpty(memberHealthy.getCityId())) {
				Area area = areaDao.get(memberHealthy.getCityId());
				memberHealthy.setCityName(area.getName());
			}

			memberHealthy.setFansAmount(0);
			memberHealthy.setFocusAmount(0);
			memberHealthy.setCommentAmount(0);
			memberHealthy.setTotalConsume(BigDecimal.ZERO);
			memberHealthy.setIsAudit(0);
			memberHealthy.setIsValid(1);
			memberHealthy.setIsValid(1);
			memberHealthyDao.save(memberHealthy);
		}

		// 店铺详细信息-判断是更新还是创建操作
		if (!StringUtils.isEmpty(detail.getId())) {
			detail.setRegistTime(new Date());
			memberHealthyDetailDao.update(detail);
		} else {
			detail.setId(null);
			detail.setHealthyId(memberHealthy.getId());
			detail.setRegistTime(new Date());
			memberHealthyDetailDao.save(detail);
		}
		return true;
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
	@Transactional
	@Override
	public boolean auditHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus) {
		MemberHealthy memberHealthy = memberHealthyDao.get(healthyUserId);
		memberHealthy.setIsAudit(auditStatus);
		memberHealthyDao.update(memberHealthy);

		auditDao.audit(memberHealthy.getId(), MemberHealthy.class, auditUserId, auditStatus);
		User user = userDao.get(memberHealthy.getUserid());
		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】健康会申请已获批准";
			String content = "恭喜您" + user.getName() + "先生/小姐，您的健康会（" + memberHealthy.getMemberCode() + "）申请已被通过，您现在可以进入“绿魔方”享受您的健康服务了！";
			MailAPI.sendMail(user.getEmail(), title, content, false);

			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
	@Transactional
	@Override
	public boolean rejectHealthyMemberApply(String healthyUserId, String auditUserId, int auditStatus) {
		MemberHealthy memberHealthy = memberHealthyDao.get(healthyUserId);
		memberHealthy.setIsAudit(auditStatus);
		memberHealthyDao.update(memberHealthy);

		auditDao.audit(memberHealthy.getId(), MemberHealthy.class, auditUserId, auditStatus);
		com.cnd.greencube.server.entity.User user = userDao.get(memberHealthy.getUserid());
		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】健康会申请已被驳回";
			String content = "很抱歉" + user.getName() + "先生/小姐，您的健康会（" + memberHealthy.getMemberCode() + "）申请已被驳回，请您与客服联系！";
			MailAPI.sendMail(user.getEmail(), title, content, false);
			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 分页获取待审核健康会
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组t.IdMemberHealthy
	 */
	@Override
	public List<MemberHealthy> loadAppliedHealthyMembersForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from MemberHealthy t where t.isAudit = 0";
		String ctql = "select count(t) from MemberHealthy t where t.isAudit = 0";
		return memberHealthyDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public List<MemberHealthy> loadApprovedHealthyMembersForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from MemberHealthy t where t.isAudit = 1";
		String ctql = "select count(t) from MemberHealthy t where t.isAudit = 1";
		return memberHealthyDao.findList(sql, ctql, null, pageInfo);
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
	@Transactional
	@Override
	public boolean sendInnerLetter(String healthyUserId, String title, String content) {
		Letter letter = new Letter();
		letter.setReceiverId(healthyUserId);
		letter.setTitle(title);
		letter.setContent(content);
		letter.setSenderId(null);
		letter.setSendTime(new Date());
		letterDao.save(letter);
		return true;
	}

	/**
	 * 得到健康会成员明细
	 */
	@Override
	public MemberHealthyDetail getHealthyDetail(String healthyId) {
		String sql = "select t.id from MemberHealthyDetail t where healthyId = ? ";
		List<MemberHealthyDetail> details = memberHealthyDetailDao.findList(sql, new Object[] { healthyId });
		MemberHealthyDetail detail = null;
		if (details != null && details.size() > 0) {
			detail = details.get(0);
		}
		return detail;
	}

	/**
	 * 根据id获得健康会
	 * 
	 * @param healthyId
	 *            -- 健康会id
	 * @return 健康会的json对象
	 */
	@Override
	public MemberHealthy getHealthyById(String healthyId) {
		return memberHealthyDao.get(healthyId);
	}

}
