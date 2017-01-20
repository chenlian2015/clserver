namespace java com.cnd.greencube.server.service

/**
 * 需求大厅服务
 * @author 胡晓光
 */
service RequirementService {
	/**
	 * 提交需求
	 * @param title -- 标题
	 * @param content -- 内容
	 * @param parentRequirementId -- 父需求id 
	 * @param posterId -- 发布者id
	 * @param posterIp -- 发布者ip地址
	 * @param requirementType -- 需求类别， 1-我要， 2-我有
	 */
	string submitText(1:string title, 2:string content, 3:string posterId,4:string __remoteAddr, 5:i32 requirementType),
	
	/**
	 * 提交需求
	 * @param title -- 标题
	 * @param content -- 内容
	 * @param parentRequirementId -- 父需求id 
	 * @param posterId -- 发布者id
	 * @param posterIp -- 发布者ip地址
	 * @param requirementType -- 需求类别， 1-我要， 2-我有
	 */
	string submitPhoto(1:string title, 2:string content, 3:string multipart, 4:string posterId, 5:string __remoteAddr, 6:i32 requirementType),
	
	/**
	 * 提交需求
	 * @param title -- 标题
	 * @param content -- 内容
	 * @param parentRequirementId -- 父需求id 
	 * @param posterId -- 发布者id
	 * @param posterIp -- 发布者ip地址
	 * @param requirementType -- 需求类别， 1-我要， 2-我有
	 */
	string submitVideo(1:string title, 2:string content, 3:string multipart, 4:string posterId,5:string __remoteAddr, 6:i32 requirementType),
	
	/**
	 * 回复需求
	 * @param parentRequirementId -- 需求id
	 * @param title -- 标题
	 * @param content -- 内容
	 * @param posterId -- 发布者id
	 * @param posterIp -- 发布者ip地址
	 * @param requirementType -- 类型
	 */
	string replyRequirement(1:string rid, 2:string title, 3:string content, 4:string posterId, 5:string __remoteAddr),
	
	/**
	 * 删除需求
	 * @param requirementId -- 需求id
	 * @return 返回成功与否
	 */
	string deleteRequirement(1:string rid),
	
	
	/**
	 * 赞一个需求
	 * @param requirementId
	 * @return 成功与否标志
	 */
	string supportRequirement(1:string rid),
	
	/**
	 * 分页返回需求json数组
	 * @param requirementType 1-我要，2-我有
	 * @param pageNum -- 页数
	 */
	string loadRequirement(1:i32 type, 2:i32 pageNum),
	
	/**
	 * 根据id读取一条需求
	 * @param requireimentId -- 需求id
	 * @return 返回需求json对象
	 */
	string getRequirement(1:string rid),
	
	/**
	 * 根据主键编号获取需求，本方法会增加requirement的点击数
	 * @param requirementId -- 需求id
	 * @return 返回需求json对象
	 */
	string readRequirement(1:string rid),
	
	/**
	 * 返回评论列表
	 * @param requirementId -- 需求id
	 * @return 返回需求json对象
	 */
	string loadReplies(1:string rid, 2:i32 pageNum),
	/**
	 * 分页获取需求（依据类别）
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 */
	string loadRequirementByCategoryId(1:string categoryId, 2:i32 pageNum),
	/**
	 * 依据类别得到需求数量
	 * @param categoryId
	 * @return
	 */
	string getRequirementCount(1:string categoryId)
}