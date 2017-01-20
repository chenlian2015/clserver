package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.FiWithdraw;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 提现业务接口
 * 
 * @author 李飞飞
 * 
 */
public interface WithdrawBusiness {
	public FiWithdraw applyWithdraw(String userid, String password, String bankcardId, int amount) throws Exception;

	/**
	 * 获取提现申请返回的信息
	 */
	public List<FiWithdraw> loadWithdraw(PageInfo pageInfo);

	/**
	 * 驳回提现申请
	 * 
	 * @param id
	 */
	public boolean rejectWithdraw(String id);

	/**
	 * 通过提现申请
	 * 
	 * @param id
	 * @param status
	 */
	public boolean approveWithdraw(String id, String status);

	/**
	 * 返回在几个小时之内的转账申请
	 */
	List<FiWithdraw> loadWithdrawApplyBeforeHours(int hour, PageInfo pageInfo);

}
