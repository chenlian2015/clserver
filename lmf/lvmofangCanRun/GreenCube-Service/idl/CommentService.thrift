namespace java com.cnd.greencube.server.service


/**
 * 评论服务
 * @author 胡晓光
 */
service CommentService {
	/**
	 * 取得TopN评论数据
	 * @param topN
	 * @return
	 */
	string loadCommentsByForeignIdTopN(1:string foreignId, 2:i32 topN),

	/**
	 * 分页取得评论数据
	 * @param pageInfo
	 * @return
	 */
	string loadCommentsByForeignId(1:string foreignId, 2:i32 pageNum),
	
    /**
     * 对某个对象进行评论
     * @param userid -- 评论人id
     * @param content -- 评论的内容
     * @param commentObjId -- 被评论的对象的id
     * @param commentObjName -- 被评论的对象的名称，可以是对象的类全名
     * @param score -- 评价的打分
     * @return 成功与否标志
     */
	string submitComment(1:string userid, 2:string content, 3:string commentObjId, 4:string commentObjName, 5:i32 score)
}