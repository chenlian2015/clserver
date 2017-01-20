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
import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.business.ShopBusiness;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.FiManagerFeeDao;
import com.cnd.greencube.server.dao.FiMemberFeeDao;
import com.cnd.greencube.server.dao.MemberNewsDao;
import com.cnd.greencube.server.dao.ShopDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.FiManagerFee;
import com.cnd.greencube.server.entity.MemberNews;
import com.cnd.greencube.server.entity.Shop;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 店主会服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("ShopBusinessImpl")
public class ShopBusinessImpl extends BaseBusinessImpl implements ShopBusiness {
	@Resource(name = "ShopDaoImpl")
	protected ShopDao memberShopDao;

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
	 * 获取一个店铺的历史管理费缴纳记录
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	@Override
	public List<FiManagerFee> getShopHistoryManagerFee(String userid) {
		String sql = "select t.id from FiManagerFee t where userid = ?";
		return managerFeeDao.findList(sql, new Object[] { userid });
	}

	/**
	 * 根据id获得店铺
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 */
	@Override
	public Shop getShopById(String id) {
		return memberShopDao.get(id);
	}

	/**
	 * 更新店铺信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean submitApply(User user, Shop shop) {
		// 判断该用户是否已经提交过了申请
		User realUser = userDao.get(user.getId());
		if (realUser == null)
			throw new NotifyException("未注册用户无权申请开店");

		if (StringUtils.isEmpty(realUser.getNickname()) || !realUser.getNickname().equals(user.getNickname())) {
			realUser.setNickname(user.getNickname());
			userDao.update(realUser);
		}

		// 店铺-判断是更新还是创建操作
		if (!StringUtils.isEmpty(shop.getId())) {
			shop.setIsAudit(0);
			memberShopDao.update(shop);
		} else {
			shop.setId(null);
			shop.setUserid(realUser.getId());
			if (!StringUtils.isEmpty(shop.getBusinessCategoryId())) {
				String[] p = shop.getBusinessCategoryId().split(",");
				String categoryName = "";
				for (String s : p) {
					CodeHealthyCategory c = codeHealthyCategoryDao.get(s);
					categoryName = categoryName.length() == 0 ? c.getName() : categoryName + "," + c.getName();
				}
				shop.setBusinessCategoryName(categoryName);
			}

			if (!StringUtils.isEmpty(shop.getProvinceId())) {
				Area area = areaDao.get(shop.getProvinceId());
				shop.setProvinceName(area.getName());
			}

			if (!StringUtils.isEmpty(shop.getCityId())) {
				Area area = areaDao.get(shop.getCityId());
				shop.setCityName(area.getName());
			}

			shop.setLevel(0);
			shop.setGoodsAmountOnSale(0);
			shop.setSalesAmount(0);
			shop.setPraiseAmount(0);
			shop.setFansAmount(0);
			shop.setFocusAmount(0);
			shop.setCommentAmount(0);
			shop.setCommentScore(0);
			shop.setTotalConsume(BigDecimal.ZERO);
			shop.setIsAudit(0);
			shop.setIsValid(0);
			shop.setIsFrontShow(1);
			shop.setIsRecommend(0);
			memberShopDao.save(shop);
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
	public boolean deleteShop(String shopId) {
		memberShopDao.delete(shopId);
		return true;
	}

	/**
	 * 取得店铺的评论
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param pageNum
	 *            -- 页数
	 * @return 评论json数组
	 */
	@Override
	public List<Comment> loadShopCommentsForPagelit(String shopId, PageInfo pageInfo) {
		return commentDao.loadCommentsByForeignId(shopId, pageInfo);
	}

	/**
	 * 分页查询店铺通知公告
	 * 
	 * @return
	 */
	@Override
	public List<MemberNews> loadShopNoticeForPagelit(String shopId, PageInfo pageInfo) {
		return memberNewsDao.loadNoticeForPagelit(shopId, pageInfo);
	}

	/**
	 * 分页查询店铺新闻
	 */
	@Override
	public List<MemberNews> loadShopNewsForPagelit(String shopId, PageInfo pageInfo) {
		return memberNewsDao.loadNewsForPagelit(shopId, pageInfo);
	}

	/**
	 * 保存咨询信息
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean updateNews(MemberNews news) {
		if (StringUtils.isEmpty(news.getId())) {
			news.setId(null);
			news.setType(1);
			news.setCreateTime(new Date());
			news.setClickCount(0);
			news.setIsValid(1);
			news.setStatusAudit(0);
			memberNewsDao.save(news);
		} else {
			news.setStatusAudit(0);
			memberNewsDao.update(news);
		}
		return true;
	}

	/**
	 * 删除咨询信息
	 * 
	 * @param newsId
	 *            --新闻id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean deleteNews(String newsId) {
		memberNewsDao.delete(newsId);
		return true;
	}

	/**
	 * 保存通知信息
	 * 
	 * @param noticeId
	 *            -- 新闻id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean updateNotice(MemberNews news) {
		if (StringUtils.isEmpty(news.getId())) {
			news.setId(null);
			news.setType(2);
			news.setCreateTime(new Date());
			news.setClickCount(0);
			news.setIsValid(1);
			news.setStatusAudit(0);
			memberNewsDao.save(news);
		} else {
			news.setStatusAudit(0);
			memberNewsDao.update(news);
		}
		return true;
	}

	/**
	 * 删除通知信息
	 * 
	 * @param noticeId
	 *            --新闻id
	 * @return 成功与否标志
	 */
	@Transactional
	@Override
	public boolean deleteNotice(String noticeId) {
		memberNewsDao.delete(noticeId);
		return true;
	}

