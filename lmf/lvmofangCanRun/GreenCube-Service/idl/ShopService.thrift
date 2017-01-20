namespace java com.cnd.greencube.server.service

/**
 * 店铺服务
 * @author 胡晓光
 */
service ShopService {
	/**
	 * 取得历史缴费记录列表
	 */
	string getShopManagerFee(1:string userid),
	
	/**
	 * 根据id获得店铺
	 * 
	 * @param id -- 店铺id
	 * @return 店铺的json对象
	 */
	string getShopById(1:string id),
	
	/**
	 * 我的店铺
	 * 
	 * @param id -- 店铺id
	 * @return 店铺的json对象
	 */
	string getMyShop(1:string userid),
	
	/**
	 * 我的店铺
	 * 
	 * @param id -- 店铺id
	 * @return 店铺的json对象
	 */
	string isMyShop(1:string token, 2:string shopId),
	
	/**
	 * 更新店铺信息
	 * 
	 * @param shopJson -- 店铺json
	 * @return 成功与否标志
	 */
	string submitApply(1:string userJson,2:string shopJson),
	
	/**
	 * 更新店铺信息
	 * @param shopJsonObject
	 * @return 成功与否标志
	 */
	string updateShop(1:string userJson,2:string shopJson),
	
	/**
	 * 删除店铺信息
	 * 
	 * @param shopId -- 店铺id
	 * @return 成功与否标志
	 */
	string deleteShop(1:string shopId),
	
	/**
	 * 取得店铺的评论
	 * 
	 * @param shopId -- 店铺id
	 * @param pageNum -- 页数
	 * @return 评论json数组
	 */
	string loadShopCommentsForPagelit(1:string shopId, 2:i32 pageNum),

	/**
	 * 分页查询店铺通知公告
	 * 
	 * @return
	 */
	string loadShopNoticeForPagelit(1:string shopId, 2:i32 pageNum),

	/**
	 * 分页查询店铺新闻
	 */
	string loadShopNewsForPagelit(1:string shopId, 2:i32 pageNum),
	
	/**
	 * 取得店铺资讯
	 * 
	 * @param newsId -- 新闻id
	 * @return 成功与否标志
	 */
	string getShopNewsById(1:string newsId),
	
	/**
	 * 保存咨询信息
	 * 
	 * @param newsId -- 新闻id
	 * @return 成功与否标志
	 */
	string updateNews(1:string newsJson),
	
	/**
	 * 删除咨询信息
	 * 
	 * @param newsId --新闻id
	 * @return 成功与否标志
	 */
	string deleteNews(1:string newsId),
	
	/**
	 * 保存通知信息
	 * 
	 * @param noticeId -- 新闻id
	 * @return 成功与否标志
	 */
	string updateNotice(1:string noticeJson),
	
	/**
	 * 删除通知信息
	 * 
	 * @param noticeId --新闻id
	 * @return 成功与否标志
	 */
	string deleteNotice(1:string noticeId),
	
	/**
	 * 分页获取待审核店铺
	 * @param pageNum 页数
	 * @return 店铺json数组
	 */
	string getAppliedShopsForPagelit(1:i32 pageNum),

	/**
	 * 分页获取已审核通过的店铺列表
	 * @param pageNum 页数
	 * @return 店铺json数组
	 */
	string getApprovedShopsForPagelit(1:i32 pageNum),
	
	/**
	 * 店铺评级
	 * @param shopId -- 店铺id
	 * @param level -- 等级
	 * @return 成功标识
	 */
	string rateShop(1:string shopId, 2:i32 level),

	/**
	 * 审核通过一个店铺
	 * @param shopId -- 店铺id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string approveShop(1:string shopId, 2:string auditUserId, 3:string auditUserName),
	
	/**
	 * 驳回申请
	 * @param shopId -- 店铺id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string rejectShop(1:string shopId, 2:string auditUserId, 3:string auditUserName),
	
	/**
	 * 店铺推荐
	 * @param shopId -- 店铺id
	 * @return 成功与否标识
	 */
	string recommendShop(1:string shopId),
	
	/**
	 * 取消店铺推荐
	 * @param shopId -- 店铺id
	 * @return 成功与否标识
	 */
	string unRecommendShop(1:string shopId),
	
	/**
	 * 更改店铺有效标志参数
	 * @param shopId -- 店铺id
	 * @return 成功与否标识
	 */
	string changeShopValidStatus(1:string shopId, 2:i32 validStatus),
	
	/**
	 * 更改店铺新闻状态
	 * @param newsId -- 新闻id
	 * @param validStatus -- 有效状态
	 * @return 成功与否标志
	 */
	string changeShopNewsValidStatus(1:string newsId, 2:i32 validStatus),
	
	/**
	 * 更改店铺新闻置顶状态
	 * @param newsId -- 新闻id
	 * @param validStatus -- 有效状态
	 * @return 成功与否标志
	 */
	string changeShopNewsTopStatus(1:string newsId, 2:i32 topStatus),
	
	/**
	 * 更改店铺新闻审核状态
	 * @param newsId -- 新闻id
	 * @param validStatus -- 有效状态
	 * @return 成功与否标志
	 */
	string changeShopNewsAuditStatus(1:string newsId, 2:i32 auditStatus),
	
	/**
	 * 对一个店铺进行评价
	 * @param shopid -- 店铺id
	 * @param commentJson 评论json内容
	 * @return 成功与否标志
	 */
	string submitShopComment(1:string shopId, 2:string content, 3:string userid),
}