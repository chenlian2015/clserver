package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.ShopGoodsItemDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.ShopGoodsItem;

/**
 * 商品项目
 * @author huxg
 *
 */
@Repository("ShopGoodsItemDaoImpl")
public class ShopGoodsItemDaoImpl extends RedisDaoSupportImpl<ShopGoodsItem, String> implements ShopGoodsItemDao {
	
	/**
	 * 返回商品项目
	 */
	@Override
	public List<ShopGoodsItem> getGoodsItems(String goodsId) {
		String sql = "select t.id from ShopGoodsItem t where t.shopGoodsId = ? and (t.isDelete <> 1 or t.isDelete is null) ";
		return super.findList(sql, new Object[] { goodsId });
	}

}
