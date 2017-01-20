package com.cnd.greencube.server.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Audit;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.SpringUtils;

/**
 * 审核实现类
 * 
 * @author huxg
 * 
 */
@Repository("AuditDaoImpl")
public class AuditDaoImpl extends RedisDaoSupportImpl<Audit, String> implements AuditDao {

	/**
	 * 记录审核
	 * @param foreignId 被审核者id
	 * @param auditObject 被审核者实体类
	 * @param auditorId 审核者id
	 * @param auditStatus 审核结果
	 * 注释 dongyali 加 2016-2-18
	 */
	@Transactional
	@Override
	public void audit(String foreignId, Class auditObject, String auditorId, int auditStatus) {
		Audit audit = new Audit();
		audit.setForeignId(foreignId);
		audit.setForeignObject(auditObject.getName());
		audit.setAuditorId(auditorId);
		audit.setAuditResult(auditStatus);
		audit.setAuditTime(new Date());
		try {
			UserDao userdao = (UserDao) SpringUtils.getBean("UserDao");
			User user = userdao.get(auditorId);
			audit.setAuditorId(user.getName());
		} catch (Exception e) {
		}
		super.save(audit);
	}

}