	/**
	 * 分页获取待审核店铺
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public List<Shop> loadAppliedShopsForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Shop t where t.isAudit <> 1";
		String ctql = "select t.id from Shop t where t.isAudit <> 1";

		return memberShopDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public List<Shop> loadApprovedShopsForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Shop t where t.isAudit = 1";
		String ctql = "select t.id from Shop t where t.isAudit = 1";
		return memberShopDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 店铺评级
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param level
	 *            -- 等级
	 * @return 成功标识
	 */
	@Transactional
	@Override
	public boolean rateShop(String shopId, int level) {
		Shop shop = memberShopDao.get(shopId);
		shop.setLevel(level > 5 ? 5 : level);
		memberShopDao.update(shop);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】店铺获得评级";
			String content = "恭喜您" + user.getName() + "先生/小姐，您的店铺（" + shop.getShopName() + "）获得了新的评级，您现在可以进入“绿魔方”查看您的店铺！";
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
	 * 审核通过一个店铺
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 */
	@Override
	@Transactional
	public boolean approveShop(String shopId, String auditUserId, String auditUserName) {
		Shop shop = memberShopDao.get(shopId);
		shop.setIsAudit(1);
		memberShopDao.update(shop);

		auditDao.audit(shop.getId(), Shop.class, auditUserId, 1);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());
		int nowType = Integer.parseInt(user.getUserType()) | User.USERTYPE_SHOP;
		user.setUserType(String.valueOf(nowType));
		userDao.update(user);

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】店铺申请已获批准";
			String content = "恭喜您" + user.getName() + "先生/小姐，您的店铺（" + shop.getShopName() + "）申请已或申请，您现在可以进入“绿魔方”管理您的商品了！";
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
	 * 驳回申请
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 */
	@Transactional
	@Override
	public boolean rejectShop(String shopId, String auditUserId, String auditUserName) {
		Shop shop = memberShopDao.get(shopId);
		shop.setIsAudit(0);
		memberShopDao.update(shop);

		auditDao.audit(shop.getId(), Shop.class, auditUserId, 1);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】店铺申请已被驳回";
			String content = user.getName() + "先生/小姐，您的店铺（" + shop.getShopName() + "）申请已被驳回，请您与客服联系！";
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
	 * 店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Transactional
	@Override
	public boolean recommendShop(String shopId) {
		Shop shop = memberShopDao.get(shopId);
		shop.setIsRecommend(1);
		memberShopDao.update(shop);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】恭喜，您的店铺已获得平台推荐";
			String content = user.getName() + "先生/小姐，恭喜，您的店铺（" + shop.getShopName() + "）已获得平台推荐，详细信息可与客服联系获取！";
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
	 * 取消店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Transactional
	@Override
	public boolean unRecommendShop(String shopId) {
		Shop shop = memberShopDao.get(shopId);
		shop.setIsRecommend(1);
		memberShopDao.update(shop);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】您的店铺推荐已被取消";
			String content = user.getName() + "先生/小姐，您的店铺（" + shop.getShopName() + "）已被平台取消了推荐，详细信息请与客服联系！";
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
	 * 更改店铺有效标志参数
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Transactional
	@Override
	public boolean changeShopValidStatus(String shopId, int validStatus) {
		Shop shop = memberShopDao.get(shopId);
		shop.setIsValid(validStatus);
		memberShopDao.update(shop);

		com.cnd.greencube.server.entity.User user = userDao.get(shop.getUserid());

		try {
			String status = validStatus == 1 ? "开启" : "关闭";
			// 发送邮件、短信通知
			String title = "【绿魔方】您的店铺推荐已被" + status;
			String content = user.getName() + "先生/小姐，您的店铺（" + shop.getShopName() + "）已被平台" + status + "，详细信息请与客服联系！";
			MailAPI.sendMail(user.getEmail(), title, content, false);

			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public MemberNews getShopNewsById(String newsId) {
		return memberNewsDao.get(newsId);
	}

	@Transactional
	@Override
	public boolean changeShopNewsValidStatus(String newsId, int validStatus) {
		MemberNews mn = memberNewsDao.get(newsId);
		mn.setIsValid(validStatus);
		memberNewsDao.update(mn);
		return true;
	}

	@Transactional
	@Override
	public boolean changeShopNewsTopStatus(String newsId, int topStatus) {
		MemberNews mn = memberNewsDao.get(newsId);
		mn.setIsTop(topStatus);

		memberNewsDao.update(mn);
		return true;
	}

	@Transactional
	@Override
	public boolean changeShopNewsAuditStatus(String newsId, int auditStatus) {
		MemberNews mn = memberNewsDao.get(newsId);
		mn.setStatusAudit(auditStatus);

		memberNewsDao.update(mn);
		return true;
	}

	@Override
	@Transactional
	public boolean submitShopComment(String shopId, String content, String userid) {
		Comment c = new Comment();
		c.setComment(content);
		c.setCommentTime(new Date());
		c.setForeignId(shopId);
		c.setForeignTable(Shop.class.getName());
		c.setUserId(userid);

		User user = userDao.get(userid);
		if (user != null) {
			c.setUserName(user.getName());
			c.setUserPhoto(user.getPhoto());
		}

		commentDao.save(c);
		return true;
	}

	@Override
	public Shop getMyShop(String userid) {
		String sql = "select t.id from Shop t where t.userid = ?";
		List<Shop> ps = memberShopDao.findList(sql, new Object[] { userid });
		if (ps == null || ps.size() == 0) {
			return null;
		}
		return ps.get(0);
	}

	@Override
	public boolean isMyShop(String userid, String shopId) {
		Shop shop = getShopById(shopId);
		if (shop == null)
			return false;
		if (shop.getUserid() != null && shop.getUserid().equals(userid))
			return true;
		return false;
	}
}
