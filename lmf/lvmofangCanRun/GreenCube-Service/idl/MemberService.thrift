namespace java com.cnd.greencube.server.service

/**
 * 供应商服务
 * @author 胡晓光
 */
service MemberService {
	/**
	 * 取得历史缴费记录列表
	 */
	string getHistoryMemberFee(1:string userid),
	
	/**
	 * 根据id获得店铺
	 * 
	 * @param id -- 店铺id
	 * @return 店铺的json对象
	 */
	string getMember(1:string userid),
	
	/**
	 * 更新店铺信息
	 * 
	 * @param memberJson -- 店铺json
	 * @return 成功与否标志
	 */
	string registMember(1:string userJson,2:string detailJson),
	
	/**
	 * 更新店铺信息
	 * @param shopJsonObject
	 * @return 成功与否标志
	 */
	string updateMember(1:string userJson,2:string detailJson),
	
	/**
	 * 更新店铺信息
	 * @param shopJsonObject
	 * @return 成功与否标志
	 */
	string loadMembers(1:i32 pageNum),
	
	/**
	 * 更改店铺有效标志参数
	 * @param shopId -- 店铺id
	 * @return 成功与否标识
	 */
	string changeMemberValidStatus(1:string memberId, 2:i32 validStatus),
	/**
	 * 新增会员
	 * @param memberJson 会员实体类
	 * @return 会员id
	 */
	string addMember(1:string memberJson)
}