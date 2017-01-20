package com.cnd.greencube.server.service.impl;

import com.cnd.greencube.server.util.PageInfo;

public abstract class AbstractServiceImpl {
	public PageInfo initPageInfo(int pageNum){
		int pageIndex = pageNum <= 0 ? 1 : pageNum;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPageIndex(pageIndex);
		pageInfo.setPageSize(15);
		return pageInfo;
	}
}
