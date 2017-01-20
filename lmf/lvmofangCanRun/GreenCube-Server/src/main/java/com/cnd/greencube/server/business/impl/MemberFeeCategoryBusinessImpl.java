/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.business.MemberFeeCategoryBusiness;
import com.cnd.greencube.server.dao.FiMemberFeeCategoryDao;
import com.cnd.greencube.server.entity.FiMemberFeeCategory;
import com.cnd.greencube.server.util.StringUtils;

/**
 * cms服务实现
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("rawtypes")
@Service("MemberFeeCategoryBusinessImpl")
public class MemberFeeCategoryBusinessImpl extends BaseBusinessImpl implements MemberFeeCategoryBusiness {
	@Resource(name = "FiMemberFeeCategoryDaoImpl")
	protected FiMemberFeeCategoryDao memberFeeCategoryDao;

	/**
	 * 取得全部分类
	 */
	@Override
	public List<FiMemberFeeCategory> loadFeeCategory(String usertype) {
		String sql = "select t.id from FiMemberFeeCategory t";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(usertype)) {
			try {
				int type = Integer.decode(usertype);
				if (type > 0) {
					sql += " where t.suitUserType = ?";
					params.add(type);
				}
			} catch (Exception e) {
			}
		}
		return memberFeeCategoryDao.findList(sql, params.toArray());
	}

	/**
	 * 创建一个分类
	 */
	@Transactional
	@Override
	public void createFeeCategory(FiMemberFeeCategory feeCategory) {
		feeCategory.setId(null);
		memberFeeCategoryDao.save(feeCategory);
	}

	/**
	 * 更新费用分类
	 */
	@Transactional
	@Override
	public void updateFeeCategory(FiMemberFeeCategory feeCategory) {
		memberFeeCategoryDao.update(feeCategory);
	}

	/**
	 * 根据id获取一个费用分类
	 */
	@Override
	public FiMemberFeeCategory getFeeCategory(String feeCategoryId) {
		return memberFeeCategoryDao.get(feeCategoryId);
	}

	/**
	 * 删除一个分类
	 */
	@Transactional
	@Override
	public void deleteFeeCategory(String id) {
		memberFeeCategoryDao.delete(id);
	}

}
