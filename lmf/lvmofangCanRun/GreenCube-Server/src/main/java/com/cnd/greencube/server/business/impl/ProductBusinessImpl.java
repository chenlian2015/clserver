/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.api.MailAPI;
import com.cnd.greencube.server.business.ProductBusiness;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.AuditDao;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.CommentDao;
import com.cnd.greencube.server.dao.ProductDao;
import com.cnd.greencube.server.dao.UserDao;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.plugin.sms.ISMSProvider;
import com.cnd.greencube.server.util.PageInfo;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 供产会商品服务实现类
 * 
 * @author huxg
 * @author dongyali 加注释
 */
@SuppressWarnings("rawtypes")
@Service("ProductBusinessImpl")
public class ProductBusinessImpl extends BaseBusinessImpl implements ProductBusiness {
	@Resource(name = "ProductDaoImpl")
	ProductDao productDao;

	@Resource(name = "AreaDaoImpl")
	AreaDao areaDao;

	@Resource(name = "AuditDaoImpl")
	AuditDao auditDao;

	@Resource(name = "UserDaoImpl")
	UserDao userDao;

	@Resource(name = "CodeHealthyCategoryDaoImpl")
	CodeHealthyCategoryDao codeHealthyCategoryDao;

	@Resource(name = "CommentDaoImpl")
	CommentDao commentDao;

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
	@Override
	public List<Product> loadProductByKeywordsAndCategory(String keyword, String categoryId, PageInfo pageInfo) {
		String sql = "select g.id from Product g";
		String ctql = "select count(g) from Product g";
		List<Object> params = new ArrayList<Object>();

		if (!StringUtils.isEmpty(keyword)) {
			sql += " where name like ?";
			ctql += " where name like ?";
			params.add("%" + keyword + "%");

			if (!StringUtils.isEmpty(categoryId)) {
				sql += " and categoryId = ?";
				ctql += " and categoryId = ?";
				params.add(categoryId);
			}
		} else {
			if (!StringUtils.isEmpty(categoryId)) {
				sql += " where categoryId = ?";
				ctql += " where categoryId = ?";
				params.add(categoryId);
			}
		}

		return productDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 按照关键字分页搜索共产会商品列表
	 * 
	 * @param userId
	 *            共产会id
	 * @param keyword
	 *            关键字
	 * @param pageNum
	 *            起始数
	 */
	@Override
	public List<Product> loadProductByKeywords(String userId, String keyword, PageInfo pageInfo) {
		String sql = "select g.id from Product g";
		String ctql = "select count(g) from Product g";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(userId)) {
			sql += " where userId = ?";
			ctql += " where userId = ?";
			params.add(userId);

			if (!StringUtils.isEmpty(keyword)) {
				sql += " and name like ?";
				ctql += " and name like ?";
				params.add("%" + keyword + "%");
			}
		} else {
			if (!StringUtils.isEmpty(keyword)) {
				sql += " where name like ?";
				ctql += " where name like ?";
				params.add("%" + keyword + "%");
			}
		}

		return productDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

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
	@Override
	public List<Product> loadProductByCategoryId(String userId, String categoryId, PageInfo pageInfo) {
		String sql = "select g.id from Product g where  statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		String ctql = "select count(g) from Product g where  statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(userId)) {
			sql += " and userId = ?";
			ctql += " and userId = ?";
			params.add(userId);
		}
		if (!StringUtils.isEmpty(categoryId)) {
			sql += " and categoryId = ?";
			ctql += " and categoryId = ?";
			params.add(categoryId);
		}
		return productDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 显示前n条数据
	 * 
	 * @param userId
	 *            共产会id
	 * @param number
	 *            前n条
	 */
	@Override
	public List<Product> loadProductTopN(String userId, int number) {
		String sql = "select g.id from Product g where userId = ? and statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		return productDao.findList(sql, params.toArray(), 1, number);
	}

	/**
	 * 依据相关条件查询共产会商品列表
	 * 
	 * @param userId
	 * @param categoryId
	 * @param provinceId
	 * @param cityId
	 * @param keyword
	 * @param pageNum
	 * 
	 */
	@Override
	public List<Product> searchProductForPagelit(String userId, String categoryId, String provinceId, String cityId, String keyword, PageInfo pageInfo)
			throws TException {
		String sql = "select g.id from Product g where statusShelf = 1 and statusAudit = 1 and statusDelete = 0";
		String ctql = "select count(g) from Product g where statusShelf = 1 and statusAudit = 1 and statusDelete = 0";

		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(userId)) {
			sql += " and userId = ?";
			ctql += " and userId = ?";
			params.add(userId);
		}

		if (!StringUtils.isEmpty(categoryId)) {
			sql += " and categoryId = ?";
			ctql += " and categoryId = ?";
			params.add(categoryId);
		}

		if (!StringUtils.isEmpty(provinceId)) {
			sql += " and provinceId = ?";
			ctql += " and provinceId = ?";
			params.add(provinceId);
		}

		if (!StringUtils.isEmpty(cityId)) {
			sql += " and cityId = ?";
			ctql += " and cityId = ?";
			params.add(cityId);
		}

		if (!StringUtils.isEmpty(keyword)) {
			sql += " and name like ?";
			ctql += " and name like ?";
			params.add("%" + keyword + "%");
		}
		return productDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 得到热点商品
	 */
	@Override
	public List<Product> getHotProduct(int count) {
		return productDao.getHotProducts(count);
	}

	/**
	 * 得到商品依据id
	 */
	@Override
	public Product getProductById(String id) {
		return productDao.get(id);
	}

	/**
	 * 修改商品
	 */
	@Transactional
	@Override
	public boolean updateProduct(Product goods) {
		if (StringUtils.isEmpty(goods.getId())) {
			// 商品分类id
			if (!StringUtils.isEmpty(goods.getCategoryId())) {
				// 设置商品分类
				String[] p = goods.getCategoryId().split(",");
				String categoryName = "";
				for (String s : p) {
					CodeHealthyCategory c = codeHealthyCategoryDao.get(s);
					if (c != null) {
						categoryName = categoryName.length() == 0 ? c.getName() : categoryName + "," + c.getName();
					}
				}

				// 所在省
				if (!StringUtils.isEmpty(goods.getProvinceId()) && StringUtils.isEmpty(goods.getProvinceName())) {
					Area area = areaDao.get(goods.getProvinceId());
					goods.setProvinceName(area.getName());
				}

				// 城市名称
				if (!StringUtils.isEmpty(goods.getCityId()) && StringUtils.isEmpty(goods.getCityName())) {
					Area area = areaDao.get(goods.getCityId());
					goods.setCityName(area.getName());
				}

				goods.setCreateTime(new Date());
				goods.setSalesCount(0);
				goods.setStatusShelf(0);
				goods.setStatusAudit(0);
				goods.setStatusDelete(0);
			}
			productDao.save(goods);
		} else {
			goods.setStatusAudit(0);
			productDao.update(goods);
		}
		return true;
	}

	/**
	 * 删除
	 */
	@Transactional
	@Override
	public boolean deleteProductById(String goodsId) {
		productDao.delete(goodsId);
		return true;
	}

	/**
	 * 商品上架
	 */
	@Transactional
	@Override
	public boolean putShelf(String goodsId) {
		Product goods = productDao.get(goodsId);
		goods.setStatusShelf(1);
		productDao.update(goods);
		return true;
	}

	/**
	 * 商品下架
	 */
	@Transactional
	@Override
	public boolean offShelf(String goodsId) {
		Product goods = productDao.get(goodsId);
		goods.setStatusShelf(0);
		productDao.update(goods);
		return true;
	}

	/**
	 * 未审核的共产会商品
	 */
	@Override
	public List<Product> loadAppliedProductForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Product t where t.statusAudit = 0";
		String ctql = "select count(t) from Product t where t.statusAudit = 0";
		return productDao.findList(sql, ctql, null, pageInfo);
	}

