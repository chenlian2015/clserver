namespace java com.cnd.greencube.server.service


/**
 * 健康会会员服务类
 * @author 胡晓光
 */
service HealthyMemberService {
/**
	 * 依据id得到健康会明细
	 * @param healthyId -- 健康会会员id
	 */
	string getHealthyById(1:string healthyId),
	/**
	 * 提交申请(更新健康会信息)
	 * @param applyJson -- 健康会会员信息申请json
	 */
	string submitApply(1:string userJson, 2:string memberJson, 3:string detailJson),
	/**
	 * 修改健康会会员信息
	 * @param applyJson -- 健康会会员信息json
	 */
	string updateHealthy(1:string userJson, 2:string memberJson, 3:string detailJson),
	/**
	 * 审核通过健康会会员申请
	 * @param healthyUserId -- 被审核的健康会会员id
	 * @param auditUserId -- 审核者id
	 * @param auditStatus -- 审核状态，1-审核通过， 0-审核不通过
	 */
	string auditHealthyMemberApply(1:string healthyUserId,  2:string auditUserId, 3:i32 auditStatus),
		/**
	 * 审核驳回健康会会员申请
	 * @param healthyUserId -- 被审核的健康会会员id
	 * @param auditUserId -- 审核者id
	 * @param auditStatus -- 审核状态，1-审核通过， 0-审核不通过
	 */
	string rejectHealthyMemberApply(1:string healthyUserId,  2:string auditUserId, 3:i32 auditStatus),
	
	/**
	 * 返回申请的健康会会员数组
	 * @param pageNum -- 页数
	 */
	string loadAppliedHealthyMembersForPagelit(1:i32 pageNum),
	
	/**
	 * 返回已审核过的健康会会员数组
	 * @param pageNum -- 页数
	 */
	string loadApprovedHealthyMembersForPagelit(1:i32 pageNum),
	
	/**
	 * 向健康会会员发送站内信息
	 * @param healthyUserId -- 健康会会员id
	 * @param title -- 发送的信息的title
	 * @param content -- 发送的内容
	 */
	string sendInnerLetter(1:string healthyUserId, 2:string title, 3:string content),
	/**
	 * 得到健康会成员详情
	 * @param healthyId 健康会id
	 */
	 string getHealthyDetail(1:string healthyId)
	
}