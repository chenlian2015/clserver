package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 用户管理服务类
 * 
 * @author apple
 * 
 */
public interface UserCollectionBusiness {

	/**
	 * 取得用户收藏的商品
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 商品json数组
	 */
	List<ShopGoods> getUserCollectGoods(String userId,PageInfo pageInfo);

	/**
	 * 取得用户收藏的知识
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 知识json数组
	 */
	List<CmsArticle> getUserCollectKnowledge(String userId,PageInfo pageInfo);

	/**
	 * 取得用户收藏的视频
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 视频json数组
	 */
	List<Video> getUserCollectVideo(String userId,PageInfo pageInfo);

	/**
	 * 取得用户收藏的医院
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 医院json数组
	 */
	List<CmsArticle> getUserCollectHospital(String userId,PageInfo pageInfo);

	/**
	 * 取得用户收藏的专家
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 专家json数组
	 */
	List<CmsArticle> getUserCollectProfessor(String userId,PageInfo pageInfo);

	/**
	 * 取得用户收藏的信息
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 文章json数组
	 */
	List<CmsArticle> getUserCollectArticle(String userId,PageInfo pageInfo);

	/**
	 * 判断在收藏中是否存在
	 * 
	 * @param userId
	 *            -- 用户id
	 * @param collectionId
	 *            -- 收藏id
	 * @return
	 */
	boolean existInCollection(String userid, String id, String storeType);

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
	void add(String userid, String foreignId, String category);

	/**
	 * 删除收藏
	 * 
	 * @param userid
	 *            -- 用户id
	 * @param foreignId
	 *            -- 外键id
	 */
	void remove(String userid, String foreignId);

	/**
	 * 取得一个用户的全部收藏id
	 * 
	 * @param userId
	 * @return 返回全部收藏的id数组
	 */
	String getMyCollectionIds(String userId);

}
