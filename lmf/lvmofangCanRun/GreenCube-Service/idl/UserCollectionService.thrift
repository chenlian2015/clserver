namespace java com.cnd.greencube.server.service

/**
 * 我的收藏服务
 * @author 胡晓光
 */
service UserCollectionService {
	/**
	 * 取得用户收藏的商品
	 * 
	 * @param userid -- 用户id
	 * @return 商品json数组
	 */
	string getUserCollectGoods(1:string userId, 2:i32 pageNum) ,

	/**
	 * 取得用户收藏的知识
	 * 
	 * @param userid -- 用户id
	 * @return 知识json数组
	 */
	string getUserCollectKnowledge(1:string userId, 2:i32 pageNum) ,

	/**
	 * 取得用户收藏的视频
	 * 
	 * @param userid -- 用户id
	 * @return 视频json数组
	 */
	string getUserCollectVideo(1:string userId, 2:i32 pageNum) ,
	
	/**
	 * 取得用户收藏的医院
	 * 
	 * @param userid -- 用户id
	 * @return 医院json数组
	 */
	string getUserCollectHospital(1:string userId, 2:i32 pageNum) ,
	
	/**
	 * 取得用户收藏的专家
	 * 
	 * @param userid -- 用户id
	 * @return 专家json数组
	 */
	string getUserCollectProfessor(1:string userId, 2:i32 pageNum) ,
	
	/**
	 * 取得用户收藏的信息
	 * 
	 * @param userid -- 用户id
	 * @return 文章json数组
	 */
	string getUserCollectArticle(1:string userId, 2:i32 pageNum),

	/**
	 * 判断在收藏中是否存在
	 * @param userId -- 用户id
	 * @param collectionId -- 收藏id
	 * @return
	 */
	string existInCollection(1:string userId, 2:string collectionId, 3:string storeType),

	/**
	 * 添加到收藏夹中
	 * 
	 * @param userid -- 用户id
	 * @param foreignId -- 外键id
	 * @param storeType -- 类别
	 */
	string add(1:string userId, 2:string foreignId, 3:string storeType),

	/**
	 * 删除收藏
	 * 
	 * @param userid -- 用户id
	 * @param foreignId -- 外键id
	 */
	string remove(1:string userId, 2:string foreignId),

	/**
	 * 取得一个用户的全部收藏id
	 * @param userId
	 * @return 返回全部收藏的id数组
	 */
	string getMyCollectionIds(1:string userId)
}