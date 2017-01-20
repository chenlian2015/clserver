namespace java com.cnd.greencube.server.service

/**
 * 费用管理
 */
service MemberFeeCategoryService {
	/*
	 * 获取所有的会员费分类
	 * @param usertype -- 用户分类，可选类型包括：0x0100,0x1000，0x0010 分别对应共产会、店主会、健康会，可以为空，为空时返回全部记录
	 */
	string loadFeeCategory(1:string usertype),
	
	/*
	 * 创建一种会员费
	 */
	string createFeeCategory(1:string memberFeeJson),
	
	/*
	 * 更新会员费
	 */
	string updateFeeCategory(1:string memberFeeJson),
	
	/**
	 * 根据id取得会员费种类
	 */
	string getFeeCategory(1:string feeCategoryId),
	
	/*
	 * 删除一种会员费
	 */
	string deleteFeeCategory(1:string id)
}