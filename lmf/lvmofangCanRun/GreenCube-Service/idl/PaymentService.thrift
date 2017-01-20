namespace java com.cnd.greencube.server.service

/**
 * 支付记录服务
 * @author 胡晓光
 */
service PaymentService {
	
	/**
	 * 返回所有可用的支付方式
	 */
	string loadAvaliablePaymentMethod(1:string clientType),
	
	/**
	 * 取得一个支付方式
	 * 
	 * @param paymentMethodId
	 * @return
	 */
	string getPaymentMethod(1:string paymentMethodId);

	/**
	 * 保存支付方式
	 * 
	 * @return
	 */
	string savePaymentMethod(1:string paymentMethod);

	/**
	 * 更新支付方式
	 * 
	 * @param paymentMethod
	 */
	string updatePaymentMethod(1:string paymentMethod);

	/**
	 * 返回所有可用的支付方式
	 */
	string enablePaymentMethod(1:string paymentMethodId),
	
	/**
	 * 返回所有可用的支付方式
	 */
	string disablePaymentMethod(1:string paymentMethodId),
	
	/**
	 * 更新支付方式
	 * 
	 * @param paymentMethod
	 */
	string deletePaymentMethod(1:string paymentMethodId);
	
	/**
	 * 确认支付
	 * @param orderid -- 订单id
	 * @return 返回订单详情及可选择的支付方式列表
	 */
	string confirm(1:string orderid),
	
	/**
	 * 获取charge对象
	 * @param paymentSubject -- 支付科目
	 * @param paymentMethod -- 支付渠道
	 * @param type -- 费用科目
	 * @param amount -- 支付金额
	 * @return 返回确认订单的相关信息
	 */
	string checkout(1:string paymentSubject, 2:string paymentMethod, 3:i32 type ,4:string token, 5:string fee, 6:string __remoteAddr, 7:string subject, 8:string body, 9:string description) ,

    /**
	 * 账户余额支付
	 * @param paymentSubject -- 支付科目
	 * @param paymentMethod -- 支付渠道
	 * @param type -- 费用科目
	 * @param amount -- 支付金额
	 * @return 返回确认订单的相关信息
	 */
	string directPay(1:string charge, 2:string password) ,
		
	/**
	 * 完成一笔支付
	 * @paymentId -- 支付id
	 */
	string finishPayment(1:string paymentId),
	
	/**
	 * 取消一笔支付
	 * @paymentId -- 支付id
	 */
	string cancelPayment(1:string paymentId)
}