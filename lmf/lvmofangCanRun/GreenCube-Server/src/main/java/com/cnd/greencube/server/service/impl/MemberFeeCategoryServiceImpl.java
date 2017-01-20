package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.MemberFeeCategoryBusiness;
import com.cnd.greencube.server.entity.FiMemberFeeCategory;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.MemberFeeCategoryService;
import com.cnd.greencube.server.util.JsonUtils;

/**
 * 会员费用类别管理
 * 
 * @author huxg
 * 
 */
public class MemberFeeCategoryServiceImpl extends AbstractServiceImpl implements MemberFeeCategoryService.Iface {
	private static final Logger logger = Logger.getLogger(MemberFeeCategoryServiceImpl.class);

	@Resource(name = "MemberFeeCategoryBusinessImpl")
	protected MemberFeeCategoryBusiness memberFeeCategoryBusiness;

	@Override
	public String createFeeCategory(String arg0) throws TException {
		try {
			FiMemberFeeCategory category = JsonUtils.String2Bean(arg0, FiMemberFeeCategory.class);
			memberFeeCategoryBusiness.createFeeCategory(category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String deleteFeeCategory(String arg0) throws TException {
		try {
			memberFeeCategoryBusiness.deleteFeeCategory(arg0);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getFeeCategory(String arg0) throws TException {
		try {
			return Message.okMessage(memberFeeCategoryBusiness.getFeeCategory(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String loadFeeCategory(String arg0) throws TException {
		try {
			return Message.okMessage(memberFeeCategoryBusiness.loadFeeCategory(arg0));
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String updateFeeCategory(String arg0) throws TException {
		try {
			FiMemberFeeCategory category = JsonUtils.String2Bean(arg0, FiMemberFeeCategory.class);
			memberFeeCategoryBusiness.updateFeeCategory(category);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
