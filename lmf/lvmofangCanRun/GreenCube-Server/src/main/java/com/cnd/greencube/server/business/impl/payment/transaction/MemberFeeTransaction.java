package com.cnd.greencube.server.business.impl.payment.transaction;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.dao.FiMemberFeeDao;
import com.cnd.greencube.server.dao.FiPlatformAccountDao;
import com.cnd.greencube.server.dao.MemberDao;
import com.cnd.greencube.server.dao.ShopDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.entity.FiMemberFee;
import com.cnd.greencube.server.entity.FiPayment;
import com.cnd.greencube.server.entity.FiPlatformAccount;
import com.cnd.greencube.server.entity.Member;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.DateUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 会费缴纳交易
 * 
 * @author huxg
 * 
 */
@Service("MemberFeeTransaction")
public class MemberFeeTransaction implements ITransaction {
	@Resource(name = "FiMemberFeeDaoImpl")
	FiMemberFeeDao memberFeeDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "ShopDaoImpl")
	ShopDao memberShopDao;

	@Resource(name = "MemberDaoImpl")
	MemberDao memberDao;

	@Resource(name = "VirtualAccountDaoImpl")
	VirtualAccountDao virtualAccountDao;

	@Resource(name = "FiPlatformAccountDaoImpl")
	private FiPlatformAccountDao fiPlatformAccountDao;

	@Override
	public void beforeDeal(Object... params) {
		FiPayment payment = (FiPayment) params[0];

		// 生成会员费缴费记录
		FiMemberFee memberFee = new FiMemberFee();
		memberFee.setUserid(payment.getExpendUserId());
		memberFee.setUserName(payment.getExpendUserName());
		memberFee.setPaymentId(payment.getId());
		memberFeeDao.save(memberFee);
	}

	/**
	 * 生成会费缴纳记录
	 */
	@Override
	public void afterDeal(Object... params) {
		FiPayment payment = (FiPayment) params[0];

		// 生成会员费缴费记录
		FiMemberFee memberFee = memberFeeDao.getMemberFeeByPaymentId(payment.getId());
		memberFee.setPayTime(new Date());
		memberFee.setFee(payment.getFee());
		memberFeeDao.update(memberFee);

		// 发送身份变动通知
		User user = userDao.get(memberFee.getUserid());

		int usertype = StringUtils.isEmpty(user.getUserType()) ? User.USERTYPE_USER | User.USERTYPE_MEMBER : Integer.parseInt(user.getUserType()) | User.USERTYPE_MEMBER;
		user.setUserType(String.valueOf(usertype));
		userDao.update(user);

		// 设置会员额外身份
		Member member = memberDao.getMember(user.getId());
		if (member != null) {
		} else {
			// 生成一条会员记录，并保存到数据库中去
			member = new Member();
			member.setUserId(user.getId());
		}
		member.setPaidMemberFee(1);
		member.setIsValid(1);

		Date now = new Date();
		Date terminate = DateUtils.nextYear(now);
		member.setBeginTime(new Date());
		member.setEndTime(terminate);
		memberDao.update(member);

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
