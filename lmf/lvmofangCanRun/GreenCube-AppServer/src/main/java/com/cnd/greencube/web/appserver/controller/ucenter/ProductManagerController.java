package com.cnd.greencube.web.appserver.controller.ucenter;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.service.ProductService;
import com.cnd.greencube.server.service.ShopGoodsService;
import com.cnd.greencube.server.service.ShopService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.util.TokenUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 产品管理
 * 
 * @author cndini
 * 
 */
@Controller("ProductManagerController")
@RequestMapping("/ucenter/product/manager")
public class ProductManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 商品列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/product_list")
	public String product_list() {
		return "/front/ucenter/productmanager/product_list";
	}

	/**
	 * 返回数据
	 */
	@RequestMapping(value = "/data_product_list", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String data_product_list() {
		// 判断当前点击了哪个标签，取得商品数据
		String label = (String) getParameter("label");
		label = StringUtils.isEmpty(label) ? "goods" : label;
		final String token = (String) getParameter("token");

		if ("goods".equals(label)) {
			// 查询商品
			final String shopId = (String) getParameter("shopId");
			if (!StringUtils.isEmpty(shopId)) {
				String isMyShop = thriftTemplate.execute("ShopService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						ShopService.Client shopGoodsClient = (ShopService.Client) client;
						String result = shopGoodsClient.isMyShop(token, shopId);
						return result;
					}
				});
				JSONObject jo = JsonUtils.String2JSONObject(isMyShop);

				if (jo.getBooleanValue("isMyShop")) {
					String goodsListJson = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
						@Override
						public String action(TServiceClient client) throws Exception {
							ShopGoodsService.Client shopGoodsClient = (ShopGoodsService.Client) client;
							String result = shopGoodsClient.loadShopGoodsByKeywords(shopId, null, getPageStart());
							return result;
						}
					});
					return goodsListJson;
				}
			} else {
				String goodsListJson = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						ShopGoodsService.Client shopGoodsClient = (ShopGoodsService.Client) client;
						String result = shopGoodsClient.loadMyShopGoods(token, getPageStart());
						return result;
					}
				});
				return goodsListJson;
			}

			return Message.error();
		} else if ("product".equals(label)) {
			// 查询产品
			try {
				final String userid = TokenUtils.isTokenRight(token);
				String productListJson = thriftTemplate.execute("ProductService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						ProductService.Client productClient = (ProductService.Client) client;
						String result = productClient.loadProductByKeywords(userid, null, getPageStart());
						return result;
					}
				});
				return productListJson;
			} catch (Exception e) {
				return Message.error();
			}
		}
		return Message.error();
	}

	/**
	 * 商品组装主界面数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/data_product_assemble", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String data_product_assemble() {
		final String goodsId = (String) getParameter("goodsId");

		String result = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				ShopGoodsService.Client shopGoodsClient = (ShopGoodsService.Client) client;
				String goodsJson = shopGoodsClient.getGoodsById(goodsId);
				String goodsItems = shopGoodsClient.getGoodsItems(goodsId);
				return Message.okMessage(new String [] {"goodsDetail", "items"}, new String [] {goodsJson, goodsItems} );
			}
		});

		return result;
	}

	/**
	 * 创建商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/create")
	public String create() {
		return "/ucenter/product/manager/create";
	}

	/**
	 * 店铺列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop_list")
	public String shop_list() {
		return "/ucenter/product/manager/shop_list";
	}

	/**
	 * 产品组装首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/product_assemble")
	public String product_assemble() {
		return "/ucenter/product/manager/product_assemble";
	}

	/**
	 * 添加产品元素
	 * 
	 * @return
	 */
	@RequestMapping(value = "/choice_list")
	public String choice_list() {
		return "/ucenter/product/manager/choice_list";
	}

	/**
	 * 添加增值服务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/services")
	public String services() {
		return "/ucenter/product/manager/services";
	}

	/**
	 * 填写产产品信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cost_accounting")
	public String cost_accounting() {
		return "/ucenter/product/manager/cost_accounting";
	}

	/**
	 * 按产品类型(1-产品，2-服务，3-其他),产品分类返回组装产品信息
	 */
	@RequestMapping(value = "/data_product_assemble_list", produces = "plain/text;charset=UTF-8")
	public @ResponseBody
	String data_product_assemble_list() {
		// 判断当前点击了哪个产品类型
		final int product_type = getParameterInt("product_type");
		final int page = getParameterInt("page");
		String keyWord = "";
		if ("1".equals(product_type)) {
			keyWord = "1";
		} else if ("2".equals(product_type)) {
			keyWord = "2";
		} else if ("3".equals(product_type)) {
			keyWord = "3";

			final String categoryId = (String) getParameter("categoryId");
			String productListJson = thriftTemplate.execute("ProductService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					ProductService.Client productServiceClient = (ProductService.Client) client;
					String result = productServiceClient.loadProductByKeywordsandCategory("1", categoryId, page);
					return result;
				}
			});
			return productListJson;
		}
		return Message.error();
	}

	/**
	 * 返回组装产品信息保存到商品信息表
	 */
	@RequestMapping(value = "/save_product_assemble_list", produces = "plain/text;charset=UTF-8")
	public @ResponseBody
	String save_product_assemble_list() {
		ShopGoods shopGoods = new ShopGoods();
		shopGoods.setName((String) getParameter("name"));
		shopGoods.setDesc((String) getParameter("desc"));
		shopGoods.setLocLon((String) getParameter("locLon"));
		shopGoods.setLocLat((String) getParameter("locLat"));
		shopGoods.setBeginDate((Date) getParameter("beginDate"));
		shopGoods.setEndDate((Date) getParameter("endDate"));
		shopGoods.setPicMain((String) getParameter("picMain"));
		final String shopGoodsJson = JsonUtils.bean2String(shopGoods);// 产品组装中填写的产品信息
		final String shopGoodsItemJson = (String) getParameter("shopGoodsItem");// 产品组装中添加的组装产品集合

		String saveProductListJson = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				ShopGoodsService.Client shopGoodsService = (ShopGoodsService.Client) client;
				return shopGoodsService.addShopGoodsEntity(shopGoodsJson, shopGoodsItemJson);
			}
		});
		return saveProductListJson;
	}
}