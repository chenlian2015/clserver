package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Shop;

public interface ShopDao extends BaseDao<Shop, String> {
	List<Shop> getShopsByUserId(String userId);
}