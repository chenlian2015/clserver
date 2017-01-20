package com.cnd.greencube.server.dao;

import java.util.List;

import com.cnd.greencube.server.entity.MemberNews;
import com.cnd.greencube.server.util.PageInfo;

public interface MemberNewsDao extends BaseDao<MemberNews, String> {
	/**
	 * 分页查询机构咨询和公共
	 * 
	 * @param insId
	 * @return
	 */
	public List<MemberNews> loadNewsForPagelit(String shopId, PageInfo pageInfo);
	
	/**
	 * 分页查询机构咨询和公共
	 * 
	 * @param insId
	 * @return
	 */
	public List<MemberNews> loadNoticeForPagelit(String shopId, PageInfo pageInfo);
}
