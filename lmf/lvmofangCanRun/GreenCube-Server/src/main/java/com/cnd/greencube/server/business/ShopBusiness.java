/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.FiManagerFee;
import com.cnd.greencube.server.entity.MemberNews;
import com.cnd.greencube.server.entity.Shop;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 店主会服务实现类
 * 
 * @author huxg
 * 
 */
public interface ShopBusiness {

	/**
	 * 获取一个店铺的历史管理费缴纳记录
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	List<FiManagerFee> getShopHistoryManagerFee(String userid);

	/**
	 * 根据id获得店铺
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	Shop getShopById(String id);

	/**
	 * 取得一个人的店铺申请信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 * 
	 * @param userId
	 */
	// String getApplyInfo(String userId);

	/**
	 * 更新店铺信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 * 
	 * @param userJson
	 * @param shopJson
	 * @param shopDetailJson
	 */
	boolean submitApply(User user, Shop shop);

	/**
	 * 删除店铺信息
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标志
	 * 
	 * @param shopId
	 */
	boolean deleteShop(String shopId);

	/**
	 * 取得店铺的评论
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param pageNum
	 *            -- 页数
	 * @return 评论json数组
	 * 
	 * @param shopId
	 * @param pageNum
	 */
	List<Comment> loadShopCommentsForPagelit(String shopId, PageInfo pageInfo);

	/**
	 * 分页查询店铺通知公告
	 * 
	 * @return
	 * 
	 * @param shopId
	 * @param pageNum
	 */
	List<MemberNews> loadShopNoticeForPagelit(String shopId, PageInfo pageInfo);

	/**
	 * 分页查询店铺新闻
	 * 
	 * @param shopId
	 * @param pageNum
	 */
	List<MemberNews> loadShopNewsForPagelit(String shopId, PageInfo pageInfo);

	/**
	 * 取得店铺资讯
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @return 成功与否标志
	 * 
	 * @param newsId
	 */
	MemberNews getShopNewsById(String newsId);

	/**
	 * 保存咨询信息
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @return 成功与否标志
	 * 
	 * @param newsJson
	 */
	boolean updateNews(MemberNews news);

	/**
	 * 删除咨询信息
	 * 
	 * @param newsId
	 *            --新闻id
	 * @return 成功与否标志
	 * 
	 * @param newsId
	 */
	boolean deleteNews(String newsId);

	/**
	 * 保存通知信息
	 * 
	 * @param noticeId
	 *            -- 新闻id
	 * @return 成功与否标志
	 * 
	 * @param noticeJson
	 */
	boolean updateNotice(MemberNews news);

	/**
	 * 删除通知信息
	 * 
	 * @param noticeId
	 *            --新闻id
	 * @return 成功与否标志
	 * 
	 * @param noticeId
	 */
	boolean deleteNotice(String noticeId);

	/**
	 * 分页获取待审核店铺
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 * 
	 * @param pageNum
	 */
	List<Shop> loadAppliedShopsForPagelit(PageInfo pageInfo);

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 * 
	 * @param pageNum
	 */
	List<Shop> loadApprovedShopsForPagelit(PageInfo pageInfo);

	/**
	 * 店铺评级
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param level
	 *            -- 等级
	 * @return 成功标识
	 * 
	 * @param shopId
	 * @param level
	 */
	boolean rateShop(String shopId, int level);

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
	 * 
	 * @param shopId
	 * @param auditUserId
	 * @param auditUserName
	 */
	boolean approveShop(String shopId, String auditUserId, String auditUserName);

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
	 * 
	 * @param shopId
	 * @param auditUserId
	 * @param auditUserName
	 */
	boolean rejectShop(String shopId, String auditUserId, String auditUserName);

	/**
	 * 店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 * 
	 * @param shopId
	 */
	boolean recommendShop(String shopId);

	/**
	 * 取消店铺推荐
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 * 
	 * @param shopId
	 */
	boolean unRecommendShop(String shopId);

	/**
	 * 更改店铺有效标志参数
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 * 
	 * @param shopId
	 * @param validStatus
	 */
	boolean changeShopValidStatus(String shopId, int validStatus);

	/**
	 * 更改店铺新闻状态
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @param validStatus
	 *            -- 有效状态
	 * @return 成功与否标志
	 * 
	 * @param newsId
	 * @param validStatus
	 */
	boolean changeShopNewsValidStatus(String newsId, int validStatus);

	/**
	 * 更改店铺新闻置顶状态
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @param validStatus
	 *            -- 有效状态
	 * @return 成功与否标志
	 * 
	 * @param newsId
	 * @param topStatus
	 */
	boolean changeShopNewsTopStatus(String newsId, int topStatus);

	/**
	 * 更改店铺新闻审核状态
	 * 
	 * @param newsId
	 *            -- 新闻id
	 * @param validStatus
	 *            -- 有效状态
	 * @return 成功与否标志
	 * 
	 * @param newsId
	 * @param auditStatus
	 */
	boolean changeShopNewsAuditStatus(String newsId, int auditStatus);

	/**
	 * 对一个店铺进行评价
	 * 
	 * @param shopid
	 *            -- 店铺id
	 * @param commentJson
	 *            评论json内容
	 * @return 成功与否标志
	 * 
	 * @param shopId
	 * @param content
	 * @param userid
	 */
	boolean submitShopComment(String shopId, String content, String userid);

	/**
	 * 取得我的店铺
	 * 
	 * @param userid
	 * @return
	 */
	Shop getMyShop(String userid);
	
	/**
	 * 判定是否是我的店铺
	 * @param token
	 * @param shopId
	 * @return
	 */
	boolean isMyShop(String userid, String shopId);
}
