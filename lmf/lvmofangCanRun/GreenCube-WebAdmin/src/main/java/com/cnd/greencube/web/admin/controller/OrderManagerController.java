package com.cnd.greencube.web.admin.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.service.OrderService;
import com.cnd.greencube.server.service.ShopGoodsService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 订单管理
 * 
 * @author dongyali
 * 
 */
@Controller("OrderManagerController")
@RequestMapping("/admin/order")
public class OrderManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 供产会商品申请列表处理
	 */
	@RequestMapping(value = "/order_query_list")
	public String order_query_list() throws Exception {
		final String orderNo = (String) getParameter("orderNo");
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("OrderService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((OrderService.Client) ps).searchOrdersByOrderNum(orderNo, start);
			}
		});

		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("orders", ja);
		return "/admin/order/order_query_list";
	}

	/**
	 * 订单中店主会商品列表（一级）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order_shopgoods_list")
	public @ResponseBody String order_shopgoods_list() throws Exception {
		// 取得当前商品id
		final String orderId = (String) getParameter("orderId");

		return thriftTemplate.execute("OrderService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((OrderService.Client) ps).loadOrderItemsByOrderId(orderId);
			}
		});

	}

	/**
	 * 订单中该店主会的组合商品列表（二级）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order_shopgoodsItem_list")
	public @ResponseBody String order_shopgoodsItem_list() throws Exception {
		// 取得当前商品id
		final String goodsId = (String) getParameter("goodsId");

		return thriftTemplate.execute("ShopGoodsService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((ShopGoodsService.Client) ps).getGoodsItems(goodsId);
			}
		});

	}

}
