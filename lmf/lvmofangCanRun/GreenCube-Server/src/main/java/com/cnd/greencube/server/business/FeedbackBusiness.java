package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Feedback;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 用户反馈服务实现类
 * 
 * @author huxg
 * 
 */
public interface FeedbackBusiness extends BaseBusiness<Feedback, String> {

	/**
	 * 取得意见反馈列表
	 */
	List<Feedback> loadFeedbacksForPagelit(PageInfo pageInfo);

	/**
	 * 根据id获取反馈信息
	 * 
	 * @param feedbackId
	 *            -- 反馈id
	 * @return 返回一个feedback json对象
	 */
	Feedback getFeedback(String feedbackId);

	/**
	 * 保存
	 * 
	 * @param adviceFd
	 */
	boolean submitFeedback(String userid, String title, String content, String phone, String qq);

	/**
	 * 删除反馈
	 */
	boolean deleteFeedback(String feedbackId);
}
