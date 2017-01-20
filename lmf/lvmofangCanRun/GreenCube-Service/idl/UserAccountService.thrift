namespace java com.cnd.greencube.server.service

/**
 * 用户账户收支、统计接口
 */
service UserAccountService {
	/*
	 * 获取用户钱包金额信息
	 * @param id -- 地区id
	 */
	string getUserVirtualAccount(1:string userid)

	/**
	 * 返回账户收入明细
	 * @param userid -- 用户id
	 */
	string loadUserIncomeDetail(1:string userid, 2:i32 pageNum),
		
	
	/**
	 *
	 *返回账户支出明细
	 * @param userid -- 用户id
	 */
	string loadUserPaymentDetail(1:string userid, 2:i32 pageNum),
	
	/**
	 * 更新上次登录时间
	 * @param userid -- 用户id
	 */
	string setAccountPassword(1:string token, 2:string password),
	
	/**
	 * 检查用户虚拟账户是否安全
	 * @param userid -- 用户id
	 */
	string checkAccountSecurity(1:string token),
	
	/**
	 * 更新上次登录时间
	 * @param userid -- 用户id
	 */
	string updateAccountPassword(1:string token, 2:string newpwd),
	
	/**
	 * 检查密码是否正确
	 */
	string checkPassword(1:string token, 2:string pwd),
	
	/**
	 * 返回账户统计信息
	 * @param userid -- 用户id
	 */
	string loadUserStatDetail(1:string userid)
	
}