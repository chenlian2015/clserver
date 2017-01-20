namespace java com.cnd.greencube.server.service

/**
 * 内容服务接口<br/>
 * <b>用于前端访问提供的各种接口服务</b>
 * @author 董亚莉
 */
service FinanceService {
	/**
	 * 统计指定时间内共产会会费缴纳情况（按月份）
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 统计情况的json串
	 */
	string loadProviderMemberfiStat(1:string startTime,2:string endTime),
	/**
	 * 统计指定时间内共产会管理费缴纳情况（按月份）
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 统计情况的json串
	 */
	string loadProviderManagerfiStat(1:string startTime,2:string endTime),
	/**
	 * 统计指定时间内店主会会费缴纳情况（按月份）
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 统计情况的json串
	 */
	string loadShopMemberfiStat(1:string startTime,2:string endTime),
	/**
	 * 统计指定时间内店主会管理费缴纳情况（按月份）
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 统计情况的json串
	 */
	string loadShopManagerfiStat(1:string startTime,2:string endTime)
	
	
}