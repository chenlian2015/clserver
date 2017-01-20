package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.UserBankCardBusiness;
import com.cnd.greencube.server.dao.UserBankCardDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.UserBankCard;

@SuppressWarnings("rawtypes")
@Service("UserBankCardBusinessImpl")
public class UserBankCardBusinessImpl extends BaseBusinessImpl implements UserBankCardBusiness {
	@Resource(name = "UserBankCardDaoImpl")
	UserBankCardDao userBankCardDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	/**
	 * 通過唯一條件得到用戶的一條一行卡信息
	 */
	@Transactional
	@Override
	public int getBankCard(String userid, String cardId) {
		String sql = "select count(t) from UserBankCard t where t.userId = ? and t.cardNum = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(cardId);
		return userBankCardDao.queryCount(sql, params.toArray());
	}

	/**
	 * 綁定銀行卡信息
	 */
	@Transactional
	@Override
	public UserBankCard bindBankCard(String userid, String holderName, String cardNum, String phone, String idcard) {
		UserBankCard userBankCard = new UserBankCard();
		userBankCard.setUserId(userid);
		userBankCard.setCardHolderName(holderName);
		userBankCard.setCardNum(cardNum);
		userBankCard.setPhone(phone);
		userBankCard.setIdCard(idcard);
		userBankCardDao.save(userBankCard);
		return userBankCard;
	}

	/**
	 * 刪除綁定的銀行卡
	 * 
	 * @cardId 银行卡主键id
	 */
	@Transactional
	@Override
	public void removeBankCard(String userid, String cardId) throws Exception {
		UserBankCard card = userBankCardDao.get(cardId);
		if (card.getUserId().equals(userid)) {
			userBankCardDao.delete(cardId);
		} else {
			throw new Exception("用户无权删除此卡！");
		}
	}

	/**
	 * 通過userid得到用戶所有的銀行信息
	 */
	@Transactional
	@Override
	public List<UserBankCard> loadUserBankCards(String userId) {
		String sql = "select t.id from UserBankCard t where t.userId=" + userId;
		return userBankCardDao.findList(sql, null);
	}

}
