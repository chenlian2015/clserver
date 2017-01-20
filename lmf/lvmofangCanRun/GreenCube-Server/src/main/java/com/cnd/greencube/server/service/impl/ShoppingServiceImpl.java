/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cnd.greencube.server.business.ShoppingBusiness;
import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.ShoppingService;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.TokenUtils;

/**
 * 购物车服务实现类
 * 
 * @author huxg
 * 
 */
public class ShoppingServiceImpl extends AbstractServiceImpl implements ShoppingService.Iface {
	@Resource(name = "ShoppingBusinessImpl")
	protected ShoppingBusiness shoppingBusiness;

	/**
	 * 返回购物车列表
	 * 
	 * @param token
	 * @param pageNum
	 * @return
	 */
	@Override
	public String mycartlist(String token, int pageNum) {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			String userid = TokenUtils.isTokenRight(token);
			List<ShoppingItem> items = shoppingBusiness.mycartlist(userid, pageInfo);
			return Message.okMessage(items);
		} catch (Exception e) {
			return Message.error();
		}
	}
}
