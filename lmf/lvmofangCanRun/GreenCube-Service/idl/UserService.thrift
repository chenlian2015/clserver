namespace java com.cnd.greencube.server.service


/**
 * 用户服务
 * @author 胡晓光
 @ 增加 dongyali
 */
service UserService {
	/**
	 * 绑定用户手机
	 * 
	 * @param userid
	 * @param mobile
	 */
	string bindMobile(1:string userid, 2:string mobile),
	
	/**
	 * 判断是否存在相同手机号码
	 * 
	 * @param mobile
	 */
	string getUserByEmail(1:string email),
	
	/**
	 * 根据手机号取得当前登录用户
	 * 
	 * @param mobile
	 * @return
	 */
	string getUserByMobile(1:string mobile),
	
	/**
	 * 判断用户账号是否存在
	 * 
	 * @return
	 */
	string getUserByUserName(1:string username),
	
	/**
	 * 通过账号密码查询用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	string getUserById(1:string userId),
	
	/**
	 * 用户修改密码方法
	 * 
	 * @param userId
	 * @param password
	 */
	string updatePwd(1:string userid, 2:string oldPassword,  3:string newPassword),
	
	/**
	 * 忘记密码
	 * 
	 * @param userId
	 * @param password
	 */
	string forgetPwd(2:string phone, 4:string newPassword),
	
	/**
	 * 更新用户个人信息
	 * 
	 * @param user
	 */

	string updateUser(1:string userJson),

	
	/**
	 * 更新上次登录时间
	 * @param userid -- 用户id
	 */
	string updateUserLastLoginTime(1:string userid),
	
	/**
	 * 分页获取用户信息
	 * @param pageNum 页数
	 * @return 注册用户json数组
	 */
	string getUsersForPagelit(1:i32 pageNum),
	/**
	 * 新增用户
	 * @param userJson 用户实体类
	 * @return 用户id
	 */
	string addUser(1:string userJson)
}