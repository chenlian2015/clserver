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

import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.business.ShopBusiness;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.FiManagerFee;
import com.cnd.greencube.server.entity.MemberNews;
import com.cnd.greencube.server.entity.Shop;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.ShopService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 店主会服务实现类
 * 
 * @author huxg
 * 
 */
public class ShopServiceImpl extends AbstractServiceImpl implements ShopService.Iface {
	private static final Logger logger = Logger.getLogger(ShopServiceImpl.class);

	@Resource(name = "ShopBusinessImpl")
	protected ShopBusiness shopBusiness;

	/**
	 * 获取历史缴费记录
	 * 
	 * @return
	 * @throws TException
	 */
	public String getShopManagerFee(String userid) throws TException {
		try {
			List<FiManagerFee> managerFees = shopBusiness.getShopHistoryManagerFee(userid);
			return Message.okMessage(new String[] { "manager_fee" }, new Object[] { managerFees });
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据id获得店铺
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 */
	@Override
	public String getShopById(String id) throws TException {
		try {
			Shop shop = shopBusiness.getShopById(id);
			return Message.okMessage(shop);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getMyShop(String userid) throws TException {
		try {
			Shop ps = shopBusiness.getMyShop(userid);
			return Message.okMessage(ps);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新店铺信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 */
	@Override
	public String submitApply(String userJson, String shopJson) throws TException {
		try {
			User user = JsonUtils.String2Bean(userJson, User.class);
			Shop shop = JsonUtils.String2Bean(shopJson, Shop.class);

			if (user == null)
				return Message.errorMessage("Error[001]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim(user.getId()))
				return Message.errorMessage("Error[002]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim("nickname"))
				return Message.errorMessage("Error[003]提交失败，参数有误");
			if (shop == null)
				return Message.errorMessage("Error[004]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim(shop.getShopName()))
				return Message.errorMessage("Error[006]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim(shop.getShopPhone()))
				return Message.errorMessage("Error[007]提交失败，参数有误");

			shopBusiness.submitApply(user, shop);
			return Message.okMessage();
		} catch (NotifyException ne) {
			return Message.errorMessage(ne.getMessage());
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新店铺信息
	 * 
	 * @param shopJsonObject
	 * @return 成功与否标志
	 */
	@Override
	public String updateShop(String userJson, String shopJson) throws TException {
		return submitApply(userJson, shopJson);
	}

	/**
	 * 删除店铺信息
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标志
	 */
	@Override
	public String deleteShop(String shopId) throws TException {
		try {
			shopBusiness.deleteShop(shopId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
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
	public String loadShopCommentsForPagelit(String shopId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Comment> comments = shopBusiness.loadShopCommentsForPagelit(shopId, pageInfo);
			return Message.okMessage(comments, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页查询店铺通知公告
	 * 
	 * @return
	 */
	@Override
	public String loadShopNoticeForPagelit(String shopId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<MemberNews> news = shopBusiness.loadShopNoticeForPagelit(shopId, pageInfo);
			return Message.okMessage(news, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页查询店铺新闻
	 */
	@Override
	public String loadShopNewsForPagelit(String shopId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<MemberNews> news = shopBusiness.loadShopNewsForPagelit(shopId, pageInfo);
			return Message.okMessage(news, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存咨询信息
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @return 成功与否标志
	 */
	@Override
	public String updateNews(String newsJson) throws TException {
		try {
			MemberNews news = JsonUtils.String2Bean(newsJson, MemberNews.class);
			shopBusiness.updateNews(news);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除咨询信息
	 * 
	 * @param newsId
	 *            --新闻id
	 * @return 成功与否标志
	 */
	@Override
	public String deleteNews(String newsId) throws TException {
		try {
			shopBusiness.deleteNews(newsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存通知信息
	 * 
	 * @param noticeId
	 *            -- 新闻id
	 * @return 成功与否标志
	 */
	@Override
	public String updateNotice(String noticeJson) throws TException {
		try {
			MemberNews news = JsonUtils.String2Bean(noticeJson, MemberNews.class);
			shopBusiness.updateNotice(news);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除通知信息
	 * 
	 * @param noticeId
	 *            --新闻id
	 * @return 成功与否标志
	 */
	@Override
	public String deleteNotice(String noticeId) throws TException {
		try {
			shopBusiness.deleteNotice(noticeId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 分页获取待审核店铺
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 */
	@Override
	public String getAppliedShopsForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Shop> shops = shopBusiness.loadAppliedShopsForPagelit(pageInfo);
			return Message.okMessage(shops, pageInfo);
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
	public String getApprovedShopsForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Shop> shops = shopBusiness.loadApprovedShopsForPagelit(pageInfo);
			return Message.okMessage(shops, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}

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
	@Override
	public String rateShop(String shopId, int level) throws TException {
		try {
			shopBusiness.rateShop(shopId, level);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
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
	public String approveShop(String shopId, String auditUserId, String auditUserName) throws TException {
		try {
			shopBusiness.approveShop(shopId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
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
	@Override
	public String rejectShop(String shopId, String auditUserId, String auditUserName) throws TException {
		try {
			shopBusiness.rejectShop(shopId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Override
	public String recommendShop(String shopId) throws TException {
		try {
			shopBusiness.recommendShop(shopId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取消店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Override
	public String unRecommendShop(String shopId) throws TException {
		try {
			shopBusiness.unRecommendShop(shopId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更改店铺有效标志参数
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 */
	@Override
	public String changeShopValidStatus(String shopId, int validStatus) throws TException {
		try {
			shopBusiness.changeShopValidStatus(shopId, validStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getShopNewsById(String newsId) throws TException {
		try {
			MemberNews news = shopBusiness.getShopNewsById(newsId);
			return Message.okMessage(news);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String changeShopNewsValidStatus(String newsId, int validStatus) throws TException {
		try {
			shopBusiness.changeShopNewsValidStatus(newsId, validStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String changeShopNewsTopStatus(String newsId, int topStatus) throws TException {
		try {
			shopBusiness.changeShopNewsTopStatus(newsId, topStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String changeShopNewsAuditStatus(String newsId, int auditStatus) throws TException {
		try {
			shopBusiness.changeShopNewsAuditStatus(newsId, auditStatus);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String submitShopComment(String shopId, String content, String userid) throws TException {
		try {
			shopBusiness.submitShopComment(shopId, content, userid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String isMyShop(String token, String shopId) {
		try {
			String userid = TokenUtils.isTokenRight(token);
			boolean result = shopBusiness.isMyShop(userid, shopId);
			return Message.okMessage(new String[] { "isMyShop" }, new Object[] { result });
		} catch (Exception e) {
			return Message.error();
		}
	}
}
