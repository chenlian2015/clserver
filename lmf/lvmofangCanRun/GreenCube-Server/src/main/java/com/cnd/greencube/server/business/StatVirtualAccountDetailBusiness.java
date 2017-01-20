package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.StatVirtualAccountDetail;

/**
 * 用户账户统计接口
 * 
 * @author huxg
 * 
 */
public interface StatVirtualAccountDetailBusiness {
	/**
	 * 返回账户统计信息
	 */
	List<StatVirtualAccountDetail> loadUserStatDetail(String userid);
	
	void beginRefresh();
}
