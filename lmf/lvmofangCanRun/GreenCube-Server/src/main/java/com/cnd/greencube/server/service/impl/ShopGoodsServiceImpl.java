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

import com.cnd.greencube.server.business.ShopGoodsBusiness;
import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.ShopGoodsItem;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.ShopGoodsService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 店铺商品服务实现类
 * 
 * @author huxg
 * 
 */
public class ShopGoodsServiceImpl extends AbstractServiceImpl implements ShopGoodsService.Iface {
	private static final Logger logger = Logger.getLogger(ShopGoodsServiceImpl.class);

	@Resource(name = "ShopGoodsBusinessImpl")
	protected ShopGoodsBusiness shopGoodsBusiness;

	/**
	 * 1 店主取得店铺的商品
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
	public String loadShopGoodsByKeywords(String shopId, String keyword, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> goods = shopGoodsBusiness.loadShopGoodsByKeywords(shopId, keyword, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
	
	/**
	 * 返回我的商品
	 * @return
	 */
	@Override
	public String loadMyShopGoods(String token, int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			String userid = TokenUtils.isTokenRight(token);
			List<ShopGoods> goods = shopGoodsBusiness.loadMyShopGoods(userid, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 2 查看店铺的商品，分页显示
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param categoryId
	 *            -- 类别
	 * @return
	 */
	@Override
	public String loadShopGoodsByCategoryId(String shopId, String categoryId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> goods = shopGoodsBusiness.loadShopGoodsByCategoryId(shopId, categoryId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 3 获取一个店铺TopN商品
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param top
	 *            -- 数字
	 * @return 返回店铺商品json数组
	 */
	@Override
	public String loadShopGoodsTopN(String shopId, int number) throws TException {
		try {
			List<ShopGoods> goods = shopGoodsBusiness.loadShopGoodsTopN(shopId, number);
			return Message.okMessage(goods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 4 搜索商品
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
	public String searchGoodsForPagelit(String shopId, String categoryId, String provinceId, String cityId, String keyword, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> goods = shopGoodsBusiness.searchGoodsForPagelit(shopId, categoryId, provinceId, cityId, keyword, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 5 取得热门商品
	 * 
	 * @param count
	 *            -- 返回记录数
	 * @return 商品json数组
	 */
	@Override
	public String getHotGoods(int count) throws TException {
		try {
			List<ShopGoods> goods = shopGoodsBusiness.getHotGoods(count);
			return Message.okMessage(goods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 6 获取一个商品详细信息
	 * 
	 * @param shopGoodsId
	 * @return 商品json对象
	 */
	@Override
	public String getGoodsById(String id) throws TException {
		try {
			ShopGoods goods = shopGoodsBusiness.getGoodsById(id);
			return Message.okMessage(goods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 7 保存商品信息<br/>
	 * 如果id为空，则执行保存操作，如果id非空，则执行更新操作
	 * 
	 * @param goodsJson
	 *            -- 商品的json数据
	 * @param courseIdList
	 */
	@Override
	public String updateGoods(String goodsJson) throws TException {
		try {
			ShopGoods goods = JsonUtils.String2Bean(goodsJson, ShopGoods.class);
			shopGoodsBusiness.updateGoods(goods);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 8 删除商品
	 * 
	 * @param 商品id
	 * @return 成功与否标识
	 */
	@Override
	public String deleteGoodsById(String token, String goodsId) throws TException {
		try {
			String userid = TokenUtils.isTokenRight(token);
			
			shopGoodsBusiness.deleteGoodsById(userid, goodsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 9 商品上架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 */
	@Override
	public String putShelf(String goodsId) throws TException {
		try {
			shopGoodsBusiness.putShelf(goodsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 10 商品下架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 */
	@Override
	public String offShelf(String goodsId) throws TException {
		try {
			shopGoodsBusiness.offShelf(goodsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 11 获取商品条目列表（依据商品id）
	 */
	@Override
	public String getGoodsItems(String goodsId) throws TException {
		try {
			List<ShopGoodsItem> items = shopGoodsBusiness.getGoodsItems(goodsId);
			return Message.okMessage(items);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 12 获取商品评论列表
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @param pageNum
	 *            -- 页数
	 * @return
	 */
	@Override
	public String loadGoodsComment(String goodsId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Comment> comments = shopGoodsBusiness.loadGoodsComment(goodsId, pageInfo);
			return Message.okMessage(comments, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 13 店铺待审核的商品
	 */
	@Override
	public String loadAppliedShopGoodsForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> shopGoods = shopGoodsBusiness.loadAppliedShopGoodsForPagelit(pageInfo);
			return Message.okMessage(shopGoods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 14 店铺已通过审核的商品
	 */
	@Override
	public String loadApprovedShopGoodsForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> shopGoods = shopGoodsBusiness.loadApprovedShopGoodsForPagelit(pageInfo);
			return Message.okMessage(shopGoods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 15 审核通过店主会商品
	 */
	@Override
	public String approveShopGoods(String goodsId, String auditUserId, String auditUserName) throws TException {
		try {
			shopGoodsBusiness.approveShopGoods(goodsId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 16 审核驳回店主会商品
	 */
	@Override
	public String rejectShopGoods(String goodsId, String auditUserId, String auditUserName) throws TException {
		try {
			shopGoodsBusiness.rejectShopGoods(goodsId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 17 依据条目id获取商品条目
	 */
	@Override
	public String getGoodsItemById(String id) throws TException {
		try {
			ShopGoodsItem goodsItem = shopGoodsBusiness.getGoodsItemById(id);
			return Message.okMessage(goodsItem);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 18 依据成员获取商品
	 */
	@Override
	public String loadGoodsByMemberId(String memberId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<ShopGoods> goods = shopGoodsBusiness.loadGoodsByMemberId(memberId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 19 增加店主会商品实体
	 */
	@Override
	public String addShopGoodsEntity(String shopGoodsJson, String shopGoodsItemJson) throws TException {
		try {
			// 店主会商品主表
			ShopGoods shopGoods = JsonUtils.String2Bean(shopGoodsJson, ShopGoods.class);
			// 店主会商品明细表
			List<ShopGoodsItem> itemlist = JsonUtils.String2List(shopGoodsItemJson, ShopGoodsItem.class);
			;
			shopGoodsBusiness.addShopGoodsEntity(shopGoods, itemlist);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
	
	
}
