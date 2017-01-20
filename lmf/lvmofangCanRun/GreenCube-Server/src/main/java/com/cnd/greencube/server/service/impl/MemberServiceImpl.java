/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.MemberBusiness;
import com.cnd.greencube.server.business.NotifyException;
import com.cnd.greencube.server.entity.FiMemberFee;
import com.cnd.greencube.server.entity.Member;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.MemberService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 供产会会员服务实现类
 * 
 * @author huxg
 * 
 */
public class MemberServiceImpl extends AbstractServiceImpl implements MemberService.Iface {
	private static final Logger logger = Logger.getLogger(MemberServiceImpl.class);

	@Resource(name = "MemberBusinessImpl")
	MemberBusiness memberBusiness;

	@Override
	public String changeMemberValidStatus(String arg0, int arg1) throws TException {
		try {
			memberBusiness.changeMemberValidStatus(arg0, arg1);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getHistoryMemberFee(String arg0) throws TException {
		try {
			List<FiMemberFee> memberFees = memberBusiness.getHistoryMemberFee(arg0);
			return Message.okMessage(new String[] { "member_fee" }, new Object[] { memberFees });
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String getMember(String arg0) throws TException {
		try {
			Member m = memberBusiness.getMemberById(arg0);
			return Message.okMessage(m);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	@Override
	public String registMember(String userJson, String memberJson) throws TException {
		try {
			User user = JsonUtils.String2Bean(userJson, User.class);
			Member shop = JsonUtils.String2Bean(memberJson, Member.class);

			if (user == null)
				return Message.errorMessage("Error[001]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim(user.getId()))
				return Message.errorMessage("Error[002]提交失败，参数有误");
			if (StringUtils.isEmptyAfterTrim("nickname"))
				return Message.errorMessage("Error[003]提交失败，参数有误");
			if (shop == null)
				return Message.errorMessage("Error[004]提交失败，参数有误");

			memberBusiness.updateMember(user, shop);
			return Message.okMessage();
		} catch (NotifyException ne) {
			return Message.errorMessage(ne.getMessage());
		} catch (Exception e) {
			return Message.error();
		}
	}

	@Override
	public String updateMember(String userJson, String providerJson) throws TException {
		try {
			return registMember(userJson, providerJson);
		} catch (Exception e) {
			return Message.error();
		}
	}

	@Override
	public String loadMembers(int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Member> members = memberBusiness.loadApprovedMembersForPagelit(pageInfo);
			return Message.okMessage(members);
		} catch (Exception e) {
			return Message.error();
		}
	}

	@Override
	public String addMember(String memberJson) throws TException {
		Member member = JsonUtils.String2Bean(memberJson, Member.class);
		member = memberBusiness.addMember(member);
	   String id = member.getId();
		return id;
	}
}
