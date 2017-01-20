/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Coupon;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 券服务类
 * 
 * @author huxg
 * 
 */
public interface CouponBusiness {

	List<Coupon> loadShopCoupons(String shopId, PageInfo pageInfo);

	List<Coupon> loadMyCoupons(String userid, PageInfo pageInfo);

	Coupon getCouponById(String id);

	Coupon getCouponBySerial(String serial);

	boolean saveCoupon(Coupon coupon);

	boolean updateCoupon(Coupon coupon);

	boolean deleteCouponById(String id);

	boolean isValidSerial(String serial);

	boolean isTerminate(String couponId);

	boolean freezeCoupon(String card, String userId);
}
