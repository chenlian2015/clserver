namespace java com.cnd.greencube.server.service

/**
 * 用户银行卡服务类
 */
service UserBankCardService {

	/**
	 *显示银行卡信息（在business中根据userid进行查询显示）
	 */
	string loadUserBankCards(1:string token),
	
	
	/**
	 *显示银行卡信息（在business中根据userid进行查询显示）
	 */
	string getBankCard(1:string token, 2:string cardId),
	
	
	/**
	 * 绑定一张银行卡
	 */
	string bindBankCard(1:string token, 2:string holderName, 3:string cardNum, 4:string phone, 5:string idcard ),
	
	
	/**
	 * 解除绑定银行卡信息（在business中根据userid进行查询显示）
	 */
	string removeBankCard(1:string token, 2:string cardId)
}