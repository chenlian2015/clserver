package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.UserCollectionBusiness;
import com.cnd.greencube.server.cache.CACHE_COLLECT;
import com.cnd.greencube.server.dao.CmsArticleDao;
import com.cnd.greencube.server.dao.ShopGoodsDao;
import com.cnd.greencube.server.dao.UserCollectionDao;
import com.cnd.greencube.server.dao.VideoDao;
import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.UserCollection;
import com.cnd.greencube.server.entity.UserCollection.CollectionType;
import com.cnd.greencube.server.entity.Video;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 用户管理服务类
 * 
 * @author apple
 * 
 */
@Service("UserCollectionBusinessImpl")
public class UserCollectionBusinessImpl extends BaseBusinessImpl<UserCollection, String> implements UserCollectionBusiness {
	@Resource(name = "UserCollectionDaoImpl")
	UserCollectionDao userCollectionDao;

	@Resource(name = "ShopGoodsDaoImpl")
	ShopGoodsDao shopGoodsDao;

	@Resource(name = "CmsArticleDaoImpl")
	CmsArticleDao cmsArticleDao;

	@Resource(name = "VideoDaoImpl")
	VideoDao videoDao;

	@Resource(name = "CACHE_COLLECT")
	CACHE_COLLECT cache;

	@Resource(name = "UserCollectionDaoImpl")
	public void setDao(UserCollectionDao UserCollectionDao) {
		super.setDao(UserCollectionDao);
	}

	public UserCollectionDao getDao() {
		return (UserCollectionDao) super.getDao();
	}

	/**
	 * 取得用户收藏的商品
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 商品json数组
	 */
	@Override
	public List<ShopGoods> getUserCollectGoods(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";
		return shopGoodsDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_GOODS }, pageInfo);
	}

	/**
	 * 取得用户收藏的知识
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 知识json数组
	 */
	@Override
	public List<CmsArticle> getUserCollectKnowledge(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";
		return cmsArticleDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_GOODS }, pageInfo);
	}

	/**
	 * 取得用户收藏的视频
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 视频json数组
	 */
	@Override
	public List<Video> getUserCollectVideo(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";
		return videoDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_GOODS }, pageInfo);
	}

	/**
	 * 取得用户收藏的医院
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 医院json数组
	 */
	@Override
	public List<CmsArticle> getUserCollectHospital(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";

		return cmsArticleDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_HOSPITAL }, pageInfo);
	}

	/**
	 * 取得用户收藏的专家
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 专家json数组
	 */
	@Override
	public List<CmsArticle> getUserCollectProfessor(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";

		return cmsArticleDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_PROFESSOR }, pageInfo);
	}

	/**
	 * 取得用户收藏的信息
	 * 
	 * @param userid
	 *            -- 用户id
	 * @return 文章json数组
	 */
	@Override
	public List<CmsArticle> getUserCollectArticle(String userId, PageInfo pageInfo) {
		String sql = "select t.foreignId from UserStore t where t.userId = ? ";
		String ctql = "select count(t) from UserStore t where t.userId = ? ";
		sql += " and t.type = ? ";
		ctql += " and t.type = ? ";
		sql += " order by time desc";

		return cmsArticleDao.findList(sql, ctql, new Object[] { userId, UserCollection.CollectionType.TYPE_INFO }, pageInfo);
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
	public boolean existInCollection(String userid, String id, String storeType) {
		String sql = "select count(t) from UserCollection t where t.userId = ? and t.foreignId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(id);
		if (null != storeType) {
			sql += " and type = ?";
			params.add(UserCollection.CollectionType.valueOf(storeType));
		}
		return userCollectionDao.queryCount(sql, params.toArray()) > 0;
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
	@Transactional
	@Override
	public void add(String userid, String foreignId, String category) {
		if (!existInCollection(userid, foreignId, category)) {
			UserCollection collection = new UserCollection();
			collection.setForeignId(foreignId);
			collection.setUserId(userid);

			CollectionType st = CollectionType.valueOf(category);
			collection.setType(st);
			collection.setTime(new Date());
			userCollectionDao.save(collection);
			cache.refresh(userid);
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
	@Transactional
	@Override
	public void remove(String userid, String foreignId) {
		String sql = "select t.id from UserCollection t where t.userId = ? and t.foreignId = ? ";
		List<UserCollection> stores = userCollectionDao.findList(sql, new Object[] { userid, foreignId });
		if (stores != null) {
			for (UserCollection s : stores) {
				userCollectionDao.delete(s.getId());
			}

			cache.refresh(userid);
		}
	}

	/**
	 * 取得一个用户的全部收藏id
	 * 
	 * @param userId
	 * @return 返回全部收藏的id数组
	 */
	@Override
	public String getMyCollectionIds(String userId) {
		return cache.read(userId);
	}

}
