package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MemberDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Member;

@Repository("MemberDaoImpl")
public class MemberDaoImpl extends RedisDaoSupportImpl<Member, String> implements MemberDao {

	@Override
	public Member getMember(String userId) {
		String sql = "select t.id from Member t where t.userId = ?";
		List<Member> m = super.findList(sql, new Object [] {userId});
		if (m != null && m.size() > 0) {
			return m.get(0);
		}
		return null;
	}

}
