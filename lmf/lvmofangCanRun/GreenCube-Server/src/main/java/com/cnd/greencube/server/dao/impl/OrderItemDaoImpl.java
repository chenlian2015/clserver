package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.OrderItemDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.OrderItem;

@Repository("OrderItemDaoImpl")
public class OrderItemDaoImpl extends RedisDaoSupportImpl<OrderItem, String> implements OrderItemDao {

}
