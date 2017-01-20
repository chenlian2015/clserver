package com.cnd.greencube.web.admin.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.service.FinanceService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 财务管理
 * 
 * @author dongyali
 * 
 */
@Controller("FinanceManagerController")
@RequestMapping("/admin/finance")
public class FinanceManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 共产会会费统计
	 */
	@RequestMapping(value = "/provider_memberfi_stat")
	public String provider_memberfi_stat() throws Exception {
		final String startTime = (String) getParameter("startTime");
		final String endTime = (String) getParameter("endTime");
		String jsonObject = thriftTemplate.execute("FinanceService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((FinanceService.Client) ps).loadProviderMemberfiStat(startTime, endTime);
			}
		});

		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		String strData = jo.getString("data");
		JSONArray ja = JsonUtils.String2JSONArray(strData);
		setParameter("finance", ja);
		return "/admin/finance/provider_memberfi_stat";
	}

	/**
	 * 共产会管理费统计
	 */
	@RequestMapping(value = "/provider_managerfi_stat")
	public String provider_managerfi_stat() throws Exception {
		final String startTime = (String) getParameter("startTime");
		final String endTime = (String) getParameter("endTime");
		String jsonObject = thriftTemplate.execute("FinanceService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((FinanceService.Client) ps).loadProviderManagerfiStat(startTime, endTime);
			}
		});

		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		String strData = jo.getString("data");
		JSONArray ja = JsonUtils.String2JSONArray(strData);
		setParameter("finance", ja);
		return "/admin/finance/provider_managerfi_stat";
	}

	/**
	 * 店主会会费统计
	 */
	@RequestMapping(value = "/shop_memberfi_stat")
	public String shop_memberfi_stat() throws Exception {
		final String startTime = (String) getParameter("startTime");
		final String endTime = (String) getParameter("endTime");
		String jsonObject = thriftTemplate.execute("FinanceService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((FinanceService.Client) ps).loadShopMemberfiStat(startTime, endTime);
			}
		});

		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		String strData = jo.getString("data");
		JSONArray ja = JsonUtils.String2JSONArray(strData);
		setParameter("finance", ja);
		return "/admin/finance/shop_memberfi_stat";
	}

	/**
	 * 店主会管理费统计
	 */
	@RequestMapping(value = "/shop_managerfi_stat")
	public String shop_managerfi_stat() throws Exception {
		final String startTime = (String) getParameter("startTime");
		final String endTime = (String) getParameter("endTime");
		String jsonObject = thriftTemplate.execute("FinanceService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((FinanceService.Client) ps).loadShopManagerfiStat(startTime, endTime);
			}
		});

		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		String strData = jo.getString("data");
		JSONArray ja = JsonUtils.String2JSONArray(strData);
		setParameter("finance", ja);
		return "/admin/finance/shop_managerfi_stat";
	}

	/**
	 * 健康会交易统计
	 */
	@RequestMapping(value = "/healthy_trade_stat")
	public String healthy_trade_stat() throws Exception {
		return "/admin/finance/healthy_trade_stat";
	}

	/**
	 * 健康会交易条目
	 */
	@RequestMapping(value = "/healthy_trade_detail")
	public String healthy_trade_detail() throws Exception {
		return "/admin/finance/healthy_trade_detail";
	}

	/**
	 * 注册用户交易统计
	 */
	@RequestMapping(value = "/user_trade_stat")
	public String user_trade_stat() throws Exception {
		return "/admin/finance/user_trade_stat";
	}

	/**
	 * yellow 注册用户交易条目
	 */
	@RequestMapping(value = "/user_trade_detail")
	public String user_trade_detail() throws Exception {
		return "/admin/finance/user_trade_detail";
	}

	/**
	 * 平台統計-交易收支明細
	 */
	@RequestMapping(value = "/trade_income_expend")
	public String trade_income_expend() throws Exception {
		return "/admin/finance/trade_income_expend";
	}

	/**
	 * 会费統計-会费缴纳概览
	 */
	@RequestMapping(value = "/memberfi_list")
	public String memberfi_list() throws Exception {
		return "/admin/finance/memberfi_list";
	}

	/**
	 * 会费統計-催缴共产会
	 */
	@RequestMapping(value = "/reminder_provider")
	public String reminder_provider() throws Exception {
		return "/admin/finance/reminder_provider";
	}

	/**
	 * 会费統計-催缴店主会
	 */
	@RequestMapping(value = "/reminder_shop")
	public String reminder_shop() throws Exception {
		return "/admin/finance/reminder_shop";
	}

	/**
	 * 提现管理-提现申请列表
	 */
	@RequestMapping(value = "/withdraw_applied_list")
	public String withdraw_applied_list() throws Exception {
		return "/admin/finance/withdraw_applied_list";
	}

	/**
	 * 提现管理-提现申请记录
	 */
	@RequestMapping(value = "/withdraw_applied_record")
	public String withdraw_applied_record() throws Exception {
		return "/admin/finance/withdraw_applied_record";
	}

	/**
	 * 退款管理-退款申请列表
	 */
	@RequestMapping(value = "/refund_applied_list")
	public String refund_applied_list() throws Exception {
		return "/admin/finance/refund_applied_list";
	}

	/**
	 * 退款管理-退款申请记录
	 */
	@RequestMapping(value = "/refund_applied_record")
	public String refund_applied_record() throws Exception {
		return "/admin/finance/refund_applied_record";
	}

	/**
	 * 发票管理-发票申请列表
	 */
	@RequestMapping(value = "/invoice_applied_list")
	public String invoice_applied_list() throws Exception {
		return "/admin/finance/invoice_applied_list";
	}

	/**
	 * 发票管理-发票备案
	 */
	@RequestMapping(value = "/invoice_record_register")
	public String invoice_record_register() throws Exception {
		return "/admin/finance/invoice_record_register";
	}

}
