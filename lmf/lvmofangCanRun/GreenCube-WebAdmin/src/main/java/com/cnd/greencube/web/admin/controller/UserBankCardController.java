package com.cnd.greencube.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnd.greencube.server.entity.UserBankCard;
import com.cnd.greencube.server.service.UserBankCardService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

@Controller("UserBankCardController")
@RequestMapping("/admin/userBankCard")
public class UserBankCardController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 加载所有的银行卡信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userBankCard_list")
	public String userBankCard_list() throws Exception {
		initPageInfo();
		
		String jsonObject = thriftTemplate.execute("UserBankCardService", new ThriftAction() {
			final String id = (String) getParameter("id");
			
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((UserBankCardService.Client) ps).loadUserBankCards(id);
			}
		});

		List<UserBankCard> userBankCard = Message.unpackList(jsonObject, UserBankCard.class);
		setParameter("userBankCard", userBankCard);
		
		return "/admin/userBankCard/userBankCard_list";
	}
	
}
