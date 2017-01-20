/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.CouponBusiness;
import com.cnd.greencube.server.entity.Coupon;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.CouponService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
/**
 * 券服务类
 * 
 * @author huxg
 * 
 */
public class CouponServiceImpl extends AbstractServiceImpl implements CouponService.Iface {
	private static final Logger logger = Logger.getLogger(CouponServiceImpl.class);

	@Resource(name = "CouponBusinessImpl")
	protected CouponBusiness couponBusiness;

	@Override
	public String loadShopCoupons(String shopId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Coupon> coupons = couponBusiness.loadShopCoupons(shopId, pageInfo);
			return Message.okMessage(coupons, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadMyCoupons(String userid, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Coupon> coupons = couponBusiness.loadMyCoupons(userid, pageInfo);
			return Message.okMessage(coupons, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getCouponById(String id) throws TException {
		try {
			Coupon c = couponBusiness.getCouponById(id);
			return Message.okMessage(c);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getCouponBySerial(String coupon) throws TException {
		try {
			Coupon c = couponBusiness.getCouponBySerial(coupon);
			return Message.okMessage(c);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String saveCoupon(String json) throws TException {
		try {
			Coupon c = JsonUtils.String2Bean(json, Coupon.class);
			couponBusiness.saveCoupon(c);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateCoupon(String json) throws TException {
		try {
			Coupon c = JsonUtils.String2Bean(json, Coupon.class);
			couponBusiness.updateCoupon(c);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}

	}

	@Override
	public String deleteCouponById(String id) throws TException {
		try {
			couponBusiness.deleteCouponById(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}

	}

	@Override
	public String isValidSerial(String serial) throws TException {
		try {
			boolean isValid = couponBusiness.isValidSerial(serial);
			return isValid ? Message.okMessage() : Message.error();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String isTerminate(String couponId) throws TException {
		try {
			boolean isTerminate = couponBusiness.isTerminate(couponId);
			return isTerminate ? Message.okMessage() : Message.error();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String freezeCoupon(String card, String userId) throws TException {
		try {
			couponBusiness.freezeCoupon(card, userId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
}
