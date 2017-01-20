namespace java com.cnd.greencube.server.service

/**
 * 业务分类代码
 * @author 胡晓光
 */
service HealthyCategoryService {
	/**
	 * 取得业务分类
	 * @return json数组
	 */
	string getHealthyCategories(),
	
	/**
	 * 根据id取得一个分类
	 * @param id -- 业务分类id 
	 * @return 业务分类json对象
	 */
	string getHealthyCategory(1:string id),
	
	/**
	 * 创建一个业务分类
	 * @param parentId --父业务分类id
	 * @param categoryJson -- json对象
	 * @return 成功与否标志
	 */
	string createHealthyCategory(1:string parentId, 2:string categoryJson),
	
	/**
	 * 更新业务分类
	 * @param categoryJson --  json对象
	 * @return 成功与否标志
	 */
	string updateHealthyCategory(1:string categoryJson),
	
	/**
	 * 删除一个业务分类
	 * @param categoryId -- 业务分类id
	 * @return 成功与否标志
	 */
	string deleteCategory(1:string categoryId)
}