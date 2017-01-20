package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.ShopGoods;

/**
 * 店铺-商品数据访问
 * @author huxg
 *
 */
public interface ShopGoodsDao extends BaseDao<ShopGoods, String> {
	List<ShopGoods> getHotGoods(int count);
}
