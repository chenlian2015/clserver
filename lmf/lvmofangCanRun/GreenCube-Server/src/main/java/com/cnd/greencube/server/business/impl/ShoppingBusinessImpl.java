/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.business.ShoppingBusiness;
import com.cnd.greencube.server.dao.ShoppingItemDao;
import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 购物车服务实现类
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("ShoppingBusinessImpl")
public class ShoppingBusinessImpl extends BaseBusinessImpl implements ShoppingBusiness {
	@Resource(name = "ShoppingItemDaoImpl")
	protected ShoppingItemDao shoppingItemDao;

	/**
	 * 返回购物车列表
	 */
	@Override
	public List<ShoppingItem> mycartlist(String userid, PageInfo pageInfo) {
		String sql = "select t.id from ShoppingItem t where t.userId = ?";
		String ctql = "select count(t) from ShoppingItem t where t.userId = ?";
		return shoppingItemDao.findList(sql, ctql, new Object[] { userid }, pageInfo);
	}

}
