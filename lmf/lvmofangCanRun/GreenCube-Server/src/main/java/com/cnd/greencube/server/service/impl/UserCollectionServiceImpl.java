package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.UserCollectionBusiness;
import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.UserCollectionService;
import com.cnd.greencube.server.util.PageInfo;
/**
 * 用户管理服务类
 * 
 * @author apple
 * 
 */
public class UserCollectionServiceImpl extends AbstractServiceImpl implements UserCollectionService.Iface {
	private static final Logger logger = Logger.getLogger(UserCollectionServiceImpl.class);

	@Resource(name = "UserCollectionBusinessImpl")
	protected UserCollectionBusiness userCollectionBusiness;

	/**
	 * 取得用户收藏的商品
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 商品json数组
	 */
	@Override
	public String getUserCollectGoods(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> goods = userCollectionBusiness.getUserCollectGoods(userId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得用户收藏的知识
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 知识json数组
	 */
	@Override
	public String getUserCollectKnowledge(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = userCollectionBusiness.getUserCollectKnowledge(userId, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得用户收藏的视频
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 视频json数组
	 */
	@Override
	public String getUserCollectVideo(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Video> videos = userCollectionBusiness.getUserCollectVideo(userId, pageInfo);
			return Message.okMessage(videos, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得用户收藏的医院
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 医院json数组
	 */
	@Override
	public String getUserCollectHospital(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = userCollectionBusiness.getUserCollectHospital(userId, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得用户收藏的专家
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 专家json数组
	 */
	@Override
	public String getUserCollectProfessor(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = userCollectionBusiness.getUserCollectProfessor(userId, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得用户收藏的信息
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 文章json数组
	 */
	@Override
	public String getUserCollectArticle(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<CmsArticle> articles = userCollectionBusiness.getUserCollectArticle(userId, pageInfo);
			return Message.okMessage(articles, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 判断在收藏中是否存在
	 * 
	 * @param userId
	 *            -- 用户id
	 * @param collectionId
	 *            -- 收藏id
	 * @return
	 */
	public String existInCollection(String userid, String id, String storeType) throws TException {
		try {

			boolean isExist = userCollectionBusiness.existInCollection(userid, id, storeType);
			return isExist ? Message.okMessage() : Message.error();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 添加到收藏夹中
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param foreignId
	 *            -- 外键id
	 * @param storeType
	 *            -- 类别
	 */
	public String add(String userid, String foreignId, String category) {
		try {
			userCollectionBusiness.add(userid, foreignId, category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除收藏
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param foreignId
	 *            -- 外键id
	 */
	public String remove(String userid, String foreignId) {
		try {
			userCollectionBusiness.remove(userid, foreignId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 取得一个用户的全部收藏id
	 * 
	 * @param userId
	 * @return 返回全部收藏的id数组
	 */
	public String getMyCollectionIds(String userId) {
		try {
			return userCollectionBusiness.getMyCollectionIds(userId);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
