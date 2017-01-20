/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.ProductBusiness;
import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.ProductService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 供产会商品服务实现类
 * 
 * @author huxg
 * @author dongyali 加注释
 */
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService.Iface {
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Resource(name = "ProductBusinessImpl")
	protected ProductBusiness productBusiness;

	/**
	 * 1 按照关键字和业务类别分页搜索共产会商品列表
	 * 
	 * @param keyword
	 *            关键字
	 * @param categoryId
	 *            业务类别id
	 * @param pageNum
	 *            起始数
	 */

	@Override
	public String loadProductByKeywordsandCategory(String keyword, String categoryId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> goods = productBusiness.loadProductByKeywordsAndCategory(keyword, categoryId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 2 按照关键字分页搜索共产会商品列表
	 * 
	 * @param providerId
	 *            共产会id
	 * @param keyword
	 *            关键字
	 * @param pageNum
	 *            起始数
	 */
	@Override
	public String loadProductByKeywords(String userId, String keyword, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> product = productBusiness.loadProductByKeywords(userId, keyword, pageInfo);
			return Message.okMessage(product, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 3 按照业务类别分页搜索共产会商品列表
	 * 
	 * @param providerId
	 *            共产会id
	 * @param categoryId
	 *            类别id
	 * @param pageNum
	 *            起始数
	 */
	@Override
	public String loadProductByCategoryId(String productId, String categoryId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> goods = productBusiness.loadProductByCategoryId(productId, categoryId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 4 显示前n条数据
	 * 
	 * @param providerId
	 *            共产会id
	 * @param number
	 *            前n条
	 */
	@Override
	public String loadProductTopN(String productId, int number) throws TException {
		try {
			List<Product> goods = productBusiness.loadProductTopN(productId, number);
			return Message.okMessage(goods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 5 依据相关条件查询共产会商品列表
	 * 
	 * @param providerId
	 * @param categoryId
	 * @param provinceId
	 * @param cityId
	 * @param keyword
	 * @param pageNum
	 * 
	 */
	@Override
	public String searchProductForPagelit(String productId, String categoryId, String provinceId, String cityId, String keyword, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> goods = productBusiness.searchProductForPagelit(productId, categoryId, provinceId, cityId, keyword, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 6 得到热点商品
	 */
	@Override
	public String getHotProducts(int count) throws TException {
		try {
			List<Product> goods = productBusiness.getHotProduct(count);
			return Message.okMessage(goods);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 7 得到商品依据id
	 */
	@Override
	public String getProductById(String id) throws TException {
		try {
			Product g = productBusiness.getProductById(id);
			return Message.okMessage(g);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 8 修改商品
	 */
	@Override
	public String updateProduct(String productJson) throws TException {
		try {
			Product g = JsonUtils.String2Bean(productJson, Product.class);
			productBusiness.updateProduct(g);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 9 删除
	 */
	@Override
	public String deleteProductById(String productId) throws TException {
		try {
			productBusiness.deleteProductById(productId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 10 商品上架
	 */
	@Override
	public String putShelf(String goodsId) throws TException {
		try {
			productBusiness.putShelf(goodsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 11 商品下架
	 */
	@Override
	public String offShelf(String goodsId) throws TException {
		try {
			productBusiness.offShelf(goodsId);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 12 未通过审核的共产会商品
	 */
	@Override
	public String loadAppliedProductForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> providerGoods = productBusiness.loadAppliedProductForPagelit(pageInfo);
			return Message.okMessage(providerGoods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 13 已通过审核的共产会商品
	 */
	@Override
	public String loadApprovedProductForPagelit(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> providerGoods = productBusiness.loadApprovedProductForPagelit(pageInfo);
			return Message.okMessage(providerGoods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 14 审核通过共产会商品
	 */
	@Override
	public String approveProduct(String goodsId, String auditUserId, String auditUserName) throws TException {
		try {
			productBusiness.approveProduct(goodsId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 15 审核驳回共产会商品
	 */
	@Override
	public String rejectProduct(String goodsId, String auditUserId, String auditUserName) throws TException {
		try {
			productBusiness.rejectProduct(goodsId, auditUserId, auditUserName);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 16 依据成员id得到共产会商品
	 */
	@Override
	public String loadProductByUserId(String userId, int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<Product> goods = productBusiness.loadProductByUserId(userId, pageInfo);
			return Message.okMessage(goods, pageInfo);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 17 增加共产会商品实体
	 */
	@Override
	public String addProductEntity(String productJson) throws TException {
		try {
			Product pg = JsonUtils.String2Bean(productJson, Product.class);
			productBusiness.addProductEntity(pg);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 18 修改共产会商品实体
	 */
	@Override
	public String updateProductEntity(String productJson) throws TException {
		try {
			Product pg = JsonUtils.String2Bean(productJson, Product.class);
			productBusiness.updateProductEntity(pg);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}


}
