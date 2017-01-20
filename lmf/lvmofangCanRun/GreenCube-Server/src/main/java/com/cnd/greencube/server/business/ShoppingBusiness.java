/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.ShoppingItem;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 购物车服务实现类
 * 
 * @author huxg
 * 
 */
public interface ShoppingBusiness {
	List<ShoppingItem> mycartlist(String token, PageInfo pageInfo);
}
