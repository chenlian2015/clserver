namespace java com.cnd.greencube.server.service

/**
 * 培训服务
 * @author 胡晓光
 */
service TrainService {
	/**
	 * 获取试卷类别列表
	 * 
	 * @return
	 */
	string loadTrainCategories(),

	/**
	 * 获取类别详细信息
	 * 
	 * @param categoryId
	 * @return
	 */
	string getTrainCategoryById(1:string categoryId),

	/**
	 * 保存类别
	 * 
	 * @param category
	 */
	string saveTrainCategory(1:string categoryJson),

	/**
	 * 更新类别
	 */
	string updateTrainCategory(1:string categoryJson),

	/**
	 * 删除类别
	 * 
	 * @param categoryId
	 */
	string deleteTrainCategoryById(1:string categoryId),
	
	/**
	 * 获取试卷列表
	 * @param categoryId -- 类别id，可以为空，为空时则查询全部培训
	 * @return
	 */
	string loadTrains(1:string categoryId),
	
	/**
	 * 获取试卷详细信息
	 * 
	 * @param trainId
	 * @return
	 */
	string getTrainById(1:string trainId),
	
	/**
	 * 获取一个试卷，本接口是为前端提供的获取一个试卷的接口
	 * 
	 */
	string frt_getTrainById(1:string trainId),

	/**
	 * 保存试卷
	 * 
	 * @param trainJson
	 */
	string saveTrain(1:string trainJson),

	/**
	 * 更新试卷
	 * 
	 * @param trainJson
	 */
	string updateTrain(1:string trainJson),

	/**
	 * 删除试卷
	 * 
	 * @param trainId
	 */
	string deleteTrainById(1:string trainId),
	
	/**
	 * 获取试题列表
	 * @param trainId -- 培训id，可为空，为空时则提取全部试题
	 * @return
	 */
	string loadQuestions(1:string trainId),
	
	/**
	 * 通过关键字搜索一个试题
	 */
	string searchQuestions(1:string keyword),
	
	/**
	 * 获取试题详细信息
	 * 
	 * @param topicId
	 * @return
	 */
	string getQuestionById(1:string questionId),
	
	/**
	 * 保存试题
	 * 
	 * @param topic
	 * @param seqs
	 * @param titles
	 */
	string saveQuestion(1:string topicJson, 2:string seqJson, 3:string titleJson),

	/**
	 * 更新试题
	 * 
	 * @param topic
	 * @param items
	 * @param seqs
	 * @param titles
	 */
	string updateQuestion(1:string topicJson, 2:string seqJson, 3:string titleJson),

	/**
	 * 删除试题
	 * 
	 * @param topicId
	 */
	string deleteQuestionById(1:string topicId),
	
	/**
	 * 获取选择题选项列表
	 * 
	 * @param topicId
	 * @return
	 */
	string loadQuestionOptions(1:string questionId)
}