	/**
	 * 已通过审核的共产会商品
	 */
	@Override
	public List<Product> loadApprovedProductForPagelit(PageInfo pageInfo) {
		String sql = "select t.id from Product t where t.statusAudit = 1";
		String ctql = "select count(t) from Product t where t.statusAudit = 1";
		return productDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean approveProduct(String goodsId, String auditUserId, String auditUserName) {
		Product goods = productDao.get(goodsId);
		goods.setStatusAudit(1);
		productDao.update(goods);

		auditDao.audit(goods.getId(), Product.class, auditUserId, 1);
		User user = userDao.get(auditUserId);

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】共产会店铺商品申请已获批准";
			String content = "恭喜您 " + user.getName() + "先生/小姐，您的店铺商品（" + goods.getName() + "）申请已被通过，您现在可以进入“绿魔方”管理您的商品了！";
			MailAPI.sendMail(user.getEmail(), title, content, false);

			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean rejectProduct(String goodsId, String auditUserId, String auditUserName) {
		Product goods = productDao.get(goodsId);
		goods.setStatusAudit(-1);
		productDao.update(goods);

		auditDao.audit(goods.getId(), Product.class, auditUserId, 1);
		User user = userDao.get(auditUserId);

		try {
			// 发送邮件、短信通知
			String title = "【绿魔方】共产会店铺商品申请已被驳回";
			String content = "很遗憾 " + user.getName() + "先生/小姐，您的店铺商品（" + goods.getName() + "）申请已被驳回，请重新申请或联系客服！";
			MailAPI.sendMail(user.getEmail(), title, content, false);

			// 发送短信通知
			ISMSProvider sms = (ISMSProvider) SpringUtils.getBean("SMSProvider");
			sms.sendMessage(user.getMobile(), content);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Product> loadProductByUserId(String userId, PageInfo pageInfo) {

		String sql = "select g.id from Product g where userId = ?";
		String ctql = "select count(g) from Product g where userId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		return productDao.findList(sql, ctql, params.toArray(), pageInfo);
	}

	/**
	 * 添加供产会商品
	 * 
	 * @param pg
	 * @return
	 */
	@Override
	public boolean addProductEntity(Product pg) {
		productDao.save(pg);
		return true;
	}

	/**
	 * 添加供产会商品
	 * 
	 * @param pg
	 * @return
	 */
	@Override
	public boolean updateProductEntity(Product pg) {
		productDao.update(pg);
		return true;
	}


}
