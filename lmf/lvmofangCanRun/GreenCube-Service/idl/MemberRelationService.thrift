namespace java com.cnd.greencube.server.service

/**
 * 会员关系
 * @author 陈炼
 */
service MemberRelationService {

	/**
	 * 注册环信用户
	 *
	 * @param username 用户登录id
	 * @param pwd 环信密码，和绿模仿平台密码不一样，自动生成，防止密码泄露给环信
	 * @return 返回环信返回的原始字符串
	 */
	string registerUser(1:string username, 2:string pwd),
	
	string addFriend(1:string hostUserId, 2:string guestUserId, 3:string relationType)
	
}