package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.CouponDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Coupon;

@Repository("CouponDaoImpl")
public class CouponDaoImpl extends RedisDaoSupportImpl<Coupon, String> implements CouponDao {
}
