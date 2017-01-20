package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.Member;

public interface MemberDao extends BaseDao<Member, String> {
	public Member getMember(String userId);
}
