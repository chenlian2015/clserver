namespace java com.cnd.greencube.server.service

/**
 * 内容服务接口<br/>
 * <b>用于前端访问提供的各种借口服务</b>
 * @author 胡晓光
 */
service CmsService {
/**
	 * 根据频道ID获取文章列表信息
	 * @param channelId 频道id
	 * @param pageinfo 分页信息
	 * @return 返回列表json数据
	 */
	string loadArticlesByChannelIdForPaglit(1:string channelId, 2:bool onlyReleased, 3:i32 pageNum),
	
	/**
	 * 根据条件查询文章信息
	 * @param keyword 文章关键字 <br>可以为空
	 * @return 列表json数据
	 */
	string searchArticles(1:string keyword, 2:bool onlyReleased,  3:i32 pageNum),
	
	/**
	 * 分页获取栏目列表数据
	 * @param pageinfo 分页信息
	 * @return 返回json数组
	 */
	string loadChannelsForPagelit(1:i32 pageNum),
	/**
	 * 获取栏目列表数据(不分页)
	 * @return 返回json数组
	 */
	string loadChannels(),
	
	/**
	 * 根据编号获取文章详细信息
	 * @param articleId 文章编号
	 */
	string getArticleById(1:string articleId),
	
	/**
	 * 更新文章信息
	 * @param articleJson 文章json数据
	 * @return 返回成功与否标识
	 */
	string updateArticle(1:string articleJson, 2:string channelId),
	
	/**
	 * 删除文章
	 * @param articleId文章id
	 * @return 返回成功与否标识
	 */
	string deleteArticle(1:string articleId),
	
	/**
	 * 发布文章
	 * @param articleId 文章Id
	 * @return 返回成功与否标识
	 */
	string publishArticle(1:string articleId),
	
	/**
	 * 取消发布一篇文章
	 * @param articleId 文章编号
	 * @return 返回成功与否标识
	 */
	string unPublishArticle(1:string articleId),
	
	/**
	 * 根据Id获取栏目详细信息
	 * @param channelId 栏目编号
	 * @return 返回栏目数据的json字符串
	 */
	string getChannelById(1:string channelId),
	
	/**
	 * 更新栏目
	 * @param channelJson 栏目json数据
	 * @return 成功与否标识
	 */
	string updateChannel(1:string channelJson),
	
	/**
	 * 删除栏目
	 * @param channelId 删除栏目
	 * @return 返回成功与否标识
	 */
	string deleteChannel(1:string channelId)
	
}