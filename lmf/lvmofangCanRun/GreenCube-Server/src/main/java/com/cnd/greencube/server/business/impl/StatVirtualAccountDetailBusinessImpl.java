package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.StatVirtualAccountDetailBusiness;
import com.cnd.greencube.server.dao.StatVirtualAccountDetailDao;
import com.cnd.greencube.server.entity.StatVirtualAccountDetail;
import com.cnd.greencube.server.util.DateUtils;

/**
 * 虚拟账户统计数据访问
 * 
 * @author cndini
 * 
 */
@SuppressWarnings("rawtypes")
@Service("StatVirtualAccountDetailBusinessImpl")
public class StatVirtualAccountDetailBusinessImpl extends BaseBusinessImpl implements StatVirtualAccountDetailBusiness {
	@Resource(name = "StatVirtualAccountDetailDaoImpl")
	StatVirtualAccountDetailDao statVirtualAccountDetailDao;

	/**
	 * 返回用户账户统计明细
	 */
	@Transactional
	@Override
	public List<StatVirtualAccountDetail> loadUserStatDetail(String userid) {
		int year = DateUtils.getYear();
		int begin = Integer.parseInt(year + "01");
		int end = Integer.parseInt(year + "12");

		// 这里统计12个月的记录
		String sql = "select t.id from StatVirtualAccountDetail t where t.userId ＝ ? and t.monthy > " + begin + " and t.monthy < " + end
				+ " order by t.monthy desc ";
		return statVirtualAccountDetailDao.findList(sql);
	}
	
	/**
	 * 刷新用户统计数据信息
	 */
	@Override
	public void beginRefresh() {
		String sql = "select from ";
	}
}
