package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.UserAccountBusiness;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.dao.VirtualAccountDetailDao;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.entity.VirtualAccountDetail;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.encrypt.Encryption;

/**
 * 用户账户业务类
 * 
 * @author cndini
 * 
 */
@SuppressWarnings("rawtypes")
@Service("UserAccountBusinessImpl")
public class UserAccountBusinessImpl extends BaseBusinessImpl implements UserAccountBusiness {

	@Resource(name = "VirtualAccountDaoImpl")
	VirtualAccountDao virtualAccountDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "VirtualAccountDetailDaoImpl")
	VirtualAccountDetailDao userAccountDetailDao;

	/**
	 * 返回用户收入明细
	 */
	@Transactional
	@Override
	public List<VirtualAccountDetail> loadUserIncomeDetail(String userid, PageInfo pageInfo) {
		String sql = "select t.id from VirtualAccountDetail t where t.paymentType = 1 and t.userId = ? order by tranTime desc";
		String ctql = "select count(t) from VirtualAccountDetail t where t.paymentType = 1 and t.userId = ?";
		return userAccountDetailDao.findList(sql, ctql, new Object[] { userid }, pageInfo);
	}

	/**
	 * 返回用户支出明细
	 */
	@Transactional
	@Override
	public List<VirtualAccountDetail> loadUserPaymentDetail(String userid, PageInfo pageInfo) {
		String sql = "select t.id from VirtualAccountDetail t where t.paymentType = 2 and t.userId = ? order by tranTime desc";
		String ctql = "select count(t) from VirtualAccountDetail t where t.paymentType = 2 and t.userId = ?";
		return userAccountDetailDao.findList(sql, ctql, new Object[] { userid }, pageInfo);
	}

	// /***
	// * 返回用户当前余额、总收入、总支出 从用户虚拟账户表 t_virtual_account 中直接查询当前信息
	// *
	// * @param userid
	// * @return
	// * @throws org.apache.thrift.TException
	// */
	// @Transactional
	// @Override
	// public List<VirtualAccount> getUserVirtualAccount(String userid) {
	// String sql =
	// "select a.N_BALANCE, a.N_TOTAL_INCOME, a.N_TOTAL_PAID from T_VIRTUAL_ACCOUNT a where a.C_USER_ID="
	// + userid;
	// List<VirtualAccount> virtualAccount = JdbcAPI.getJdbc().queryExt(sql);
	// return virtualAccount;
	// }

	/**
	 * 取得用户账号
	 */
	@Override
	public VirtualAccount getUserAccount(String userid) {
		String sql = "select t.id from VirtualAccount t where t.userId = ?";
		List<VirtualAccount> vas = virtualAccountDao.findList(sql, new Object[] { userid });
		if (vas != null && vas.size() > 0) {
			return vas.get(0);
		}
		return null;
	}

	/**
	 * 生成用户账号
	 */
	@Transactional
	@Override
	public VirtualAccount createUserAccountForUser(String userid) {
		// 生成虚拟账户
		VirtualAccount va = new VirtualAccount();
		va.setUserId(userid);
		va.setBalance(0);
		va.setCbitBalance(0);
		virtualAccountDao.save(va);
		return va;
	}

	@Transactional
	@Override
	public void setAccountPassword(String userid, String password) {
		VirtualAccount account = virtualAccountDao.getUserVirtualAccount(userid);
		account.setAccountPassword(Encryption.encodeMD5(password));
		virtualAccountDao.update(account);
	}

	@Transactional
	@Override
	public void updateAccountPassword(String userid, String newpwd) throws Exception {
		VirtualAccount account = virtualAccountDao.getUserVirtualAccount(userid);
		account.setAccountPassword(Encryption.encodeMD5(newpwd));
		virtualAccountDao.update(account);
	}

	@Override
	public boolean checkPassword(String userid, String pwd) {
		VirtualAccount account = virtualAccountDao.getUserVirtualAccount(userid);
		String encryptedPwd = Encryption.encodeMD5(pwd);
		return encryptedPwd.equals(account.getAccountPassword());
	}
}
