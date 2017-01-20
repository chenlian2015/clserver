package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.UserBankCard;

public interface UserBankCardBusiness {

	/**
	 * 显示银行卡信息（在business中根据userid进行查询显示）
	 * 
	 * @param userid
	 */
	public List<UserBankCard> loadUserBankCards(String userId);

	/**
	 * 显示银行卡信息（在business中根据userid进行查询显示）
	 * 
	 * @param userid
	 * @param cardId
	 */
	public int getBankCard(String userid, String cardId) ;

	/**
	 * 绑定一张银行卡
	 * 
	 * @param userid
	 * @param bankName
	 * @param holderName
	 * @param cardNum
	 * @param phone
	 */
	public UserBankCard bindBankCard(String userid, String holderName, String cardNum, String phone, String idcard);

	/**
	 * 解除绑定银行卡信息（在business中根据userid进行查询显示）
	 * 
	 * @param userid
	 * @param cardId
	 */
	public void removeBankCard(String userid, String cardNum) throws Exception;
}
