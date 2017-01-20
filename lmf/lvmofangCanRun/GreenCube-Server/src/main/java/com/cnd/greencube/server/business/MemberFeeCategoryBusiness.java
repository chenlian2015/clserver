package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.FiMemberFeeCategory;

/**
 * 会员会费管理业务借口
 * 
 * @author huxg
 * 
 */
public interface MemberFeeCategoryBusiness {
	/**
	 * 返回所有会费分类
	 * 
	 * @param usertype
	 *            用户类别
	 * @return @
	 */
	public List<FiMemberFeeCategory> loadFeeCategory(String usertype);

	/**
	 * 创建一种费用类别
	 * 
	 * @param memberFeeJson
	 * @return
	 */
	public void createFeeCategory(FiMemberFeeCategory feeCategory);

	/**
	 * 更新费用分类
	 * 
	 * @param memberFeeJson
	 * @return
	 */
	public void updateFeeCategory(FiMemberFeeCategory feeCategory);

	/**
	 * 根据id取得会员费种类
	 * 
	 * @param feeCategoryId
	 */
	public FiMemberFeeCategory getFeeCategory(String feeCategoryId);

	/**
	 * 删除费用类别
	 * 
	 * @param id
	 * @return
	 */
	public void deleteFeeCategory(String id);
}
