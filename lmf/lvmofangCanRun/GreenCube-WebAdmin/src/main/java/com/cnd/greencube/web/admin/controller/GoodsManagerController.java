package com.cnd.greencube.web.admin.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.Product;
import com.cnd.greencube.server.entity.ShopGoods;
import com.cnd.greencube.server.entity.ShopGoodsItem;
import com.cnd.greencube.server.service.MemberService;
import com.cnd.greencube.server.service.ProductService;
import com.cnd.greencube.server.service.ShopGoodsService;
import com.cnd.greencube.server.service.ShopService;
import com.cnd.greencube.web.base.filter.parameter.ParameterWrapper;
import com.cnd.greencube.web.base.security.UserAuthentication;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 商品管理
 * 
 * @author dongyali
 * 
 */
@Controller("GoodsManagerController")
@RequestMapping("/admin/goods")
public class GoodsManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 供产会商品申请列表处理
	 */
	@RequestMapping(value = "/provider_goods_applied_list")
	public String provider_goods_applied_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("ProductService", new ThriftAction() {
			@Override
			public String action(TServiceClient cms) throws Exception {
				return ((ProductService.Client) cms).loadAppliedProductForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("goods", ja);
		return "/admin/goods/provider_goods_applied_list";
	}

	/**
	 * 供产会商品执行审核操作
	 */
	@RequestMapping(value = "/provider_goods_audit")
	public @ResponseBody String provider_goods_audit() throws Exception {
		// 取得当前登录用户
		final JSONObject currentLoginUser = UserAuthentication
				.getLoginUserInfo(ParameterWrapper.getWrapper().getRequest());
		if (currentLoginUser != null) {
			final String goodsId = (String) getParameter("goodsId");
			int auditStatus = getParameter("auditStatus") == null ? 0 : getParameterInt("auditStatus");

			if (auditStatus == 1) {
				return thriftTemplate.execute("ProductService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((ProductService.Client) ps).approveProduct(goodsId, currentLoginUser.getString("id"),
								currentLoginUser.getString("name"));
					}
				});
			} else {
				return thriftTemplate.execute("ProductService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((ProductService.Client) ps).rejectProduct(goodsId, currentLoginUser.getString("id"),
								currentLoginUser.getString("name"));
					}
				});
			}
		} else {
			return Message.errorMessage("很抱歉，您还未登录，不能进行审核！");
		}
	}

	// provider_goods_detail
	/**
	 * 供产会商品执行详情操作
	 */
	@RequestMapping(value = "/provider_goods_detail")
	public String provider_goods_detail() throws Exception {
		// 取得当前商品id
		final String goodsId = (String) getParameter("goodsId");

		String jsonBaseInfo = thriftTemplate.execute("ProductService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ProductService.Client) ps).getProductById(goodsId);
			}
		});

		// 基本信息
		Product providerGoods = Message.unpackObject(jsonBaseInfo, Product.class);
		setParameter("baseinfo", providerGoods);

		return "/admin/goods/provider_goods_detail";
	}

	/**
	 * 共产会仓库概览
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/provider_warehouse_brower")
	public String provider_warehouse_brower() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("MemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((MemberService.Client) ps).loadMembers(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/goods/provider_warehouse_brower";
	}

	/**
	 * 共产会依据共产会成员查询商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/provider_member_goods")
	public @ResponseBody String provider_member_goods() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");
		// 取得当前商品id
		final String memberId = (String) getParameter("memberId");

		return thriftTemplate.execute("ProductService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ProductService.Client) ps).loadProductByUserId(memberId, start);
			}
		});
	}

	/**
	 * 店主会商品申请列表处理
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shop_goods_applied_list")
	public String shop_goods_applied_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).loadAppliedShopGoodsForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("goods", ja);
		return "/admin/goods/shop_goods_applied_list";
	}

	/**
	 * 店主会商品执行审核操作
	 */
	@RequestMapping(value = "/shop_goods_audit")
	public @ResponseBody String shop_goods_audit() throws Exception {
		// 取得当前登录用户
		final JSONObject currentLoginUser = UserAuthentication
				.getLoginUserInfo(ParameterWrapper.getWrapper().getRequest());
		if (currentLoginUser != null) {
			final String goodsId = (String) getParameter("goodsId");
			int auditStatus = getParameter("auditStatus") == null ? 0 : getParameterInt("auditStatus");

			if (auditStatus == 1) {
				return thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((ShopGoodsService.Client) ps).approveShopGoods(goodsId,
								currentLoginUser.getString("id"), currentLoginUser.getString("name"));
					}
				});

			} else {
				return thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((ShopGoodsService.Client) ps).rejectShopGoods(goodsId, currentLoginUser.getString("id"),
								currentLoginUser.getString("name"));
					}
				});
			}
		} else {
			return Message.errorMessage("很抱歉，您还未登录，不能进行审核！");
		}
	}

	/**
	 * 店主会商品主表执行详情操作
	 */
	@RequestMapping(value = "/shop_goods_detail")
	public String shop_goods_detail() throws Exception {
		// 取得当前商品id
		final String goodsId = (String) getParameter("goodsId");

		String jsonBaseInfo = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).getGoodsById(goodsId);
			}
		});

		// 基本信息
		ShopGoods shopGoods = Message.unpackObject(jsonBaseInfo, ShopGoods.class);
		setParameter("baseinfo", shopGoods);
		return "/admin/goods/shop_goods_detail";
	}

	/**
	 * 店主会商品对应供应商明细展开
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shop_goodsitem_list")
	public @ResponseBody String shop_goodsitem_list() throws Exception {
		// 取得当前商品id
		final String goodsId = (String) getParameter("goodsId");

		return thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).getGoodsItems(goodsId);
			}
		});
	}

	/**
	 * 店主会商品条目表执行详情操作
	 */
	@RequestMapping(value = "/shop_goodsitem_detail")
	public String shop_goodsitem_detail() throws Exception {
		// 取得当前商品对应供应商商品 id
		final String goodsitemId = (String) getParameter("goodsitemId");

		String jsonBaseInfo = thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).getGoodsItemById(goodsitemId);
			}
		});

		// 基本信息
		ShopGoodsItem shopGoodsItem = Message.unpackObject(jsonBaseInfo, ShopGoodsItem.class);
		setParameter("GoodsItem", shopGoodsItem);
		return "/admin/goods/shop_goodsitem_detail";
	}

	/**
	 * 店主会仓库概览
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shop_warehouse_brower")
	public String shop_warehouse_brower() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("ShopService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopService.Client) ps).getApprovedShopsForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/goods/shop_warehouse_brower";
	}

	/**
	 * 店主会依据店主会成员查询商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shop_member_goods")
	public @ResponseBody String shop_member_goods() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");
		// 取得当前商品id
		final String memberId = (String) getParameter("memberId");

		return thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).loadGoodsByMemberId(memberId, start);
			}
		});

	}
}
