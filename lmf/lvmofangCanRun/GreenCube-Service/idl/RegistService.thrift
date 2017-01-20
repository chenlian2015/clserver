namespace java com.cnd.greencube.server.service

/**
 * 注册服务
 * @author 胡晓光 
 */
service RegistService {
	/**
	 * 手机注册
	 * @param ownerId -- mobile
	 * @param pageNum -- verifyCode
	 * @param imei -- 手机串号
	 * @param password -- 密码
	 */
	string appRegist(1:string mobile ,2:string verifyCode, 3:string imei, 4:string password);
	
	/**
	 * 显示一个分类下面的视频
	 * @param usernmae -- 业务分类
	 * @param password -- 页数
	 * @param nickname -- 昵称
	 * @param mobile -- 手机号码
	 */
	string webRegist(1:string usernmae, 2:string password, 3:string nickname, 4:string mobile);
	
}