package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cnd.greencube.server.business.UserBankCardBusiness;
import com.cnd.greencube.server.entity.UserBankCard;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.UserBankCardService;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 用户银行卡实现服务类
 * 
 * @author 李飞飞
 */
public class UserBankCardServiceImpl extends AbstractServiceImpl implements UserBankCardService.Iface {
	private static final Logger logger = Logger.getLogger(WithdrawServiceImpl.class);

	@Resource(name = "UserBankCardBusinessImpl")
	protected UserBankCardBusiness userBankCardBusiness;

	/**
	 * 绑定银行卡信息
	 */
	@Override
	public String bindBankCard(String token, String holderName, String cardNum, String phone, String idcard) {
		try {
			String uid = TokenUtils.isTokenRight(token);
			UserBankCard userBankCard = userBankCardBusiness.bindBankCard(uid, holderName, cardNum, phone, idcard);
			return Message.okMessage(userBankCard);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 获取一条银行卡信息
	 */
	@Override
	public String getBankCard(String token, String cardId) {
		try {
			String uid = TokenUtils.isTokenRight(token);
			int userBankCard = userBankCardBusiness.getBankCard(uid, cardId);
			return Message.okMessage(userBankCard);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 解除绑定的银行卡信息
	 */
	@Override
	public String removeBankCard(String token, String cardId) {
		try {
			String uid = TokenUtils.isTokenRight(token);

			userBankCardBusiness.removeBankCard(uid, cardId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.errorMessage(e.getMessage());
		}
	}

	/**
	 * 获取多条银行卡信息
	 */
	@Override
	public String loadUserBankCards(String token) {
		try {
			String uid = TokenUtils.isTokenRight(token);
			List<UserBankCard> userBankCard = userBankCardBusiness.loadUserBankCards(uid);
			return Message.okMessage(userBankCard);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
