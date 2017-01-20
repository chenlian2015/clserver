namespace java com.cnd.greencube.server.service

/**
 * 店铺商品-服务
 * @author 胡晓光
 */
service ShopGoodsService {
	/**
	 * 店主取得店铺的商品
	 * @param shopId -- 店铺id
	 * @param keyword -- 过滤关键字
	 * @param pageNum -- 分页页数
	 * @return 商品json数组
	 */
	string loadShopGoodsByKeywords(1:string shopId, 2:string keyword, 3:i32 pageNum),
	
	/**
	 * 店主取得店铺的商品
	 * @param shopId -- 店铺id
	 * @param keyword -- 过滤关键字
	 * @param pageNum -- 分页页数
	 * @return 商品json数组
	 */
	string loadMyShopGoods(1:string token, 3:i32 pageNum),
	
	/**
	 * 查看店铺的商品，分页显示
	 * @param shopId -- 店铺id
	 * @param filter -- 过滤关键字
	 * @param status -- 店铺状态
	 * @return
	 */
	string loadShopGoodsByCategoryId(1:string shopId, 2:string categoryId, 3:i32 pageNum),
	
	/**
	 * 获取一个店铺TopN商品
	 * @param shopId -- 店铺id
	 * @param top -- 数字
	 * @return 返回店铺商品json数组
	 */
	string loadShopGoodsTopN(1:string shopId, 2:i32 number),
	
	/**
	 * 搜索商品
	 * @param categoryId -- 分类id
	 * @param provinceId -- 省id
	 * @param cityId -- 城市id 
	 * @param keyword -- 商品关键字
	 * @return 商品json 数组
	 */
	string searchGoodsForPagelit(1:string shopId, 2:string categoryId, 3:string provinceId, 4:string cityId, 5:string keyword, 6:i32 pageNum),
	
	/**
	 * 取得热门商品
	 * @param count -- 返回记录数
	 * @return 商品json数组
	 */
	string getHotGoods(1:i32 count),
	
	/**
	 * 获取一个商品详细信息
	 * @param shopGoodsId
	 * @return 商品json对象
	 */
	string getGoodsById(1:string id),
	
	/**
	 * 保存商品信息<br/>如果id为空，则执行保存操作，如果id非空，则执行更新操作
	 * 
	 * @param goodsJson -- 商品的json数据
	 * @param courseIdList
	 */
	string updateGoods(1:string goodsJson),

	/**
	 * 删除商品
	 * @param 商品id
	 * @return 成功与否标识
	 */
	string deleteGoodsById(1:string token, 2:string goodsId),

	/**
	 * 商品上架操作
	 * @param goodsId -- 商品id
	 * @return 成功与否标识
	 */
	string putShelf(1:string goodsId),

	/**
	 * 商品下架操作
	 * @param goodsId -- 商品id
	 * @return 成功与否标识
	 */
	string offShelf(1:string goodsId)
	
	/**
	 * 获取商品项目列表
	 * @param goodsId -- 商品id
	 * @return 关联的供应商商品列表
	 */
	string getGoodsItems(1:string goodsId),

	/**
	 * 获取商品评论列表
	 * 
	 * @param goodsId -- 商品id
	 * @param pageNum -- 页数
	 * @return 商品评论json数组
	 */
	string loadGoodsComment(1:string goodsId, 2:i32 pageNum),
		/**
	 * 分页获取待审核店主会商品
	 * @param pageNum 页数
	 * @return 店主会商品json数组
	 */
	string loadAppliedShopGoodsForPagelit(1:i32 pageNum),

	/**
	 * 分页获取已审核通过的店主会商品列表
	 * @param pageNum 页数/**
	 * 依据成员得到商品列表
	 * @param memberId
	 * @return 店主会商品json数组
	 */
	string loadApprovedShopGoodsForPagelit(1:i32 pageNum),
	/**
	 * 审核通过一个店铺商品
	 * @param goodsId -- 店铺商品id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string approveShopGoods(1:string goodsId, 2:string auditUserId, 3:string auditUserName),
	 /**     
	  * 驳回通过一个店铺商品
	 * @param goodsId -- 店铺商品id
	 * @param auditUserId -- 审核人id
	 * @param auditUserName --  审核人姓名
	 * @return 成功与否标识
	 */
	string rejectShopGoods(1:string goodsId, 2:string auditUserId, 3:string auditUserName),
	/**
	 * 获取一个商品详细信息
	 * @param id 商品条目id
	 * @return 商品条目json对象
	 */
	string getGoodsItemById(1:string id),
	/**
	 * 依据成员得到商品列表
	 * @param memberId
	 * @return 商品json对象
	 */
	string loadGoodsByMemberId(1:string memberId,2:i32 pageNum),
     /**
      * 添加店主会商品（主表和明细表）
      * @param shopGoodsJson
      * @param shopGoodsItemJson
      * @return
      */
     string addShopGoodsEntity(1:string shopGoodsJson,2:string shopGoodsItemJson)
	
}