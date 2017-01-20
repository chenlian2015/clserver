namespace java com.cnd.greencube.server.service

/**
 * 登录服务
 */
service LoginService {
	/**
	 * app的登录
	 */
	string applogin(1:string imei, 2:string username, 3:string pwd, 4:string verifycode, 5:bool pwdEncrypted , 6:string __remoteAddr),
	
	/**
	 * app维护心跳
	 */
	string appHeartBeat(1:string token, 2:string imei, 3:string __remoteAddr),
	
	/**
	 * 登录
	 */
	string login(1:string username, 2:string pwd, 3:string verifycode, 4:bool pwdEncrypted, 5:string usertype),
	
	/**
	 * 退出登录
	 */
	string logout(1:string userid)
}