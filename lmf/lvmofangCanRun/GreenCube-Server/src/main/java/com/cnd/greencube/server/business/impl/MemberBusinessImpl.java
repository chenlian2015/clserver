package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.api.MailAPI;
import com.cnd.greencube.server.business.MemberBusiness;
import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.FiManagerFeeDao;
import com.cnd.greencube.server.dao.FiMemberFeeDao;
import com.cnd.greencube.server.dao.MemberDao;
import com.cnd.greencube.server.dao.MemberNewsDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.FiMemberFee;
import com.cnd.greencube.server.entity.Member;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

@SuppressWarnings("rawtypes")
@Service("MemberBusinessImpl")
public class MemberBusinessImpl extends BaseBusinessImpl implements MemberBusiness {
	@Resource(name = "MemberDaoImpl")
	protected MemberDao memberDao;

	@Resource(name = "AreaDaoImpl")
	protected AreaDao areaDao;

	@Resource(name = "CommentDaoImpl")
	CommentDao commentDao;

	@Resource(name = "MemberNewsDaoImpl")
	MemberNewsDao memberNewsDao;

	@Resource(name = "AuditDaoImpl")
	AuditDao auditDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "CodeHealthyCategoryDaoImpl")
	CodeHealthyCategoryDao codeHealthyCategoryDao;

	@Resource(name = "FiMemberFeeDaoImpl")
	FiMemberFeeDao memberFeeDao;

	@Resource(name = "FiManagerFeeDaoImpl")
	FiManagerFeeDao managerFeeDao;

	/**
	 * 获取一个店铺历史会员费缴纳列表
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	@Override
	public List<FiMemberFee> getHistoryMemberFee(String userid) {
		String sql = "select t.id from FiMemberFee t where userid = ?";
		return memberFeeDao.findList(sql, new Object[] { userid });
	}

	/**
	 * 根据id获得店铺
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 */
	@Override
	public Member getMemberById(String id) {
		return memberDao.get(id);
	}

	/**
	 * 更新( 存在就修改，否则就新增)店铺信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean updateMember(User user, Member member) {
		User realUser = userDao.get(user.getId());
		if (realUser == null)
			throw new NotifyException("未注册用户无权申请供产会");

		if (StringUtils.isEmpty(realUser.getNickname()) || !realUser.getNickname().equals(user.getNickname())) {
			realUser.setNickname(user.getNickname());
			userDao.update(realUser);
		}

		// 店铺-判断是更新还是创建操作
		if (!StringUtils.isEmpty(member.getId())) {
			memberDao.update(member);
		} else {
			member.setId(null);
			member.setUserId(realUser.getId());
			memberDao.save(member);
		}
		return true;
	}

	/**
	 * 删除店铺信息
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean deleteMember(String shopId) {
		memberDao.delete(shopId);
		return true;
	}

	/**
	 * 分页获取待审核店铺
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组t.id
	 */
	@Override
	public List<Member> loadAppliedMembersForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Member t where t.isAudit = 0";
		String ctql = "select count(t) from Member t where t.isAudit = 0";
		return memberDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public List<Member> loadApprovedMembersForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from MemberProvider t where t.isAudit = 1";
		String ctql = "select count(t) from MemberProvider t where t.isAudit = 1";
		return memberDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 更改店铺有效标志参数（店铺开启与关闭时触发）
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Transactional
	@Override
	public boolean changeMemberValidStatus(String userId, int validStatus) {
		Member member = memberDao.getMember(userId);
		member.setIsValid(validStatus);
		memberDao.update(member);

		User user = userDao.get(userId);
		try {
			String status = validStatus == 1 ? "开启" : "关闭";
			// 发送邮件、短信通知
			String title = "【绿魔方】您的店铺推荐已被" + status;
			String content = user.getName() + "先生/小姐，您的会员身份已被绿魔方平台" + status + "，详细信息请与客服联系！";
			MailAPI.sendMail(user.getEmail(), title, content, false);

			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	// /**
	// * 审核通过一个店铺
	// *
	// * @param shopId
	// * -- 店铺id
	// * @param auditUserId
	// * -- 审核人id
	// * @param auditUserName
	// * -- 审核人姓名
	// * @return 成功与否标识
	// */
	// @Override
	// @Transactional
	// public boolean approveMember(String memberId, String auditUserId, String
	// auditUserName) {
	// Member shop = memberDao.get(memberId);
	// shop.setIsAudit(1);
	// memberDao.update(shop);
	//
	// auditDao.audit(shop.getId(), MemberProvider.class, auditUserId, 1);
	// User user = userDao.get(shop.getUserid());
	// int nowType = Integer.parseInt(user.getUserType()) |
	// User.USERTYPE_PROVIDER;
	// user.setUserType(String.valueOf(nowType));
	// userDao.update(user);
	//
	// try {
	// // 发送邮件、短信通知
	// String title = "【绿魔方】共产会店铺申请已获批准";
	// String content = "恭喜您" + user.getName() + "先生/小姐，您的店铺（" +
	// shop.getProviderName() + "）申请已或申请，您现在可以进入“绿魔方”管理您的商品了！";
	// MailAPI.sendMail(user.getEmail(), title, content, false);
	//
	// // 发送短信通知
	// ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
	// sms.sendMessage(user.getMobile(), content);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return true;
	// }
	//
	// /**
	// * 驳回申请
	// *
	// * @param shopId
	// * -- 店铺id
	// * @param auditUserId
	// * -- 审核人id
	// * @param auditUserName
	// * -- 审核人姓名
	// * @return 成功与否标识
	// */
	// @Transactional
	// @Override
	// public boolean rejectMember(String memberId, String auditUserId, String
	// auditUserName) {
	// MemberProvider shop = memberDao.get(memberId);
	// shop.setIsAudit(-1);
	// memberDao.update(shop);
	// auditDao.audit(shop.getId(), MemberProvider.class, auditUserId, -1);
	// User user = userDao.get(shop.getUserid());
	//
	// try {
	// // 发送邮件、短信通知
	// String title = "【绿魔方】共产会店铺申请已被驳回";
	// String content = user.getName() + "先生/小姐，您的店铺（" + shop.getProviderName()
	// + "）申请已被驳回，请您与客服联系！";
	// MailAPI.sendMail(user.getEmail(), title, content, false);
	//
	// // 发送短信通知
	// ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
	// sms.sendMessage(user.getMobile(), content);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return true;
	// }

	@Override
	public Member addMember(Member member) {
		memberDao.save(member);
		return member;
	}

}
