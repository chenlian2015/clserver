namespace java com.cnd.greencube.server.service


/**
 * 购物车服务
 * @author 胡晓光
 */
service ShoppingService {
	/**
	 * 我的购物车列表
	 */
	string mycartlist(1:string token, 2:i32 pageNum);
}