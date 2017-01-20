namespace java com.cnd.greencube.server.service

/**
 * 工作空间
 * @author 董亚莉
 */
service OpportunitySpaceService {
	/**
	 * 取得所有工作空间分类
	 * @return json数组
	 */
	string loadOpportunitySpaceCategorys(),
	/**
	 * 取得推荐的所有工作空间分类
	 * @return json数组
	 */
	string loadRecommendCategorys(),
	/**
	 * 根据id取得一个工作空间分类
	 * @param id -- 分类id 
	 * @return 分类json对象
	 */
	string getOpportunitySpaceCategory(1:string id),
	
	/**
	 * 创建一个分类
	 * @param parentId --父分类id
	 * @param categoryJson -- json对象
	 * @return 成功与否标志
	 */
	string createOpportunitySpaceCategory(1:string parentId, 2:string categoryJson),
	
	/**
	 * 更新修改分类
	 * @param categoryJson --  json对象
	 * @return 成功与否标志
	 */
	string updateOpportunitySpaceCategory(1:string categoryJson),
	
	/**
	 * 删除一个分类
	 * @param categoryId -- 分类id
	 * @return 成功与否标志
	 */
	string deleteOpportunitySpaceCategory(1:string categoryId)
}