package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.ShopOrderDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.ShopOrder;

@Repository("ShopOrderDaoImpl")
public class ShopOrderDaoImpl extends RedisDaoSupportImpl<ShopOrder, String> implements ShopOrderDao {

}
