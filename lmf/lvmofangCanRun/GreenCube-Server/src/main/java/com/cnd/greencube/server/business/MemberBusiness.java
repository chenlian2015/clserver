package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.FiMemberFee;
import com.cnd.greencube.server.entity.Member;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

public interface MemberBusiness {
	/**
	 * 获取一个店铺历史会员费缴纳列表
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	List<FiMemberFee> getHistoryMemberFee(String userid);

	/**
	 * 根据id获得店铺
	 * 
	 * @param id
	 *            -- 店铺id
	 * @return 店铺的json对象
	 * 
	 * @param id
	 */
	Member getMemberById(String id);

	/**
	 * 更新店铺信息
	 * 
	 * @param shopJson
	 *            -- 店铺json
	 * @return 成功与否标志
	 * 
	 * @param shopJson
	 */
	boolean updateMember(User user, Member member);

	/**
	 * 删除店铺信息
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标志
	 * 
	 * @param shopId
	 */
	boolean deleteMember(String memberId);

	/**
	 * 分页获取待审核店铺
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 * 
	 * @param pageNum
	 */
	List<Member> loadAppliedMembersForPagelit(PageInfo pageInfo);

	/**
	 * 分页获取已审核通过的店铺列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺json数组
	 * 
	 * @param pageNum
	 */
	List<Member> loadApprovedMembersForPagelit(PageInfo pageInfo);
//
//	/**
//	 * 审核通过一个店铺
//	 * 
//	 * @param shopId
//	 *            -- 店铺id
//	 * @param auditUserId
//	 *            -- 审核人id
//	 * @param auditUserName
//	 *            -- 审核人姓名
//	 * @return 成功与否标识
//	 * 
//	 * @param shopId
//	 * @param auditUserId
//	 * @param auditUserName
//	 */
//	boolean approveMember(String memberId, String auditUserId, String auditUserName);
//
//	/**
//	 * 驳回申请
//	 * 
//	 * @param shopId
//	 *            -- 店铺id
//	 * @param auditUserId
//	 *            -- 审核人id
//	 * @param auditUserName
//	 *            -- 审核人姓名
//	 * @return 成功与否标识
//	 * 
//	 * @param shopId
//	 * @param auditUserId
//	 * @param auditUserName
//	 */
//	boolean rejectMember(String memberId, String auditUserId, String auditUserName);


	/**
	 * 更改店铺有效标志参数
	 * 
	 * @param shopId
	 *            -- 店铺id
	 * @return 成功与否标识
	 * 
	 * @param shopId
	 * @param validStatus
	 */
	boolean changeMemberValidStatus(String userId, int validStatus);
	/**
	 * 新增会员
	 * @param member
	 * @return
	 */
	Member addMember(Member member);
}
