/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.CouponBusiness;
import com.cnd.greencube.server.entity.Coupon;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 券服务类
 * 
 * @author huxg
 * 
 */
@Service("CouponBusinessImpl")
public class CouponBusinessImpl extends BaseBusinessImpl<Coupon, String> implements CouponBusiness {

	@Transactional(readOnly = true)
	@Override
	public List<Coupon> loadShopCoupons(String shopId, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Coupon> loadMyCoupons(String userid, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Coupon getCouponById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Coupon getCouponBySerial(String serial) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public boolean saveCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean deleteCouponById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean isValidSerial(String serial) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean isTerminate(String couponId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean freezeCoupon(String card, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
