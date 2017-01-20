package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.StatVirtualAccountDetailBusiness;
import com.cnd.greencube.server.business.UserAccountBusiness;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.UserAccountService;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 用户账户明细务实现类
 * 
 * @author huxg
 * 
 */

public class UserAccountServiceImpl extends AbstractServiceImpl implements UserAccountService.Iface {
	private static final Logger logger = Logger.getLogger(VideoServiceImpl.class);

	@Resource(name = "StatVirtualAccountDetailBusinessImpl")
	protected StatVirtualAccountDetailBusiness statVirtualAccountDetailBusiness;

	@Resource(name = "UserAccountBusinessImpl")
	protected UserAccountBusiness userAccountBusiness;

	/**
	 * 设置用户账户密码
	 */
	@Override
	public String setAccountPassword(String token, String password) throws TException {
		try {
			if (StringUtils.isEmpty(token))
				return Message.errorMessage("参数有误，提交失败！");
			if (StringUtils.isEmpty(password))
				return Message.errorMessage("密码不能为空，请重新输入！");
			
			String userid = TokenUtils.isTokenRight(token);
			
			userAccountBusiness.setAccountPassword(userid, password);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 更新用户账号密码
	 */
	@Override
	public String updateAccountPassword(String token, String newpwd) throws TException {
		try {
			if (StringUtils.isEmpty(token))
				return Message.errorMessage("参数有误，提交失败！");
			if (StringUtils.isEmpty(newpwd))
				return Message.errorMessage("新密码不能为空，请重新输入！");
			
			String userid = TokenUtils.isTokenRight(token);
			
			userAccountBusiness.updateAccountPassword(userid, newpwd);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.errorMessage(e.getMessage());
		}
	}

	/***
	 * 返回用户当前余额、总收入、总支出
	 * 
	 * @param userid
	 * @return
	 * @throws org.apache.thrift.TException
	 */
	@Override
	public String getUserVirtualAccount(String userid) {
		try {
			VirtualAccount virtualAccount = userAccountBusiness.getUserAccount(userid);
			return Message.okMessage(virtualAccount);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}

	}

	/**
	 * 返回账户收入明细
	 */
	@Override
	public String loadUserIncomeDetail(String userid, int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			return Message.okMessage(userAccountBusiness.loadUserIncomeDetail(userid, pageInfo));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 返回账户支出明细
	 */
	@Override
	public String loadUserPaymentDetail(String userid, int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			return Message.okMessage(userAccountBusiness.loadUserPaymentDetail(userid, pageInfo));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 返回账户统计明细
	 */
	@Override
	public String loadUserStatDetail(String userid) {
		try {
			return Message.okMessage(statVirtualAccountDetailBusiness.loadUserStatDetail(userid));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String checkAccountSecurity(String token) throws TException {
		try {
			String userid = TokenUtils.isTokenRight(token);
			
			// 此处判断用户虚拟账户密码是否正确
			VirtualAccount account = userAccountBusiness.getUserAccount(userid);
			if (account.getAccountPassword() == null) {
				// 未设置账户取款密码，需跳转到设置密码界面
				return Message.errorMessage(new String[] { "errno" }, new String[] { "-1" });
			}
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String checkPassword(String token, String pwd) throws TException {
		try {
			String userid = TokenUtils.isTokenRight(token);

			boolean right = userAccountBusiness.checkPassword(userid, pwd);
			return right ? Message.okMessage() : Message.error();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
