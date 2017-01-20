/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.api.MailAPI;
import com.cnd.greencube.server.business.ShopGoodsBusiness;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.ProductDao;
import com.cnd.greencube.server.dao.ShopDao;
import com.cnd.greencube.server.dao.ShopGoodsDao;
import com.cnd.greencube.server.dao.ShopGoodsItemDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.Shop;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.ShopGoodsItem;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 店铺商品服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("ShopGoodsBusinessImpl")
public class ShopGoodsBusinessImpl extends BaseBusinessImpl implements ShopGoodsBusiness {
	@Resource(name = "ShopGoodsDaoImpl")
	ShopGoodsDao shopGoodsDao;

	@Resource(name = "ShopDaoImpl")
	ShopDao shopDao;

	@Resource(name = "ProductDaoImpl")
	ProductDao productDao;

	@Resource(name = "AreaDaoImpl")
	AreaDao areaDao;

	@Resource(name = "AuditDaoImpl")
	AuditDao auditDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "CodeHealthyCategoryDaoImpl")
	CodeHealthyCategoryDao codeHealthyCategoryDao;

	@Resource(name = "ShopGoodsItemDaoImpl")
	ShopGoodsItemDao shopGoodsItemDao;

	@Resource(name = "CommentDaoImpl")
	CommentDao commentDao;

	/**
	 * 店主取得店铺的商品
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param keyword
	 *            -- 过滤关键字
	 * @param pageNum
	 *            -- 分页页数
	 * @return 商品json数组
	 */
	@Override
	public List<ShopGoods> loadShopGoodsByKeywords(String shopId, String keyword, PageInfo pageInfo) {
		String sql = "select g.id from ShopGoods g where shopId = ? and g.statusDelete = 0 ";
		String ctql = "select count(g) from ShopGoods g where shopId = ?  and g.statusDelete = 0 ";
		List<Object> params = new ArrayList<Object>();
		params.add(shopId);
		if (!StringUtils.isEmpty(keyword)) {
			sql += " and name like ?";
			ctql += " and name like ?";
			params.add("%" + keyword + "%");
		}
		return shopGoodsDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 查看店铺的商品，分页显示
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param filter
	 *            -- 过滤关键字
	 * @param status
	 *            -- 店铺状态
	 * @return
	 */
	@Override
	public List<ShopGoods> loadShopGoodsByCategoryId(String shopId, String categoryId, PageInfo pageInfo) {
		String sql = "select g.id from ShopGoods g where shopId = ? and statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		String ctql = "select count(g) from ShopGoods g where shopId = ? and statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		List<Object> params = new ArrayList<Object>();
		params.add(shopId);

		if (!StringUtils.isEmpty(categoryId)) {
			sql += " and categoryId = ?";
			ctql += " and categoryId = ?";
			params.add(categoryId);
		}

		return shopGoodsDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 获取一个店铺TopN商品
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param top
	 *            -- 数字
	 * @return 返回店铺商品json数组
	 */
	@Override
	public List<ShopGoods> loadShopGoodsTopN(String shopId, int number) {
		String sql = "select g.id from ShopGoods g where shopId = ? and statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		List<Object> params = new ArrayList<Object>();
		params.add(shopId);

		return shopGoodsDao.findList(sql, params.toArray(), 1, number);
	}

	/**
	 * 搜索商品
	 * 
	 * @param categoryId
	 *            -- 分类id
	 * @param provinceId
	 *            -- 省id
	 * @param cityId
	 *            -- 城市id
	 * @param keyword
	 *            -- 商品关键字
	 * @return 商品json 数组
	 */
	@Override
	public List<ShopGoods> searchGoodsForPagelit(String shopId, String categoryId, String provinceId, String cityId, String keyword, PageInfo pageInfo) {
		String sql = "select g.id from ShopGoods g where statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		String ctql = "select count(g) from ShopGoods g where statusShelf = 1 and statusAudit = 1 and statusDelete = 0";

		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(shopId)) {
			sql += " and shopId = ?";
			ctql += " and shopId = ?";
			params.add(shopId);
		}

		if (!StringUtils.isEmpty(categoryId)) {
			sql += " and categoryId like ?";
			ctql += " and categoryId like ?";
			params.add("%" + categoryId + "%");
		}

		if (!StringUtils.isEmpty(provinceId)) {
			sql += " and provinceId = ?";
			ctql += " and provinceId = ?";
			params.add(provinceId);
		}

		if (!StringUtils.isEmpty(cityId)) {
			sql += " and cityId = ?";
			ctql += " and cityId = ?";
			params.add(cityId);
		}

		if (!StringUtils.isEmpty(keyword)) {
			sql += " and name like ?";
			ctql += " and name like ?";
			params.add("%" + keyword + "%");
		}

		return shopGoodsDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 取得热门商品
	 * 
	 * @param count
	 *            -- 返回记录数
	 * @return 商品json数组
	 */
	@Override
	public List<ShopGoods> getHotGoods(int count) {
		return shopGoodsDao.getHotGoods(count);
	}

	/**
	 * 获取一个商品详细信息
	 * 
	 * @param shopGoodsId
	 * @return 商品json对象
	 */
	@Override
	public ShopGoods getGoodsById(String id) {
		return shopGoodsDao.get(id);
	}

	/**
	 * 保存商品信息<br/>
	 * 如果id为空，则执行保存操作，如果id非空，则执行更新操作
	 * 
	 * @param goodsJson
	 *            -- 商品的json数据
	 * @param courseIdList
	 */
	@Override
	@Transactional
	public boolean updateGoods(ShopGoods goods) {
		if (StringUtils.isEmpty(goods.getId())) {
			// 商品分类id
			if (!StringUtils.isEmpty(goods.getCategoryId())) {
				// 设置商品分类
				String[] p = goods.getCategoryId().split(",");
				String categoryName = "";
				for (String s : p) {
					CodeHealthyCategory c = codeHealthyCategoryDao.get(s);
					if (c != null) {
						categoryName = categoryName.length() == 0 ? c.getName() : categoryName + "," + c.getName();
					}
				}

				// 所在省
				if (!StringUtils.isEmpty(goods.getProvinceId()) && StringUtils.isEmpty(goods.getProvinceName())) {
					Area area = areaDao.get(goods.getProvinceId());
					goods.setProvinceName(area.getName());
				}

				// 城市名称
				if (!StringUtils.isEmpty(goods.getCityId()) && StringUtils.isEmpty(goods.getCityName())) {
					Area area = areaDao.get(goods.getCityId());
					goods.setCityName(area.getName());
				}

				goods.setCreateTime(new Date());
				goods.setSalesCount(0);
				goods.setStatusShelf(0);
				goods.setStatusAudit(0);
				goods.setStatusDelete(0);
				goods.setIsRecommend(0);
			}
			shopGoodsDao.save(goods);
		} else {
			goods.setStatusAudit(0);
			shopGoodsDao.update(goods);
		}
		return true;
	}

	/**
	 * 删除商品
	 * 
	 * @param 商品id
	 * @return 成功与否标识
	 */
	@Override
	@Transactional
	public boolean deleteGoodsById(String userid, String goodsId) {
		User user = userDao.get(userid);
		ShopGoods goods = shopGoodsDao.get(goodsId);

		if (goods == null)
			return true;

		Shop shop = shopDao.get(goods.getShopId());
		if (shop == null)
			return false;

		if (!"admin".equals(user.getUserType()) && user.getId().equals(shop.getUserid())) {
			return false;
		}

		// 可以直接删除掉
		goods.setStatusDelete(1);
		shopGoodsDao.update(goods);

		// 连带商品项目一同删除
		List<ShopGoodsItem> items = shopGoodsItemDao.getGoodsItems(goodsId);
		for (ShopGoodsItem g : items) {
			g.setIsDelete(1);
			shopGoodsItemDao.update(g);
		}

		return true;
	}

	/**
	 * 商品上架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 */
	@Override
	@Transactional
	public boolean putShelf(String goodsId) {
		ShopGoods goods = shopGoodsDao.get(goodsId);
		goods.setStatusShelf(1);
		shopGoodsDao.update(goods);
		return true;
	}

	/**
	 * 商品下架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 */
	@Override
	@Transactional
	public boolean offShelf(String goodsId) {
		ShopGoods goods = shopGoodsDao.get(goodsId);
		goods.setStatusShelf(0);
		shopGoodsDao.update(goods);
		return true;
	}

	/**
	 * 获取商品项目列表
	 */
	@Override
	public List<ShopGoodsItem> getGoodsItems(String goodsId) {
		return shopGoodsItemDao.getGoodsItems(goodsId);
	}

	/**
	 * 获取商品评论列表
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @param pageNum
	 *            -- 页数
	 * @return
	 */
	@Override
	public List<Comment> loadGoodsComment(String goodsId, PageInfo pageInfo) {
		return commentDao.loadCommentsByForeignId(goodsId, pageInfo);
	}

	@Override
	public List<ShopGoods> loadAppliedShopGoodsForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from ShopGoods t where t.statusAudit = 0";
		String ctql = "select count(t) from ShopGoods t where t.statusAudit = 0";
		return shopGoodsDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public List<ShopGoods> loadApprovedShopGoodsForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from ShopGoods t where t.statusAudit = 1";
		String ctql = "select count(t) from ShopGoods t where t.statusAudit = 1";
		return shopGoodsDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean approveShopGoods(String goodsId, String auditUserId, String auditUserName) {
		ShopGoods goods = shopGoodsDao.get(goodsId);
		goods.setStatusAudit(1);
		shopGoodsDao.update(goods);

		auditDao.audit(goods.getId(), ShopGoods.class, auditUserId, 1);
		User user = userDao.get(auditUserId);

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】店主会店铺商品申请已获批准";
			String content = "恭喜您 " + user.getName() + "先生/小姐，您的店主会店铺商品（" + goods.getName() + "）申请已被通过，您现在可以进入“绿魔方”管理您的商品了！";
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
	public boolean rejectShopGoods(String goodsId, String auditUserId, String auditUserName) {
		ShopGoods goods = shopGoodsDao.get(goodsId);
		goods.setStatusAudit(-1);
		shopGoodsDao.update(goods);

		auditDao.audit(goods.getId(), ShopGoods.class, auditUserId, 1);
		User user = userDao.get(auditUserId);

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】店主会店铺商品申请已被驳回";
			String content = "很遗憾 " + user.getName() + "先生/小姐，您的店铺商品（" + goods.getName() + "）申请已被驳回，请重新申请或联系客服！";
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
	 * 获取一个商品条目详细信息
	 * 
	 * @param shopGoodsItemId
	 * @return 商品条目json对象
	 */
	@Override
	public ShopGoodsItem getGoodsItemById(String id) {
		return shopGoodsItemDao.get(id);
	}

	@Override
	public List<ShopGoods> loadGoodsByMemberId(String memberId, PageInfo pageInfo) {
		String sql = "select g.id from ShopGoods g where shopId = ?";
		String ctql = "select count(g) from ShopGoods g where shopId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		return shopGoodsDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 添加店主会商品（主表和明细表）
	 * 
	 * @param sg
	 * @param sgi
	 * @return
	 */
	@Override
	public boolean addShopGoodsEntity(ShopGoods sg, List<ShopGoodsItem> sgiList) {
		shopGoodsDao.save(sg);
		for (int i = 0; i < sgiList.size(); i++) {
			ShopGoodsItem sgi = sgiList.get(i);
			shopGoodsItemDao.save(sgi);
		}
		return true;
	}

	@Override
	public List<ShopGoods> loadMyShopGoods(String userid, PageInfo pageInfo) {
		String sql = "select g.id from ShopGoods g , Shop s where g.statusDelete = 0 and g.shopId = s.id and s.userid = ? ";
		String ctql = "select count(g) from ShopGoods g , Shop s where g.statusDelete = 0 and  g.shopId = s.id and s.userid = ? ";
		return shopGoodsDao.findList(sql, ctql, new Object[] { userid }, pageInfo);
	}

}
