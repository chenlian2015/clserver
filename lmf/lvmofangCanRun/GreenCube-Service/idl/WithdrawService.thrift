namespace java com.cnd.greencube.server.service

/**
 * 地区接口
 */
service WithdrawService {
	/**
	 * 发起提现申请
	 * @param userid -- 用户id
	 */
	string applyWithdraw(1:string userid,  2:string password, 3:string bankcardId, 4:i32 amount),
	
	
	/**
	 *
	 * 返回在几个小时之内的转账申请
	 *@param
	 */
	string loadWithdrawApplyBeforeHours(1:i32 hour, 2:i32 pageNum),
	
	/**
	 *
	 *返回提现申请信息
	 *@param
	 */
	string loadWithdraw(1:i32 pageNum),
	
	
	/**
	 * 驳回提现申请
	 */
	string rejectWithdraw(1:string id),
	
	
	/**
	 * 通过提现申请
	 */
	string approveWithdraw(1:string id, 2:string status)
}