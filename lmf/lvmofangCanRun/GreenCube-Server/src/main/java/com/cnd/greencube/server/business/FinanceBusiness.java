package com.cnd.greencube.server.business;

/**
 * 财务管理
 * 
 * @author dongyali 2016-03-07
 *
 */
public interface FinanceBusiness {
	/**
	 * 统计指定时间内共产会会费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	String loadProviderMemberfiStat(String startTime, String endTime);

	/**
	 * 统计指定时间内共产会管理费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	String loadProviderManagerfiStat(String startTime, String endTime);

	/**
	 * 统计指定时间内店主会会费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	String loadShopMemberfiStat(String startTime, String endTime);

	/**
	 * 统计指定时间内店主会管理费缴纳情况（按月份）
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 统计情况的json串
	 */
	String loadShopManagerfiStat(String startTime, String endTime);
}
