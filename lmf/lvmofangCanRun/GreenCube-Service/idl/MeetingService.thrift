namespace java com.cnd.greencube.server.service


/**
 * 会议服务类
 * @author 会议服务
 */
service MeetingService {
	/**
	 * 创建一个会议室
	 * @param meetingName -- 会议名称
	 * @param password -- 密码
	 * @param topic -- 会议议题
	 * @param maxPersonCount -- 最大与会人员数
	 * @param meetingModel -- 会议模式 1-视频会议，2-传统会议模式
	 * @param type 1-公共会议室，2-私人会议室
	 * @param status 0-未开始，1-已开始，2-已结束
	 * @param creatorId -- 创建者id
	 * @param beginTime -- 开始时间
	 * @param endTime -- 结束时间
	 */
	string createMeetRoot(1:string meetingName, 2:string password, 3:string topic, 4:i32 maxPersonCount, 5:i32 meetingModel, 6:i32 type, 7:i32 status, 8:string creatorId, 9:string beginTime, 10:string endTime),

	/**
	 * 开始一个会议
	 * @param meetingId -- 会议室id
	 * @return 返回成功与否标志
	 */
	string startMeeting(1:string meetingId),
	
	/**
	 * 关闭会议室
	 * @param meetingId -- 会议室id
	 * @return 返回成功与否标志
	 */
	string closeMeeting(1:string meetingId),
	
	/**
	 * 进入会议室
	 * @param meetingId -- 会议id
	 * @param userid -- 进入会议室人的id
	 * @return -- 返回成功与否标志
	 */
	string enterMeeting(1:string meetingId, 2:string userid),
	
	/**
	 * 退出会议室
	 * @param meetingId -- 会议室id
	 * @param userid -- 退出会议室人的id
	 * @return 返回成功与否标志
	 */
	string quitMeeting(1:string meetingId, 2:string userid),
	
	/**
	 * 要求一个人进入会议室
	 * @param meetingId -- 会议室id
	 * @param userId -- 用户id
	 * @return 返回成功与否标志
	 */
	string inviteUser(1:string meetingId, 2:string userId),
	
	/**
	 * 设置会议室密码
	 * @param meetingId -- 会议室id
	 * @param password -- 会议室密码
	 * @return 返回成功与否标志
	 */
	string setMeetingPassword(1:string meetingId, 2:string password),
	
	/**
	 * 获取会议室消息
	 * @param meetingId -- 会议室id
	 * @return -- 返回成消息json数组
	 */
	string loadMeetingMessage (1:string meetingId, 2:i32 pageNum),
	
	/**
	 * 发送文本消息
	 * @param mettingid -- 会议室id 
	 * @param parentMessageId -- 父消息id
	 * @param posterId -- 发布者id
	 * @param content -- 消息内容
	 * @return 返回成功与否标志
	 */
	string postTextMessage(1:string meetingId, 2:string parentMessageid, 3:string posterId, 4:string content),
	
	/**
	 * 发送文本消息
	 * @param mettingid -- 会议室id 
	 * @param parentMessageId -- 父消息id
	 * @param posterId -- 发布者id
	 * @param content -- 消息内容
	 * @return 返回成功与否标志
	 */
	string postVoiceMessage(1:string meetingid, 2:string parentMessageid, 3:string posterId),
	
	/**
	 * 对消息点赞
	 * @param messageid -- 消息id
	 * @return 返回成功与否标志
	 */
	string supportMessage(1:string messageid)
}