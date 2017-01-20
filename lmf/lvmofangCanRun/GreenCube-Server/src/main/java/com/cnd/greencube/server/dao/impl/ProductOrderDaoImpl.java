package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.ProductOrderDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.ProductOrder;

@Repository("ProductOrderDaoImpl")
public class ProductOrderDaoImpl extends RedisDaoSupportImpl<ProductOrder, String> implements ProductOrderDao {

}
