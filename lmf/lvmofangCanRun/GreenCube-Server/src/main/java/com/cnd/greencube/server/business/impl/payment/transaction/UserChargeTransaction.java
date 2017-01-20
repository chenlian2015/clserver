package com.cnd.greencube.server.business.impl.payment.transaction;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.dao.FiPlatformAccountDao;
import com.cnd.greencube.server.dao.VirtualAccountDetailDao;
import com.cnd.greencube.server.entity.FiPayment;
import com.cnd.greencube.server.entity.FiPlatformAccount;
import com.cnd.greencube.server.entity.VirtualAccountDetail;

/**
 * 用户充值交易
 * 
 * @author huxg
 * 
 */
@Service("UserChargeTransaction")
public class UserChargeTransaction implements ITransaction {
	@Resource(name = "FiPlatformAccountDaoImpl")
	private FiPlatformAccountDao fiPlatformAccountDao;

	@Resource(name = "VirtualAccountDetailDaoImpl")
	private VirtualAccountDetailDao virtualAccountDetailDao;

	@Override
	public void beforeDeal(Object... params) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterDeal(Object... params) {
		// 充值完毕后，增加平台总收入
		FiPayment payment = (FiPayment) params[0];

		// 记录虚拟账户变化明细表中
		VirtualAccountDetail detail = new VirtualAccountDetail();
		detail.setPaymentMethod(payment.getMethod());
		detail.setUserId(payment.getExpendUserId());
		detail.setPaymentType(payment.getTranType());
		detail.setTranTime(new Date());
		detail.setAmount(payment.getFee());
		virtualAccountDetailDao.save(detail);
		
		
		FiPlatformAccount pa = fiPlatformAccountDao.getAccount();
		pa.setUserBalance(pa.getUserBalance() == null ? (long) payment.getFee().intValue() : pa.getUserBalance().intValue() + payment.getFee().intValue());
		pa.setBalance(pa.getBalance() == null ? (long) payment.getFee().intValue() : pa.getBalance().intValue() + payment.getFee().intValue());
		fiPlatformAccountDao.update(pa);
	}

	@Override
	public void cancelDeal(Object... params) {
		// TODO Auto-generated method stub

	}
}
