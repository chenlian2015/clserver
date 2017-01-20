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

import com.cnd.greencube.server.business.PaymentBusiness;
import com.cnd.greencube.server.business.UserAccountBusiness;
import com.cnd.greencube.server.entity.FiPaymentMethod;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.PaymentService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.TokenUtils;
import com.pingplusplus.model.Charge;

/**
 * 支付服务实现类
 * 
 * @author huxg
 * 
 */
public class PaymentServiceImpl extends AbstractServiceImpl implements PaymentService.Iface {
	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	@Resource(name = "PaymentBusinessImpl")
	protected PaymentBusiness paymentBusiness;

	@Resource(name = "UserAccountBusinessImpl")
	protected UserAccountBusiness userAccountBusiness;

	/**
	 * 确认支付
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回确认订单的相关信息
	 */
	@Override
	public String confirm(String orderid) throws TException {
		try {
			paymentBusiness.confirm(orderid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 缴纳会费
	 * 
	 * @param paymentMethod
	 *            -- 支付渠道
	 * @param amount
	 *            -- 支付金额
	 * @return 返回确认订单的相关信息
	 */
	@Override
	public String checkout(String paymentSubject, String paymentMethod, int type, String token, String fee, String clientIp, String subject, String body,
			String description) throws TException {
		try {
			String userid = TokenUtils.isTokenRight(token);
			
			int amount = Integer.parseInt(fee);

			// 如果当前是账户余额支付，则检查用户账户
			if ("greencube_directpay".equals(paymentMethod)) {
				// 检查是否设置了密码
				VirtualAccount va = userAccountBusiness.getUserAccount(userid);
				if (va == null) {
					// 为该用户创建一个账户
					va = userAccountBusiness.createUserAccountForUser(userid);
				}

				if (null == va.getAccountPassword())
					return Message.errorMessage(new String[] { "errorno" }, new String[] { "-1" });

				if (va.getBalance() == null || va.getBalance().intValue() < amount)
					return Message.errorMessage(new String[] { "errorno" }, new String[] { "-2" });
			}

			Charge charge = paymentBusiness.checkout(paymentSubject, type, paymentMethod, userid, amount, clientIp, subject, body, description);
			return Message.okMessage(charge);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return Message.error();
		}
	}

	/**
	 * 账户余额支付
	 */
	@Override
	public String directPay(String charge, String password) {
		try {
			Charge c = JsonUtils.String2Bean(charge, Charge.class);
			
			// 完成支付
			paymentBusiness.directPay(c.getOrderNo(), password);

			// 完成支付后续操作
			finishPayment(c.getOrderNo());
			return Message.okMessage();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return Message.errorMessage(e.getMessage());
		}
	}

	// /**
	// * 缴纳管理费
	// *
	// * @param paymentMethod
	// * -- 支付渠道
	// * @param amount
	// * -- 支付金额
	// * @return 返回确认订单的相关信息
	// */
	// @Override
	// public String payManagerFee(String paymentMethod, int type, String
	// paierId, String fee, String clientIp, String subject, String body, String
	// description)
	// throws TException {
	// try {
	// int amount = Integer.parseInt(fee);
	// paymentBusiness.payMemberOrManagerFee(FiPayment.SUBJECT_MANAGER_FEE,
	// type, paymentMethod, paierId, amount, clientIp, subject, body,
	// description);
	// return Message.okMessage();
	// } catch (Exception e) {
	// logger.error(e);
	// return Message.error();
	// }
	// }

	/**
	 * 校验支付反馈 <br>
	 * 支付完毕后，银联或第三方支付会向我们的网关反馈支付信息，本方法用于验证反馈的支付信息
	 * 
	 * @param paymentid
	 *            -- 支付交易id
	 * @return -- 返回成功与否标志
	 */
	// @Override
	// public String verifyPaymentFeedback(String paymentId) throws TException {
	// try {
	// paymentBusiness.verifyPaymentFeedback(paymentId);
	// return Message.okMessage();
	// } catch (Exception e) {
	// logger.error(e);
	// return Message.error();
	// }
	// }

	/**
	 * 完成一笔支付交易
	 * 
	 * @param paymentId
	 *            -- 支付交易id
	 * @return 返回成功与否标志
	 */
	// @Override
	// public String handlePaymentSuccess(String paymentId) throws TException {
	// try {
	// return paymentBusiness.handlePaymentSuccess(paymentId);
	// } catch (Exception e) {
	// logger.error(e);
	// return Message.error();
	// }
	// }

	/**
	 * 通知支付失败
	 * 
	 * @param paymentId
	 *            --支付交易id
	 * @return 返回成功与否标志
	 */
	// @Override
	// public String handlePaymentFailed(String paymentId) throws TException {
	// try {
	// return paymentBusiness.handlePaymentFailed(paymentId);
	// } catch (Exception e) {
	// logger.error(e);
	// return Message.error();
	// }
	// }

	/**
	 * 查看平台内所有发生的支付记录
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 支付记录json数组
	 */
	// @Override
	// public String loadPaymentsForPagelit(int pageNum) throws TException {
	// try {
	// PageInfo pageInfo = initPageInfo(pageNum);
	// List<FiPayment> payments =
	// paymentBusiness.loadPaymentsForPagelit(pageInfo);
	// return Message.okMessage(payments, pageInfo);
	// } catch (Exception e) {
	// logger.error(e);
	// return Message.error();
	// }
	// }

	/**
	 * 返回所有可用的支付方式
	 */
	public String loadAvaliablePaymentMethod(String clientType) throws org.apache.thrift.TException {
		try {
			List<FiPaymentMethod> methods = paymentBusiness.loadAvaliablePaymentMethod(clientType);
			return Message.okMessage(methods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String deletePaymentMethod(String arg0) throws TException {
		try {
			paymentBusiness.deletePaymentMethod(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getPaymentMethod(String arg0) throws TException {
		try {
			return Message.okMessage(paymentBusiness.getPaymentMethod(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String savePaymentMethod(String arg0) throws TException {
		try {
			FiPaymentMethod method = JsonUtils.String2Bean(arg0, FiPaymentMethod.class);
			paymentBusiness.savePaymentMethod(method);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updatePaymentMethod(String arg0) throws TException {
		try {
			FiPaymentMethod method = JsonUtils.String2Bean(arg0, FiPaymentMethod.class);
			paymentBusiness.updatePaymentMethod(method);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String enablePaymentMethod(String arg0) throws TException {
		try {
			paymentBusiness.disablePaymentMethod(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String disablePaymentMethod(String arg0) throws TException {
		try {
			paymentBusiness.disablePaymentMethod(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String cancelPayment(String arg0) throws TException {
		try {
			paymentBusiness.cancelPayment(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String finishPayment(String arg0) throws TException {
		try {
			paymentBusiness.finishPayment(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
