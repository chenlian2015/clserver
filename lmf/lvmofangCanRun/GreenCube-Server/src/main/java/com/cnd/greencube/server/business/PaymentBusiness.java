/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.FiPayment;
import com.cnd.greencube.server.entity.FiPaymentMethod;
import com.cnd.greencube.server.util.PageInfo;
import com.pingplusplus.model.Charge;

/**
 * 支付服务实现类
 * 
 * @author huxg
 * 
 */
public interface PaymentBusiness {

	/**
	 * 确认支付
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回订单详情及可选择的支付方式列表
	 */

	String confirm(String orderid);
	
	/**
	 * 支付会员费或者管理费
	 * 
	 * @param paymentMethod
	 *            -- 支付渠道
	 * @param amount
	 *            -- 支付金额
	 * @return 返回确认订单的相关信息
	 */
	Charge checkout(String paymentSubject, int type, String paymentMethod, String paierId, int fee, String clientIp, String subject, String body,
			String description) throws Exception;
	
	/**
	 * 账户余额支付
	 */
	void directPay(String paymentId, String password)  throws Exception ;
	
	/**
	 * 支付给某一个用户
	 * @param paymentSubject
	 * @param paymentMethod
	 * @param fee
	 * @param clientIp
	 * @param subject
	 * @param body
	 * @param description
	 * @return
	 */
//	Charge payUser(String paymentSubject, String paymentMethod, String paierId, String receiverId, int fee, String clientIp, String subject,
//			String body, String description) throws Exception;
	
	/**
	 * 充值
	 * @param paymentMethod
	 * @param userid
	 * @param fee
	 * @param clientIp
	 * @return
	 * @throws Exception
	 */
	Charge charge(String tranType, String paymentMethod, String userid, int fee, String clientIp) throws Exception;
//	/**
//	 * 校验支付反馈 <br>
//	 * 支付完毕后，银联或第三方支付会向我们的网关反馈支付信息，本方法用于验证反馈的支付信息
//	 * 
//	 * @param paymentid
//	 *            -- 支付交易id
//	 * @return -- 返回成功与否标志
//	 */
//
//	boolean verifyPaymentFeedback(String paymentId);

	/**
	 * 完成一笔支付交易
	 * 
	 * @param paymentId
	 *            -- 支付交易id
	 * @return 返回成功与否标志
	 */

//	String handlePaymentSuccess(String paymentId);

	/**
	 * 通知支付失败
	 * 
	 * @param paymentId
	 *            --支付交易id
	 * @return 返回成功与否标志
	 */

//	String handlePaymentFailed(String paymentId);

	/**
	 * 查看平台内所有发生的支付记录
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 支付记录json数组
	 */

	List<FiPayment> loadPaymentsForPagelit(PageInfo pageInfo);

	/**
	 * 获取全部支付方式列表
	 * 
	 * @return
	 */
	List<FiPaymentMethod> loadAvaliablePaymentMethod(String client);

	/**
	 * 取得一个支付方式
	 * 
	 * @param paymentMethodId
	 * @return
	 */
	FiPaymentMethod getPaymentMethod(String paymentMethodId);

	/**
	 * 保存支付方式
	 * 
	 * @return
	 */
	void savePaymentMethod(FiPaymentMethod paymentMethod);

	/**
	 * 更新支付方式
	 * 
	 * @param paymentMethod
	 */
	void updatePaymentMethod(FiPaymentMethod paymentMethod);

	/**
	 * 更新支付方式
	 * 
	 * @param paymentMethod
	 */
	void deletePaymentMethod(String paymentMethodId);

	/**
	 * 启用一种支付方式
	 * 
	 * @param arg0
	 */
	void enablePaymentMethod(String paymentMethodId);

	/**
	 * 禁用一种支付方式
	 * 
	 * @param arg0
	 */
	void disablePaymentMethod(String paymentMethodId);
	
	/**
	 * 完成支付
	 */
	void finishPayment(String paymentId);
	
	/**
	 * 取消支付
	 * @param paymentId
	 */
	void cancelPayment(String paymentId);
}
