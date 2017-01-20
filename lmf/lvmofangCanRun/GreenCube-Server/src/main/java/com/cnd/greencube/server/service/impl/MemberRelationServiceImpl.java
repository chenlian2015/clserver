package com.cnd.greencube.server.service.impl;

import javax.annotation.Resource;

import org.apache.thrift.TException;

import com.cnd.greencube.server.business.MemberRelationBusiness;
import com.cnd.greencube.server.business.impl.MemberRelationBusinessImpl;
import com.cnd.greencube.server.service.MemberRelationService; 
/**
 * 好友关系
 * 
 * @author chenlian
 * 
 * */
public class MemberRelationServiceImpl extends AbstractServiceImpl implements MemberRelationService.Iface{

	@Resource(name = "MemberRelationBusinessImpl")
	MemberRelationBusiness memberRelationBusiness;
	
	@Override
	public String registerUser(String username, String pwd) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	 public String addFriend(String hostUserId, String guestUserId, String relationType) throws TException {
		// TODO Auto-generated method stub
		return memberRelationBusiness.addFriend(hostUserId, guestUserId, relationType);
	}
}
