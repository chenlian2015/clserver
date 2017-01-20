/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Comment;
import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.ShopGoodsItem;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 店铺商品服务实现类
 * 
 * @author huxg
 * 
 */
public interface ShopGoodsBusiness {

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
	 * 
	 * @param shopId
	 * @param keyword
	 * @param pageNum
	 */
	List<ShopGoods> loadShopGoodsByKeywords(String shopId, String keyword, PageInfo pageInfo);

	/**
	 * 返回我的商品
	 * 
	 * @param token
	 * @param pageNum
	 * @return
	 */
	List<ShopGoods> loadMyShopGoods(String userid, PageInfo pageInfo);

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
	 * 
	 * @param shopId
	 * @param categoryId
	 * @param pageNum
	 */
	List<ShopGoods> loadShopGoodsByCategoryId(String shopId, String categoryId, PageInfo pageInfo);

	/**
	 * 获取一个店铺TopN商品
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @param top
	 *            -- 数字
	 * @return 返回店铺商品json数组
	 * 
	 * @param shopId
	 * @param number
	 */
	List<ShopGoods> loadShopGoodsTopN(String shopId, int number);

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
	 * 
	 * @param shopId
	 * @param categoryId
	 * @param provinceId
	 * @param cityId
	 * @param keyword
	 * @param pageNum
	 */
	List<ShopGoods> searchGoodsForPagelit(String shopId, String categoryId, String provinceId, String cityId, String keyword, PageInfo pageInfo);

	/**
	 * 取得热门商品
	 * 
	 * @param count
	 *            -- 返回记录数
	 * @return 商品json数组
	 * 
	 * @param count
	 */
	List<ShopGoods> getHotGoods(int count);

	/**
	 * 获取一个商品详细信息
	 * 
	 * @param shopGoodsId
	 * @return 商品json对象
	 * 
	 * @param id
	 */
	ShopGoods getGoodsById(String id);

	/**
	 * 保存商品信息<br/>
	 * 如果id为空，则执行保存操作，如果id非空，则执行更新操作
	 * 
	 * @param goodsJson
	 *            -- 商品的json数据
	 * @param courseIdList
	 * 
	 * @param goodsJson
	 */
	boolean updateGoods(ShopGoods goods);

	/**
	 * 删除商品
	 * 
	 * @param 商品id
	 * @return 成功与否标识
	 * 
	 * @param goodsId
	 */
	boolean deleteGoodsById(String userid, String goodsId);

	/**
	 * 商品上架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 * 
	 * @param goodsId
	 */
	boolean putShelf(String goodsId);

	/**
	 * 商品下架操作
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 成功与否标识
	 * 
	 * @param goodsId
	 */
	boolean offShelf(String goodsId);

	/**
	 * 获取商品详情列表
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @return 关联的供应商商品列表
	 * 
	 * @param goodsId
	 */
	List<ShopGoodsItem> getGoodsItems(String goodsId);

	/**
	 * 获取商品评论列表
	 * 
	 * @param goodsId
	 *            -- 商品id
	 * @param pageNum
	 *            -- 页数
	 * @return 商品评论json数组
	 * 
	 * @param goodsId
	 * @param pageNum
	 */
	List<Comment> loadGoodsComment(String goodsId, PageInfo pageInfo);

	/**
	 * 分页获取待审核店主会商品
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店主会商品json数组
	 * 
	 * @param pageNum
	 */
	public List<ShopGoods> loadAppliedShopGoodsForPagelit(PageInfo pageInfo);

	/**
	 * 分页获取已审核通过的店主会商品列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店主会商品json数组
	 * 
	 * @param pageNum
	 */
	public List<ShopGoods> loadApprovedShopGoodsForPagelit(PageInfo pageInfo);

	/**
	 * 审核通过一个店铺商品
	 * 
	 * @param goodsId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 * 
	 * @param goodsId
	 * @param auditUserId
	 * @param auditUserName
	 */
	public boolean approveShopGoods(String goodsId, String auditUserId, String auditUserName);

	/**
	 * 驳回通过一个店铺商品
	 * 
	 * @param goodsId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 * 
	 * @param goodsId
	 * @param auditUserId
	 * @param auditUserName
	 */
	public boolean rejectShopGoods(String goodsId, String auditUserId, String auditUserName);

	/**
	 * 获取一个商品详细信息
	 * 
	 * @param shopGoodsItemId
	 * @return 商品条目json对象
	 * 
	 * @param id
	 */
	ShopGoodsItem getGoodsItemById(String id);

	/**
	 * 依据成员得到商品列表
	 * 
	 * @param memberId
	 * @param pageNum
	 *            页数
	 * @return 商品list
	 * 
	 * @param memberId
	 */
	public List<ShopGoods> loadGoodsByMemberId(String memberId, PageInfo pageInfo);

	/**
	 * 添加店主会商品（主表和明细表）
	 * 
	 * @param sg
	 * @param sgi
	 * @return
	 */
	public boolean addShopGoodsEntity(ShopGoods sg, List<ShopGoodsItem> sgiList);
	
	
}
