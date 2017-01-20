package com.cnd.greencube.server.business.impl.payment.transaction;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.dao.FiManagerFeeDao;
import com.cnd.greencube.server.dao.FiPlatformAccountDao;
import com.cnd.greencube.server.dao.MemberDao;
import com.cnd.greencube.server.dao.ShopDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.entity.FiManagerFee;
import com.cnd.greencube.server.entity.FiPayment;
import com.cnd.greencube.server.entity.FiPlatformAccount;
import com.cnd.greencube.server.entity.Member;
import com.cnd.greencube.server.entity.Shop;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.DateUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 管理费交易
 * 
 * @author huxg
 * 
 */
@Service("ManagerFeeTransaction")
public class ManagerFeeTransaction implements ITransaction {
	@Resource(name = "FiManagerFeeDaoImpl")
	FiManagerFeeDao managerFeeDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "MemberDaoImpl")
	MemberDao memberDao;

	@Resource(name = "ShopDaoImpl")
	ShopDao shopDao;

	@Resource(name = "MemberDaoImpl")
	MemberDao memberProviderDao;

	@Resource(name = "VirtualAccountDaoImpl")
	VirtualAccountDao virtualAccountDao;

	@Resource(name = "FiPlatformAccountDaoImpl")
	private FiPlatformAccountDao fiPlatformAccountDao;

	@Override
	public void beforeDeal(Object... params) {
		FiPayment payment = (FiPayment) params[0];

		// 生成会员费缴费记录
		FiManagerFee managerFee = new FiManagerFee();
		managerFee.setUserid(payment.getExpendUserId());
		managerFee.setUserName(payment.getExpendUserName());
		managerFee.setPaymentId(payment.getId());
		managerFeeDao.save(managerFee);
	}

	/**
	 * 生成会费缴纳记录
	 */
	@Override
	public void afterDeal(Object... params) {
		FiPayment payment = (FiPayment) params[0];

		// 生成会员费缴费记录
		FiManagerFee managerFee = managerFeeDao.getManagerFeeByPaymentId(payment.getId());
		managerFee.setPayTime(new Date());
		managerFee.setFee(payment.getFee());
		managerFeeDao.update(managerFee);

		// 发送身份变动通知
		User user = userDao.get(managerFee.getUserid());
		int usertype = StringUtils.isEmpty(user.getUserType()) ? User.USERTYPE_USER | User.USERTYPE_SHOP : Integer.parseInt(user.getUserType())
				| User.USERTYPE_SHOP;
		user.setUserType(String.valueOf(usertype));
		userDao.update(user);
		
		// 缴纳的是店主会会费
		Shop shop = shopDao.get(managerFee.getShopId());
		if (shop != null) {
			shop.setPaidManagerFee(1);

			// 判断是否缴纳了会员费，如果缴纳了会员费，则店铺自动设置为有效
			Member member = memberDao.getMember(user.getId());
			if (member != null && member.getIsValid() != null && member.getIsValid().intValue() == 1 && member.getPaidMemberFee() != null
					&& member.getPaidMemberFee().intValue() == 1) {
				shop.setIsValid(1);
				Date now = new Date();
				Date terminate = DateUtils.nextYear(now);
				shop.setValidBegin(new Date());
				shop.setValidEnd(terminate);
			}
			shopDao.update(shop);
		}

		// 充值完毕后，增加平台总收入
		FiPlatformAccount pa = fiPlatformAccountDao.getAccount();
		pa.setBalance(pa.getBalance() == null ? (long) payment.getFee().intValue() : pa.getBalance().intValue() + payment.getFee().intValue());
		pa.setTotalIncome(pa.getTotalIncome() == null ? (long) payment.getFee().intValue() : pa.getTotalIncome().intValue() + payment.getFee().intValue());
		fiPlatformAccountDao.update(pa);
	}

	@Override
	public void cancelDeal(Object... params) {
		// TODO Auto-generated method stub
	}
}
