package com.cnd.greencube.server.business.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.FeedbackBusiness;
import com.cnd.greencube.server.dao.FeedbackDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Feedback;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 用户反馈服务实现类
 * 
 * @author huxg
 * 
 */
@Service("FeedbackBusinessImpl")
public class FeedbackBusinessImpl extends BaseBusinessImpl<Feedback, String> implements FeedbackBusiness {
	@Resource(name = "FeedbackDaoImpl")
	private FeedbackDao feedbackDao;

	@Resource(name = "UserDaoImpl")
	protected UserDao userDao;

	@Resource(name = "FeedbackDaoImpl")
	public void setDao(FeedbackDao feedbackDao) {
		super.setDao(feedbackDao);
	}

	/**
	 * 取得意见反馈列表
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Feedback> loadFeedbacksForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Feedback t order by publishTime desc";
		String ctql = "select count(t) from Feedback t ";
		return super.findList(sql, ctql, new Object[] {}, pageInfo);
	}

	/**
	 * 根据id获取反馈信息
	 * 
	 * @param feedbackId
	 *            -- 反馈id
	 * @return 返回一个feedback json对象
	 */
	@Transactional(readOnly = true)
	@Override
	public Feedback getFeedback(String feedbackId) {
		return (Feedback) feedbackDao.get(feedbackId);
	}

	/**
	 * 保存
	 * 
	 * @param adviceFd
	 */
	@Override
	@Transactional
	public boolean submitFeedback(String userid, String title, String content, String phone, String qq) {
		Feedback f = new Feedback();
		f.setUserId(userid);
		f.setTitle(title);
		f.setContent(content);
		f.setPhone(phone);
		f.setQq(qq);
		f.setPublishTime(new Date());

		User user = userDao.get(userid);
		if (user != null) {
			f.setUserName(user.getName());
		}
		feedbackDao.save(f);
		return true;
	}

	/**
	 * 删除反馈
	 */
	@Override
	@Transactional
	public boolean deleteFeedback(String feedbackId) {
		feedbackDao.delete(feedbackId);
		return true;
	}

}
