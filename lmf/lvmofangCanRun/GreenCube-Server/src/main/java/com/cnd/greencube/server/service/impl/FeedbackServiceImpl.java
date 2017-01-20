package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.FeedbackBusiness;
import com.cnd.greencube.server.entity.Feedback;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.FeedbackService;
import com.cnd.greencube.server.util.PageInfo;
/**
 * 用户反馈服务实现类
 * 
 * @author huxg
 * 
 */
public class FeedbackServiceImpl extends AbstractServiceImpl implements FeedbackService.Iface {
	private static final Logger logger = Logger.getLogger(FeedbackServiceImpl.class);

	@Resource(name = "FeedbackBusinessImpl")
	protected FeedbackBusiness feedbackBusiness;

	/**
	 * 取得意见反馈列表
	 */
	@Override
	public String loadFeedbacksForPagelit(int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Feedback> feedbacks = feedbackBusiness.loadFeedbacksForPagelit(pageInfo);
			return Message.okMessage(feedbacks, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 根据id获取反馈信息
	 * 
	 * @param feedbackId
	 *            -- 反馈id
	 * @return 返回一个feedback json对象
	 */
	@Override
	public String getFeedbackById(String feedbackId) throws TException {
		try {
			Feedback f = feedbackBusiness.getFeedback(feedbackId);
			return Message.okMessage(f);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存
	 * 
	 * @param adviceFd
	 */
	@Override
	public String submitFeedback(String userid, String title, String content, String phone, String qq) {
		try {
			feedbackBusiness.submitFeedback(userid, title, content, phone, qq);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除反馈
	 */
	@Override
	public String deleteFeedback(String feedbackId) throws TException {
		try {
			feedbackBusiness.deleteFeedback(feedbackId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
