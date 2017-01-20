package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.ShopDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Shop;

@Repository("ShopDaoImpl")
public class ShopDaoImpl extends RedisDaoSupportImpl<Shop, String> implements ShopDao {
	public List<Shop> getShopsByUserId(String userId) {
		String sql = "select t.id from Shop t where t.userid = ?";
		List<Shop> shops = super.findList(sql, new Object[] { userId });
		return shops;
	}

	// public Shop getShopByUserId(String userId) {
	// String sql = "select t.id from Shop t where t.userid = ?";
	// List<Shop> shops = super.findList(sql, new Object[] { userId });
	// if (shops != null && shops.size() > 0) {
	// return shops.get(0);
	// }
	// return null;
	// }
}