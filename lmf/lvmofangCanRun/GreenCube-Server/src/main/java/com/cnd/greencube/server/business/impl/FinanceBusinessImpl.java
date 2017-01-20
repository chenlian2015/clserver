package com.cnd.greencube.server.business.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnd.greencube.server.api.JdbcAPI;
import com.cnd.greencube.server.business.FinanceBusiness;
import com.cnd.greencube.server.protocal.Message;

/**
 * 财务管理
 * 
 * @author dongyali 2016-03-07
 *
 */
@Service("FinanceBusinessImpl")
public class FinanceBusinessImpl implements FinanceBusiness {
	/**
	 * 统计指定时间内共产会会费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	@Override
	public String loadProviderMemberfiStat(String startTime, String endTime) {
		String sql = "select a.payTime,count(a.payTime) as count,sum(a.N_FEE) as fee from"
				+ " (SELECT date_format(D_PAY_TIME,'%Y-%m') AS payTime,date_format(D_END_TIME,'%Y-%m') AS endTime,N_FEE FROM t_fi_member_fee WHERE N_TYPE = 256) a"
				+ " where 1 = 1";

		if (!StringUtils.isEmpty(startTime)) {
			sql += " and a.payTime >= '" + startTime + "'";
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql += " and a.payTime <= '" + endTime + "'";
		}
		sql += " group by a.payTime";

		return Message.okMessage(JdbcAPI.getJdbc().queryExt(sql));
	}

	/**
	 * 统计指定时间内共产会管理费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	@Override
	public String loadProviderManagerfiStat(String startTime, String endTime) {
		String sql = "select a.payTime,count(a.payTime) as count,sum(a.N_FEE) as fee from"
				+ " (SELECT date_format(D_PAY_TIME,'%Y-%m') AS payTime,date_format(D_END_TIME,'%Y-%m') AS endTime,N_FEE FROM T_FI_MANAGER_FEE WHERE N_TYPE = 256)a"
				+ " where 1 = 1";
		if (!StringUtils.isEmpty(startTime)) {
			sql += " and a.payTime>='" + startTime + "'";
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql += " and a.payTime<='" + endTime + "'";
		}
		sql += " group by a.payTime";
		return Message.okMessage(JdbcAPI.getJdbc().queryExt(sql));
	}

	/**
	 * 统计指定时间内店主会会费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	@Override
	public String loadShopMemberfiStat(String startTime, String endTime) {
		String sql = "select a.payTime,count(a.payTime) as count,sum(a.N_FEE) as fee from"
				+ " (SELECT date_format(D_PAY_TIME,'%Y-%m') AS payTime,date_format(D_END_TIME,'%Y-%m') AS endTime,N_FEE FROM t_fi_member_fee WHERE N_TYPE = 4096)a"
				+ " where 1 = 1";
		if (!StringUtils.isEmpty(startTime)) {
			sql += " and a.payTime >= '" + startTime + "'";
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql += " and a.payTime <= '" + endTime + "'";
		}
		sql += " group by a.payTime";
		return Message.okMessage(JdbcAPI.getJdbc().queryExt(sql));
	}

	/**
	 * 统计指定时间内店主会管理费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	@Override
	public String loadShopManagerfiStat(String startTime, String endTime) {
		String sql = "select a.payTime,count(a.payTime) as count,sum(a.N_FEE) as fee from"
				+ " (SELECT date_format(D_PAY_TIME,'%Y-%m') AS payTime,date_format(D_END_TIME,'%Y-%m') AS endTime,N_FEE FROM T_FI_MANAGER_FEE WHERE N_TYPE = 4096)a"
				+ " where 1 = 1";
		if (!StringUtils.isEmpty(startTime)) {
			sql += " and a.payTime >= '" + startTime + "'";
		}
		if (!StringUtils.isEmpty(endTime)) {
			sql += " and a.payTime <= '" + endTime + "'";
		}
		sql += " group by a.payTime";
		return Message.okMessage(JdbcAPI.getJdbc().queryExt(sql));
	}

}
