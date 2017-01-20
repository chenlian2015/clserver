package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.MemberNewsDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.MemberNews;
import com.cnd.greencube.server.util.PageInfo;

@Repository("MemberNewsDaoImpl")
public class MemberNewsDaoImpl extends RedisDaoSupportImpl<MemberNews, String> implements MemberNewsDao {
	/**
	 * 分页查询机构咨询和公共
	 * 
	 * @param insId
	 * @return
	 */
	public List<MemberNews> loadNewsForPagelit(String shopId, PageInfo pageInfo) {
		String sql = "select t.id from MemberNews t where t.sellerId = ? and type = 1";
		String ctql = "select count(t) from MemberNews t where t.sellerId = ? and type = 1";
		sql += " order by isTop desc , order asc , createTime desc";
		return (List<MemberNews>) findList(sql, ctql, new Object[] { shopId }, pageInfo);
	}
	
	/**
	 * 分页查询机构咨询和公共
	 * 
	 * @param insId
	 * @return
	 */
	public List<MemberNews> loadNoticeForPagelit(String shopId, PageInfo pageInfo) {
		String sql = "select t.id from MemberNews t where t.sellerId = ? and type = 2";
		String ctql = "select count(t) from MemberNews t where t.sellerId = ? and type = 2";
		sql += " order by isTop desc , order asc , createTime desc";
		return (List<MemberNews>) findList(sql, ctql, new Object[] { shopId }, pageInfo);
	}
}
