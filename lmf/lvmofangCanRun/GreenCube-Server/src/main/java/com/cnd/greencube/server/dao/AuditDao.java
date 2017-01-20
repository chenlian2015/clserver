package com.cnd.greencube.server.dao;

import com.cnd.greencube.server.entity.Audit;

/**
 * 审核数据访问类
 * 
 * @author huxg
 * 
 */
public interface AuditDao extends BaseDao<Audit, String> {
	/**
	 * 执行审核
	 * 
	 * @param foreignId
	 * @param auditObject
	 * @param auditorId
	 * @param auditStatus
	 */
	public void audit(String foreignId, Class auditObject, String auditorId, int auditStatus);
}
