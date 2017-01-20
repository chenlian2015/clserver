namespace java com.cnd.greencube.server.service

/**
 * 用户反馈服务
 * @author
 */
service FeedbackService {
	/**
	 * 根据id获取反馈信息
	 * @param feedbackId -- 反馈id
	 * @return 返回一个feedback json对象
	 */
	string getFeedbackById(1:string feedbackId),
	
	/**
	 * 更新反馈信息
	 * @param feedbackJon -- 反馈json对象
	 * @return 成功与否标志
	 */
	string loadFeedbacksForPagelit(1:i32 pageNum),
	
	/**
	 * 提交反馈意见
	 * @param userid -- 提交者id
	 * @param title -- 反馈标题
	 * @param content -- 反馈内容
	 * @param phone -- 反馈者电话
	 * @param qq -- 反馈者qq号码
	 * @return -- 返回成功标志
	 */
	string submitFeedback(1:string userid, 2:string title, 3:string content, 4:string phone, 5:string qq),
	
	/**
	 * 删除用户反馈
	 * @param feedbackId
	 * @return 成功与否标志
	 */
	string deleteFeedback(1:string feedbackId)
}