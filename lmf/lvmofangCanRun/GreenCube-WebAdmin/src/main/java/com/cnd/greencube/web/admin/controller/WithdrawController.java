package com.cnd.greencube.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.entity.FiWithdraw;
import com.cnd.greencube.server.service.WithdrawService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

@Controller("WithdrawController")
@RequestMapping("/admin/withdraw")
public class WithdrawController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 类别列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/withdraw_list")
	public String withdraw_list() throws Exception {
		initPageInfo();

		String jsonObject = thriftTemplate.execute("WithdrawService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((WithdrawService.Client) ps).loadWithdraw(0);
			}
		});

		List<FiWithdraw> withdraws = Message.unpackList(jsonObject, FiWithdraw.class);
		setParameter("withdraws", withdraws);
		
//		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
//		JSONArray ja = jo.getJSONArray("data");
//		setParameter("withdraw", ja);
		
		return "/admin/withdraw/withdraw_list";
	}

	
	/**
	 * 拒绝该申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/withdraw_reject")
	public @ResponseBody String withdraw_reject() throws Exception {
		final String id = (String) getParameter("id");
		
		return thriftTemplate.execute("WithdrawService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((WithdrawService.Client) ps).rejectWithdraw(id);
			}
		});
	}
	
	/**
	 * 通过该申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/withdraw_approve")
	public @ResponseBody String withdraw_approve() throws Exception {
		final String id = (String) getParameter("id");
		final String status = (String) getParameter("status");
		
		return thriftTemplate.execute("WithdrawService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((WithdrawService.Client) ps).approveWithdraw(id,status);
			}
		});
	}
}
