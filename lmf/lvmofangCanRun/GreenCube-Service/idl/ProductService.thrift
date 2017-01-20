namespace java com.cnd.greencube.server.service

/**
 * 供应商商品服务
 * @author 胡晓光
 */
service ProductService {
	/**
	 * 店主依据业务类别和关键字取得店铺的商品
	 * @param keyword -- 过滤关键字
	 * @param categoryId -- 业务类别id
	 * @param pageNum -- 分页页数
	 * @return 商品json数组
	 */
	string loadProductByKeywordsandCategory(1:string keyword, 2:string categoryId, 3:i32 pageNum),
	/**
	 * 店主取得店铺的商品
	 * @param userId -- 店铺id
	 * @param keyword -- 过滤关键字
	 * @param pageNum -- 分页页数
	 * @return 商品json数组
	 */
	string loadProductByKeywords(1:string userId, 2:string keyword, 3:i32 pageNum),
	
	/**
	 * 查看店铺的商品，分页显示
	 * @param userId -- 店铺id
	 * @param filter -- 过滤关键字
	 * @param status -- 店铺状态
	 * @return
	 */
	string loadProductByCategoryId(1:string userId, 2:string categoryId, 3:i32 pageNum),
	
	/**
	 * 获取一个店铺TopN商品
	 * @param userId -- 店铺id
	 * @param top -- 数字
	 * @return 返回店铺商品json数组
	 */
	string loadProductTopN(1:string userId, 2:i32 number),
	
	/**
	 * 搜索商品
	 * @param categoryId -- 分类id
	 * @param provinceId -- 省id
	 * @param cityId -- 城市id 
	 * @param keyword -- 商品关键字
	 * @return 商品json 数组
	 */
	string searchProductForPagelit(1:string userId, 2:string categoryId, 3:string provinceId, 4:string cityId, 5:string keyword, 6:i32 pageNum),
	
	/**
	 * 取得热门商品
	 * @param count -- 返回记录数
	 * @return 商品json数组
	 */
	string getHotProducts(1:i32 count),
	
	/**
	 * 获取一个商品详细信息
	 * @param userId
	 * @return 商品json对象
	 */
	string getProductById(1:string id),
	
	/**
	 * 保存商品信息<br/>如果id为空，则执行保存操作，如果id非空，则执行更新操作
	 * 
	 * @param goodsJson -- 商品的json数据
	 * @param courseIdList
	 */
	string updateProduct(1:string productJson),

	/**
	 * 删除商品
	 * @param 商品id
	 * @return 成功与否标识
	 */
	string deleteProductById(1:string productId),

	/**
	 * 商品上架操作
	 * @param goodsId -- 商品id
	 * @return 成功与否标识
	 */
	string putShelf(1:string productId),

	/**
	 * 商品下架操作
	 * @param goodsId -- 商品id
	 * @return 成功与否标识loadProductByKeywords
	 */
	string offShelf(1:string productId),
		/**
	 * 分页获取待审核共产会商品
	 * @param pageNum 页数
	 * @return 共产会商品json数组
	 */
	string loadAppliedProductForPagelit(1:i32 pageNum),

	/**
	 * 分页获取已审核通过的共产会商品列表
	 * @param pageNum 页数
	 * @return 店铺商品json数组
	 */
	string loadApprovedProductForPagelit(1:i32 pageNum),
		/**
	 * 审核通过一个店铺商品
	 * @param goodId -- 店铺商品id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string approveProduct(1:string productId, 2:string auditUserId, 3:string auditUserName),
	
	/**
	 * 驳回通过一个店铺商品
	 * @param goodId -- 店铺商品id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string rejectProduct(1:string productId, 2:string auditUserId, 3:string auditUserName),
	
	/**
	 * 依据成员得到商品列表
	 * @param memberId
	 * @return 商品json对象
	 */
	string loadProductByUserId(1:string userId,2:i32 pageNum),
	
	 /**
     * 添加供产会商品
     * @param pg
     * @return
     */
    string addProductEntity(1:string productJson),
    
    /**
     * 修改供产会商品
     * @param pg
     * @return
     */
    string updateProductEntity(1:string productJson),
    

}