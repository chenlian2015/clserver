package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.FinanceBusiness;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.FinanceService;

/**
 * finance服务实现
 * 
 * @author dongyali
 * 
 */
public class FinanceServiceImpl extends AbstractServiceImpl implements FinanceService.Iface {
	private static final Logger logger = Logger.getLogger(FinanceServiceImpl.class);
	@Resource(name = "FinanceBusinessImpl")
	protected FinanceBusiness FinanceBusiness;

	/**
	 * jdbcDAO 共产会会费统计
	 */
	@Override
	public String loadProviderMemberfiStat(String startTime, String endTime) throws TException {
		try {
			String finance = FinanceBusiness.loadProviderMemberfiStat(startTime, endTime);
			return finance;
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 共产会管理费统计
	 */
	@Override
	public String loadProviderManagerfiStat(String startTime, String endTime) throws TException {
		try {
			String finance = FinanceBusiness.loadProviderManagerfiStat(startTime, endTime);
			return finance;
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 店主会会费统计
	 */
	@Override
	public String loadShopMemberfiStat(String startTime, String endTime) throws TException {
		try {
			String finance = FinanceBusiness.loadShopMemberfiStat(startTime, endTime);
			return finance;
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 店主会管理费统计
	 */
	@Override
	public String loadShopManagerfiStat(String startTime, String endTime) throws TException {
		try {
			String finance = FinanceBusiness.loadShopManagerfiStat(startTime, endTime);
			return finance;
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
