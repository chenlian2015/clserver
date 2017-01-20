namespace java com.cnd.greencube.server.service

/**
 * 视频管理服务类
 * @author 胡晓光 
 */
service VideoService {
/**
	 * 取得视频列表
	 * @param ownerId -- 发布者ID
	 * @param pageNum -- 页数
	 */
	string loadVideosForPagelit(1:string sellerId, 2:bool strictAudited, 3:i32 pageNum),
	
	/**
	 * 显示一个分类下面的视频
	 * @param categoryId -- 业务分类
	 * @param pageNum -- 页数
	 */
	string loadVideosByCategoryId(1:string categoryId, 2:bool strictAudited, 3:i32 pageNum),
	
	/**
	 * 根据主键获取视频
	 * @param videoId -- 视频id
	 * @return 返回视频json对象
	 */
	string getVideoById(1:string videoId),
	
	/**
	 * 上传视频
	 * @param videoJson
	 */
	string updateVideo(1:string videoJson),
	
	/**
	 * 删除视频
	 * @param videoId 视频id
	 */
	string deleteVideo(1:string videoId),
	
	/**
	 * 审核视频
	 * @param videoId -- 被审核的视频id
	 * @param userid -- 被审核人id
	 * @param username -- 被审核人姓名
	 */
	string approveVideo(1:string videoId, 2:string userid, 3:string username),
	
	/**
	 * 驳回视频
	 * @param videoId -- 被审核视频id
	 * @param userid -- 审核人id
	 * @param username -- 审核人姓名
	 */
	string rejectVideo(1:string videoId, 2:string userid, 3:string username)
}