/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 供产会商品服务实现类
 * 
 * @author huxg
 * @author dongyali 加注释
 */
public interface ProductBusiness {
	/**
	 * 按照关键字和业务类别分页搜索共产会商品列表
	 * 
	 * @param keyword
	 *            关键字
	 * @param categoryId
	 *            业务类别id
	 * @param pageInfo
	 *            起始数
	 */

	List<Product> loadProductByKeywordsAndCategory(String keyword, String categoryId, PageInfo pageInfo);

	/**
	 * 按照关键字分页搜索共产会商品列表
	 * 
	 * 共产会id
	 * 
	 * @param keyword
	 *            关键字
	 * @param pageNum
	 *            起始数
	 */

	List<Product> loadProductByKeywords(String userId, String keyword, PageInfo pageInfo);

	/**
	 * 按照业务类别分页搜索共产会商品列表
	 * 
	 * @param userId
	 *            共产会id
	 * @param categoryId
	 *            类别id
	 * @param pageNum
	 *            起始数
	 */

	List<Product> loadProductByCategoryId(String userId, String categoryId, PageInfo pageInfo);

	/**
	 * 显示前n条数据
	 * 
	 * @param userId
	 *            共产会id
	 * @param number
	 *            前n条
	 */

	List<Product> loadProductTopN(String userId, int number);

	/**
	 * 依据相关条件查询共产会商品列表
	 * 
	 * @param userId
	 * @param categoryId
	 * @param provinceId
	 * @param cityIdthrift2java
	 *            .sh
	 * @param keyword
	 * @param pageNum
	 * 
	 */

	List<Product> searchProductForPagelit(String userId, String categoryId, String provinceId, String cityId,
			String keyword, PageInfo pageInfo) throws Exception;

	/**
	 * 得到热点商品
	 */

	List<Product> getHotProduct(int count);

	/**
	 * 得到商品依据id
	 */

	Product getProductById(String id);

	/**
	 * 修改商品
	 */

	boolean updateProduct(Product product);

	/**
	 * 删除
	 */

	boolean deleteProductById(String productId);

	/**
	 * 商品上架
	 */

	boolean putShelf(String productId);

	/**
	 * 商品下架
	 */

	boolean offShelf(String productId);

	/**
	 * 分页获取待审核共产会商品
	 * 
	 * @param pageNum
	 *            页数
	 * @return 共产会商品json数组
	 * 
	 * @param pageNum
	 */
	public List<Product> loadAppliedProductForPagelit(PageInfo pageInfo);

	/**
	 * 分页获取已审核通过的共产会商品列表
	 * 
	 * @param pageNum
	 *            页数
	 * @return 店铺商品json数组
	 * 
	 * @param pageNum
	 */
	public List<Product> loadApprovedProductForPagelit(PageInfo pageInfo);

	/**
	 * 审核通过一个店铺商品
	 * 
	 * @param productId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 * 
	 * @param productId
	 * @param auditUserId
	 * @param auditUserName
	 */
	public boolean approveProduct(String productId, String auditUserId, String auditUserName);

	/**
	 * 驳回通过一个店铺商品
	 * 
	 * @param productId
	 *            -- 店铺id
	 * @param auditUserId
	 *            -- 审核人id
	 * @param auditUserName
	 *            -- 审核人姓名
	 * @return 成功与否标识
	 * 
	 * @param productId
	 * @param auditUserId
	 * @param auditUserName
	 */
	public boolean rejectProduct(String productId, String auditUserId, String auditUserName);

	/**
	 * 依据成员得到商品列表
	 * 
	 * @param memberId
	 * @param pageNum
	 *            页数
	 * @return 商品list
	 * 
	 * @param memberId
	 */
	public List<Product> loadProductByUserId(String userId, PageInfo pageInfo);

	/**
	 * 添加供产会商品
	 * 
	 * @param pg
	 * @return
	 */
	public boolean addProductEntity(Product pg);

	/**
	 * 修改供产会商品
	 * 
	 * @param pg
	 * @return
	 */
	public boolean updateProductEntity(Product pg);
	
	
	

}
