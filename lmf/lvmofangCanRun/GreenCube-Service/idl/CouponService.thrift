namespace java com.cnd.greencube.server.service

/**
 * 优惠券
 * @author 胡晓光
 */
service CouponService {
	/**
	 * 取得店铺优惠券
	 */
	string loadShopCoupons(1:string shopId, 2:i32 pageNum),
	
	/**
	 * 取得我的优惠券
	 */
	string loadMyCoupons(1:string userid, 2:i32 pageNum),
	
	/**
	 * 根据id取得优惠券
	 */
	string getCouponById(1:string id),
	
	/**
	 * 根据卡号取得优惠券
	 */
	string getCouponBySerial(1:string coupon),
	
	/**
	 * 保存优惠券
	 */
	string saveCoupon(1:string json),
	
	/**
	 * 更新优惠券
	 */
	string updateCoupon(1:string json),
	
	/**
	 * 删除优惠券
	 */
	string deleteCouponById(1:string id),
	
	/**
	 * 判断优惠券是否有效
	 */
	string isValidSerial(1:string serial),
	
	/**
	 * 判断优惠券是否过期
	 */
	string isTerminate(1:string couponId),
	
	/**
	 * 冻结优惠券
	 */
	string freezeCoupon(1:string card, 2:string userId)
}