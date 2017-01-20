package com.cnd.greencube.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.RequirementBusiness;
import com.cnd.greencube.server.business.UserBusiness;
import com.cnd.greencube.server.entity.Requirement;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.RequirementService;
import com.cnd.greencube.server.util.DateUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 机会空间，需求大厅
 * 
 * @author huxg
 * 
 */
public class RequirementServiceImpl extends AbstractServiceImpl implements RequirementService.Iface {
	private static final Logger logger = Logger.getLogger(RequirementServiceImpl.class);

	@Resource(name = "RequirementBusinessImpl")
	protected RequirementBusiness requirementBusiness;

	@Resource(name = "UserBusinessImpl")
	protected UserBusiness userBusiness;

	/**
	 * 删除需求
	 */
	@Override
	public String deleteRequirement(String arg0) throws TException {
		try {
			requirementBusiness.deleteRequirement(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getRequirement(String rid) throws TException {
		try {
			Requirement req = requirementBusiness.getRequirement(rid);
			return Message.okMessage(req);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadRequirement(int type, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Requirement> reqs = requirementBusiness.loadRequirement(type, pageInfo);

			List<RequirementItem> result = new ArrayList<RequirementItem>();

			if (reqs == null || reqs.isEmpty() || reqs.size() == 0) {
				return Message.okMessage(result, pageInfo);
			}

			// 按照App端的要求，对数据按照日期进行分类
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			Date today = new Date();
			Date yesterDay = DateUtils.getPrevDate(today);

			String keyToday = "今天";
			String keyTYesterday = "昨天";

			Map<String, RequirementItem> reqItems = new LinkedHashMap<String, RequirementItem>();
			for (Requirement req : reqs) {
				if (DateUtils.isSameDate(today, req.getPostTime())) {
					addRequirement(reqItems, keyToday, req);
				} else if (DateUtils.isSameDate(yesterDay, req.getPostTime())) {
					addRequirement(reqItems, keyTYesterday, req);
				} else {
					// 添加这一天
					addRequirement(reqItems, sf.format(req.getPostTime()), req);
				}
			}

			Set<String> set = reqItems.keySet();
			Iterator<String> iter = set.iterator();
			while (iter.hasNext()) {
				result.add(reqItems.get(iter.next()));
			}

			return Message.okMessage(result, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	void addRequirement(Map<String, RequirementItem> items, String key, Requirement req) {
		RequirementItem item = items.get(key);
		if (null == item) {
			item = new RequirementItem();
			item.setLabel(key);
			items.put(key, item);
		}
		item.addReq(req);
	}

	@Override
	public String readRequirement(String rid) throws TException {
		try {
			requirementBusiness.readRequirement(rid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String replyRequirement(String rid, String title, String content, String posterId, String posterIp) throws TException {
		try {
			Requirement req = requirementBusiness.replyRequirement(rid, title, content, posterId, posterIp);
			return Message.okMessage(req);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 提交一条需求
	 */
	@Override
	public String submitText(String title, String content, String posterId, String __remoteAddr, int requirementType) throws TException {
		try {
			if (userBusiness.getUserById(posterId) == null)
				return Message.errorMessage("无效的用户，请重新登录后再试！");

			Requirement req = requirementBusiness.submitText(title, content, posterId, __remoteAddr, requirementType);
			return Message.okMessage(req);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 提交一条需求
	 */
	@Override
	public String submitPhoto(String title, String content, String multipart, String posterId, String __remoteAddr, int requirementType) throws TException {
		try {
			if (userBusiness.getUserById(posterId) == null)
				return Message.errorMessage("无效的用户，请重新登录后再试！");
			Requirement req = requirementBusiness.submitPhoto(title, content, multipart, posterId, __remoteAddr, requirementType);
			return Message.okMessage(req);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 提交一条需求
	 */
	@Override
	public String submitVideo(String title, String content, String multipart, String posterId, String __remoteAddr, int requirementType) throws TException {
		try {
			if (userBusiness.getUserById(posterId) == null)
				return Message.errorMessage("无效的用户，请重新登录后再试！");

			Requirement req = requirementBusiness.submitVideo(title, content, multipart, posterId, __remoteAddr, requirementType);
			return Message.okMessage(req);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String supportRequirement(String rid) throws TException {
		try {
			requirementBusiness.supportRequirement(rid);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}
	@Override
	public String getRequirementCount(String categoryId) throws TException {
		try {
			int count = requirementBusiness.getRequirementCount(categoryId);
			return Message.okMessage(count);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadRequirementByCategoryId(String categoryId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Requirement> reqs = requirementBusiness.loadRequirementByCategoryId(categoryId, pageInfo);
			return Message.okMessage(reqs);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadReplies(String rid, int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Requirement> reqs = requirementBusiness.loadReplies(rid, pageInfo);
			return Message.okMessage(reqs, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	public class RequirementItem {
		String label;
		List<Requirement> reqs = new ArrayList<Requirement>();

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public List<Requirement> getReqs() {
			return reqs;
		}

		public void setReqs(List<Requirement> reqs) {
			this.reqs = reqs;
		}

		public void addReq(Requirement req) {
			this.reqs.add(req);
		}
	}

	
}
