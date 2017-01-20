package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.ShoppingItemDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.ShoppingItem;

@Repository("ShoppingItemDaoImpl")
public class ShoppingItemDaoImpl extends RedisDaoSupportImpl<ShoppingItem, String> implements ShoppingItemDao {
}
