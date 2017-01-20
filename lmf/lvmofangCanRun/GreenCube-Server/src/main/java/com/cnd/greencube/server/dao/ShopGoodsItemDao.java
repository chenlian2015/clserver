package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.ShopGoodsItem;

/**
 * 商品项目数据访问
 * @author huxg
 *
 */
public interface ShopGoodsItemDao extends BaseDao<ShopGoodsItem, String> {
	/**
	 * 返回商品项目
	 */
	public List<ShopGoodsItem> getGoodsItems(String goodsId);
}
