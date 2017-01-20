namespace java com.cnd.greencube.server.service


/**
 * 用户订单服务类
 * @author 胡晓光
 */
service OrderService {
	/**
	 * 取得我的订单
	 * @param userid -- 用户id
	 * @param filter -- 订单类型
	 * @param pageNum -- 页数
	 */
	string loadBuierOrders(1:string userid, 2:string filter, 3:i32 pageNum),
	
	/**
	 * 取得一个订单的详细信息
	 * @param orderid -- 订单id
	 * @return 订单的json数据
	 */
	string getBuierOrder(1:string orderid),
	
	/**
	 * 删除订单
	 * @param -- 订单id
	 * @return 成功与否标志
	 */
	string deleteBuierOrder(1:string orderid),
	
	/**
	 * 结账操作
	 * @param userid -- 用户id
	 * @param shoppingItemsJson -- 购物车中的订单项json数据
	 * @param needInvoice -- 是否需要发票
	 * @return -- 成功与否标志
	 */
	string createOrder(1:string userid, 2:string shoppingItemsJson, 3:string couponId, 4:string receiverId),
	
	/**
	 * 提交发票申请
	 * @param orderid -- 订单id
	 * @param invoiceJson -- 订单json数据
	 * @return -- 成功与否标志
	 */
	string submitOrderInvoiceApply(1:string orderid,2:string invoiceJson),
	
	/**
	 * 审核发票申请信息
	 * @param invoiceId -- 发票id
	 * @return -- 成功与否标志
	 */
	string auditOrderInvoice(1:string invoiceId, 2:string auditorId),
	
	/**
	 * 审核发票申请信息
	 * @param invoiceId -- 发票id
	 * @return -- 成功与否标志
	 */
	string unAuditOrderInvoice(1:string invoiceId, 2:string auditorId),
	
	/**
	 * 发布发票
	 * @param invoiceId -- 发票id
	 * @param publisherId -- 操作人
	 * @return -- 成功与否标志
	 */
	string publishOrderInvoice(1:string invoiceId, 2:string publisherId),
	
	/**
	 * 取消发布发票
	 * @param invoiceId -- 发票id
	 * @param publisherId -- 操作人
	 * @return 成功与否标志
	 */
	string unPublishOrderInvoice(1:string invoiceId, 2:string publisherId),
	
	/**
	 * 取得订单的发票信息
	 * @param orderid -- 订单id
	 * @return 订单发票json数据
	 */
	string getOrderInvoice(1:string orderid),
	
	/**
	 * 返回已申请的发票列表
	 * @param pageNum -- 页数
	 * @return 订单发票json数组
	 */
	string loadApplyOrderInvoice(1:i32 pageNum),
	
	/**
	 * 返回已完结的发票
	 * @param pageNum -- 页数
	 * @return 订单发票json数组
	 */
	string loadPublishedOrderInvoice(1:i32 pageNum),
	
	/**
	 * 修改订单发票信息
	 * @param orderid -- 订单id
	 * @param invoiceJson -- 发票json数据
	 * @return 成功与否标志
	 */	
	string updateOrderInvoice(1:string orderid, 2:string invoiceJson),	
	
	/**
	 * 取得一个订单的快递信息
	 * @param orderid -- 订单id
	 * @return 返回订单的快递信息json数据
	 */
	string getOrderMailing(1:string orderid),
	
	/**
	 * 取得店铺订单
	 * @param shopid -- 店铺id
	 * @param pageNum -- 页数
	 * @return 返回店铺订单列表
	 */
	string loadShopOrdersForPagelit(1:string shopid, 2:i32 pageNum),
	
	/**
	 * 取得店铺订单
	 * @param shopOrderId
	 * @return 返回店铺订单json对象
	 */
	string getShopOrder(1:string shopOrdeId),
	
	/**
	 * 删除订单
	 * @param shopOrderId -- 店铺订单id
	 * @return 返回成功标志
	 */
	string deleteShopOrder(1:string shopOrderId),
	
	/**
	 * 取得供应商订单列表
	 * @param userId -- 供应商id
	 * @param pageNum -- 页数
	 * @return 返回供应商订单json数组
	 */
	string loadProductOrdersForPagelit(1:string userId, 2:i32 pageNum),
	
	/**
	 * 取得供应商订单
	 * @param userId -- 供应商id	/**
	 * @return 返回供应商订单json对象
	 */
	string getProductOrder(1:string productOrderId),
	
	/**
	 * 删除供应商订单
	 * @param productOrderId -- 供应商订单id
	 * @return 返回成功与否标志
	 */
	string deleteProductOrder(1:string productOrderId),
	
	/**
	 * 取得我的订单(依据订单号)
	 * @param orderNum -- 订单单号
	 * @param pageNum -- 页数
	 */
	string searchOrdersByOrderNum(1:string orderNum, 2:i32 pageNum),
	
	/**
	 * 取得我的订单条目(依据订单号)
	 * @param orderId -- 订单id
	 */
	string loadOrderItemsByOrderId(1:string orderId),
		/**
	 * 分页取得我的订单条目(依据订单号)
	 * @param orderId -- 订单id
	 * @param pageNum -- 页数
	  @param status -- 订单状态
	 */
	string loadOrderItemsByOrderIdForPageLit(1:string orderId,2:i32 pageNum)
}