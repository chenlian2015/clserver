package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.entity.VirtualAccountDetail;
import com.cnd.greencube.server.util.PageInfo;

public interface UserAccountBusiness {
//	/***
//	 * 返回用户当前余额、总收入、总支出
//	 * 
//	 * @param userid
//	 * @return
//	 * @throws org.apache.thrift.TException
//	 */
//	List<VirtualAccount> getUserVirtualAccount(String uderid);

	/**
	 * 取得用户账户
	 * 
	 * @param userid
	 * @return
	 */
	VirtualAccount getUserAccount(String userid);

	/**
	 * 创建用户账户
	 * 
	 * @return
	 */
	VirtualAccount createUserAccountForUser(String userid);

	/**
	 * 设置账户密码
	 * 
	 * @param userid
	 * @param password
	 */
	void setAccountPassword(String userid, String password);

	/**
	 * 更新账户密码
	 * 
	 * @param userid
	 * @param oldpwd
	 * @param newpwd
	 */
	void updateAccountPassword(String userid, String newpwd) throws Exception;

	/**
	 * 返回账户收入明细
	 */
	public List<VirtualAccountDetail> loadUserIncomeDetail(String arg0, PageInfo pageInfo);

	/**
	 * 返回账户支出明细
	 */
	public List<VirtualAccountDetail> loadUserPaymentDetail(String arg0, PageInfo pageInfo);
	
	/**
	 * 检查用户账户密码
	 * @param userid
	 * @param pwd
	 * @return
	 */
	boolean checkPassword(String userid, String pwd);
}
