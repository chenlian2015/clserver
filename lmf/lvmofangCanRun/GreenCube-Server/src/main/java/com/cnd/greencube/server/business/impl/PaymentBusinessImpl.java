/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.PaymentBusiness;
import com.cnd.greencube.server.business.impl.payment.charge.ChannelFactory;
import com.cnd.greencube.server.business.impl.payment.charge.IChannel;
import com.cnd.greencube.server.business.impl.payment.charge.IChannelFactory;
import com.cnd.greencube.server.business.impl.payment.transaction.ITransaction;
import com.cnd.greencube.server.business.impl.payment.transaction.ITransactionFactory;
import com.cnd.greencube.server.business.impl.payment.transaction.TransactionFactory;
import com.cnd.greencube.server.dao.FiPaymentDao;
import com.cnd.greencube.server.dao.FiPaymentMethodDao;
import com.cnd.greencube.server.dao.FiPlatformAccountDao;
import com.cnd.greencube.server.dao.OrderDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.entity.FiPayment;
import com.cnd.greencube.server.entity.FiPaymentMethod;
import com.cnd.greencube.server.entity.Order;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.encrypt.Encryption;
import com.ibm.icu.text.SimpleDateFormat;
import com.pingplusplus.model.Charge;

/**
 * 支付服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("PaymentBusinessImpl")
public class PaymentBusinessImpl extends BaseBusinessImpl implements PaymentBusiness {
	@Resource(name = "OrderDaoImpl")
	private OrderDao orderDao;

	@Resource(name = "UserDaoImpl")
	private UserDao userDao;

	@Resource(name = "VirtualAccountDaoImpl")
	private VirtualAccountDao virtualAccountDao;

	@Resource(name = "FiPlatformAccountDaoImpl")
	private FiPlatformAccountDao fiPlatformAccountDao;

	@Resource(name = "FiPaymentDaoImpl")
	private FiPaymentDao paymentDao;

	@Resource(name = "FiPaymentMethodDaoImpl")
	private FiPaymentMethodDao paymentMethodDao;

	@Resource(name = "paymentSubjectCommand")
	private Map<String, String> mapPaymentSubjectCommand;

	/**
	 * 确认支付
	 * 
	 * @param orderid
	 *            -- 订单id
	 * @return 返回订单详情及可选择的支付方式列表
	 */
	@Override
	public String confirm(String orderid) {
		try {
			Order order = orderDao.get(orderid);
			// 取得支付方式
			return Message.okMessage(order);
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 
	 * @param paymentSubject
	 *            -- 支付科目，参见支付科目表，goods/manager_fee/member_fee/user_charge
	 * @param paymentMethod
	 *            -- 支付渠道，选定的支付渠道
	 * @param paierId
	 *            -- 支付者id
	 * @param fee
	 *            -- 支付金额
	 * @param clientIp
	 *            -- 支付者ip地址
	 * @param subject
	 *            -- 名称
	 * @param body
	 *            -- 内容
	 * @param description
	 *            -- 相关描述
	 * @return
	 */
	@Transactional
	@Override
	public Charge checkout(String paymentSubject, int type, String paymentMethod, String paierId, int fee, String clientIp, String subject, String body,
			String description) throws Exception {
		User user = userDao.get(paierId);

		// 完成结算，开始做交易记录
		FiPayment payment = new FiPayment();

		payment.setTranType(paymentSubject);

		// 设置支付金额
		payment.setFee(fee);

		// 设置创建时间
		payment.setCreateTime(new Date());

		// 支付渠道
		payment.setMethod(paymentMethod);

		// 支付者id
		payment.setExpendUserId(user.getId());

		payment.setExpendUserName(user.getName());

		// 保存所有交易记录数据
		paymentDao.save(payment);

		ITransaction tran = TransactionFactory.getFactory().getTransaction(paymentSubject);
		tran.beforeDeal(payment, new Integer(type));

		IChannelFactory chargeFacory = ChannelFactory.getFactory();
		IChannel channel = chargeFacory.createChargeChannel(paymentMethod, payment.getId(), fee, clientIp, subject, body, description);
		Charge charge = channel.createCharge();
		return charge;
	}

	/**
	 * 账户余额支付
	 */
	@Transactional
	@Override
	public void directPay(String paymentId, String password) throws Exception {
		FiPayment payment = paymentDao.get(paymentId);

		// 扣减用户虚拟账户资金
		VirtualAccount va = virtualAccountDao.getUserVirtualAccount(payment.getExpendUserId());
		if (va.getAccountPassword() == null) {
			throw new Exception("尚未设置账户密码，不能进行交易");
		}

		if (!Encryption.encodeMD5(password).equals(va.getAccountPassword())) {
			throw new Exception("取款密码有误，不能进行交易");
		}

		if (va == null || va.getBalance() == null || va.getBalance().intValue() < payment.getFee().intValue())
			throw new Exception("账户余额不足");

		int newBalance = va.getBalance().intValue() - payment.getFee().intValue();
		va.setBalance(newBalance);
		virtualAccountDao.update(va);
	}

	/**
	 * 
	 * @param paymentSubject
	 *            -- 支付科目，参见支付科目表
	 * @param paymentMethod
	 *            -- 支付渠道，选定的支付渠道
	 * @param paierId
	 *            -- 支付者id
	 * @param receiverId
	 *            -- 收款者id
	 * @param fee
	 *            -- 支付金额
	 * @param clientIp
	 *            -- 支付者ip地址
	 * @param subject
	 *            -- 名称
	 * @param body
	 *            -- 内容
	 * @param description
	 *            -- 相关描述
	 * @return
	 */
	// @Transactional
	// @Override
	// public Charge payUser(String tranType, String paymentMethod, String
	// paierId, String receiverId, int fee, String clientIp, String subject,
	// String body,
	// String description) throws Exception {
	// ITransaction tran =
	// TransactionFactory.getFactory().getTransaction(tranType);
	// tran.beforeDeal();
	//
	// User paier = userDao.get(paierId);
	// User receiver = userDao.get(receiverId);
	//
	// // 完成结算，开始做交易记录
	// FiPayment payment = new FiPayment();
	//
	// payment.setTranType(tranType);
	//
	// // 设置支付金额
	// payment.setFee(fee);
	//
	// // 设置创建时间
	// payment.setCreateTime(new Date());
	//
	// // 支付渠道
	// payment.setMethod(paymentMethod);
	//
	// // 支付者id
	// payment.setExpendUserId(paier.getId());
	//
	// payment.setExpendUserName(paier.getName());
	//
	// payment.setIncomeUserId(receiver.getId());
	//
	// payment.setIncomeUserName(receiver.getName());
	//
	// // 保存所有交易记录数据
	// paymentDao.save(payment);
	//
	// IChannelFactory chargeFacory = ChannelFactory.getFactory();
	// IChannel channel = chargeFacory.createChargeChannel(paymentMethod,
	// payment.getId(), fee, clientIp, subject, body, description);
	//
	// Charge charge = channel.createCharge();
	// return charge;
	// }

	// /**
	// * 取得支付科目
	// *
	// * @param paymentSubject
	// * @return
	// * @throws Exception
	// */
	// IPaymentSubjectRequest gePaymentSubjectRequest(String paymentSubject)
	// throws Exception {
	// if (null == mapPaymentSubjectCommand || mapPaymentSubjectCommand.size()
	// == 0 || !mapPaymentSubjectCommand.containsKey(paymentSubject)) {
	// throw new Exception("无法找到对应的支付科目");
	// }
	// String paymentSubjectRequest =
	// mapPaymentSubjectCommand.get(paymentSubject);
	// return (IPaymentSubjectRequest)
	// SpringUtils.getBean(paymentSubjectRequest);
	// }

	/**
	 * 充值的实现
	 */
	@Override
	public Charge charge(String tranType, String paymentMethod, String userid, int fee, String clientIp) throws Exception {
		// 判断当前采用的是哪种支付方式，如果是现金交易，则采用现金交易模式，如果是虚拟账户交易，则采用虚拟账户交易模式
		User user = userDao.get(userid);

		// 完成结算，开始做交易记录
		FiPayment payment = new FiPayment();

		payment.setTranType(tranType);

		// 设置支付金额
		payment.setFee(fee);

		// 设置创建时间
		payment.setCreateTime(new Date());

		// 支付渠道
		payment.setMethod(paymentMethod);

		// 支付者id
		payment.setExpendUserId(user.getId());

		payment.setExpendUserName(user.getName());

		// 保存所有交易记录数据
		paymentDao.save(payment);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ITransaction tran = TransactionFactory.getFactory().getTransaction(tranType);
		tran.beforeDeal(payment, new Integer(tranType));

		String now = sf.format(new Date());
		String subject = now + " 充值" + ((int) fee / 100) + "元";
		IChannelFactory chargeFacory = ChannelFactory.getFactory();
		IChannel channel = chargeFacory.createChargeChannel(paymentMethod, payment.getId(), fee, clientIp, subject, subject, subject);
		Charge charge = channel.createCharge();
		return charge;
	}

	/**
	 * 查看平台内所有发生的支付记录
	 * 
	 * @param pageNum
	 *            -- 页数
	 * @return 支付记录json数组
	 */
	@Override
	public List<FiPayment> loadPaymentsForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from FiPayment t order by createTime desc";
		String ctql = "select count(t.id) from FiPayment t ";
		return paymentDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public List<FiPaymentMethod> loadAvaliablePaymentMethod(String client) {
		String sql = "select t.id from FiPaymentMethod t ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmptyAfterTrim(client)) {
			sql += "where clientType = ?";
			params.add(client);
		}
		sql += "order by t.order asc";
		return paymentMethodDao.findList(sql, params.toArray());
	}

	@Override
	public FiPaymentMethod getPaymentMethod(String paymentMethodId) {
		return paymentMethodDao.get(paymentMethodId);
	}

	@Transactional
	@Override
	public void savePaymentMethod(FiPaymentMethod paymentMethod) {
		paymentMethodDao.save(paymentMethod);
	}

	@Transactional
	@Override
	public void updatePaymentMethod(FiPaymentMethod paymentMethod) {
		paymentMethodDao.update(paymentMethod);
	}

	@Transactional
	@Override
	public void deletePaymentMethod(String paymentMethodId) {
		paymentMethodDao.delete(paymentMethodId);
	}

	/**
	 * 启用一种支付方式
	 * 
	 * @param arg0
	 */
	@Transactional
	@Override
	public void enablePaymentMethod(String paymentMethodId) {
		FiPaymentMethod method = paymentMethodDao.get(paymentMethodId);
		method.setEnable(1);
		paymentMethodDao.update(method);
	}

	/**
	 * 禁用一种支付方式
	 * 
	 * @param arg0
	 */
	@Transactional
	@Override
	public void disablePaymentMethod(String paymentMethodId) {
		FiPaymentMethod method = paymentMethodDao.get(paymentMethodId);
		method.setEnable(0);
		paymentMethodDao.update(method);
	}

	/**
	 * 完成支付
	 */
	@Override
	public void finishPayment(String paymentId) {
		FiPayment payment = paymentDao.get(paymentId);
		payment.setStatus(FiPayment.PAY_STATUS_FINISH);
		paymentDao.update(payment);

		try {
			ITransactionFactory factory = TransactionFactory.getFactory();
			ITransaction transaction = factory.getTransaction(payment.getTranType());
			transaction.afterDeal(payment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取消支付
	 */
	@Override
	public void cancelPayment(String paymentId) {
		// 启动线程，设置该笔交易完成状态
		FiPayment payment = paymentDao.get(paymentId);
		payment.setStatus(FiPayment.PAY_STATUS_CANCELD);
		paymentDao.update(payment);
	}
}
