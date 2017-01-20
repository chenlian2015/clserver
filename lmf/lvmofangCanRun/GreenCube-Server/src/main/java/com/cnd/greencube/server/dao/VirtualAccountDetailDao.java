package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.VirtualAccountDetail;

/**
 * 虚拟账户详细信息数据访问
 * @author huxg
 *
 */
public interface VirtualAccountDetailDao extends BaseDao<VirtualAccountDetail, String>{
	VirtualAccountDetail getUserVirtualAccountDetail(String userid);
}
