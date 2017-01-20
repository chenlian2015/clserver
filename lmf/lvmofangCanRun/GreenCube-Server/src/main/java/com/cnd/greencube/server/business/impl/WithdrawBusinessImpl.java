package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.WithdrawBusiness;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.VirtualAccountDao;
import com.cnd.greencube.server.dao.WithdrawDao;
import com.cnd.greencube.server.entity.FiWithdraw;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

@SuppressWarnings("rawtypes")
@Service("WithdrawBusinessImpl")
public class WithdrawBusinessImpl extends BaseBusinessImpl implements WithdrawBusiness {
	@Resource(name = "WithdrawDaoImpl")
	WithdrawDao withdrawDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "VirtualAccountDaoImpl")
	VirtualAccountDao virtualAccountDao;

	/**
	 * 申请转账
	 */
	@Transactional
	@Override
	public FiWithdraw applyWithdraw(String userid, String password, String bankcardId, int amount) throws Exception {
		User user = userDao.get(userid);
		FiWithdraw withdraw = new FiWithdraw();
		withdraw.setUserId(userid);
		withdraw.setUserName(user.getName());
		withdraw.setBankId(bankcardId);
		withdraw.setAmount(amount);
		withdraw.setStatus(1);
		withdraw.setApplyTime(new Date());
		withdrawDao.save(withdraw);
		return withdraw;
	}

	@Transactional
	@Override
	public List<FiWithdraw> loadWithdraw(PageInfo pageInfo) {
		String sql = "select t.id from FiWithdraw t";
		String ctql = "select count(t) from FiWithdraw t";
		return withdrawDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean rejectWithdraw(String id) {
		FiWithdraw withdraw = withdrawDao.get(id);
		withdraw.setStatus(3);
		withdrawDao.update(withdraw);
		return false;
	}

	@Override
	public boolean approveWithdraw(String id, String status) {
		FiWithdraw withdraw = withdrawDao.get(id);
		withdraw.setStatus(2);
		withdrawDao.update(withdraw);
		return true;
	}

	/**
	 * 返回在几个小时之内的转账申请
	 */
	@Override
	public List<FiWithdraw> loadWithdrawApplyBeforeHours(int hour, PageInfo pageInfo) {
		Date begin = new Date();
		Date before = beforeHour(hour, begin);
		String sql = "select t.id from FiWithdraw t where t.status = 1 ";

		if (null != pageInfo) {
			String ctql = "select count(t) from FiWithdraw t where t.status = 1";
			List<Object> params = new ArrayList<Object>();
			if (hour > 0) {
				sql += " and applyTime <= ?";
				ctql += " and applyTime <= ?";
				params.add(before);
			}
			sql += " order by applyTime desc";
			return withdrawDao.findList(sql, ctql, params.toArray(), pageInfo);
		} else {
			List<Object> params = new ArrayList<Object>();
			if (hour > 0) {
				sql += " and applyTime <= ?";
				params.add(before);
			}
			sql += " order by applyTime desc";
			return withdrawDao.findList(sql, params.toArray());
		}
	}

	/**
	 * 获取几个小时之前
	 * 
	 * @param hour
	 * @param current
	 * @return
	 */
	Date beforeHour(int hour, Date current) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(current);
		calendar.add(Calendar.HOUR_OF_DAY, -hour);
		Date date = calendar.getTime();
		return date;
	}
}
