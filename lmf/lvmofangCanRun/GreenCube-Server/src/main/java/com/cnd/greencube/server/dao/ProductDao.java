package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.Product;

public interface ProductDao extends BaseDao<Product, String> {
	List<Product> getHotProducts(int count);
}
