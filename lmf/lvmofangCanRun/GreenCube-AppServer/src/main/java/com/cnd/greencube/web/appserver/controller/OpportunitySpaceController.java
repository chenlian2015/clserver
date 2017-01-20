package com.cnd.greencube.web.appserver.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.service.OpportunitySpaceService;
import com.cnd.greencube.server.service.RequirementService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;


/**
 * 机会空间管理
 * @author dongyali
 *
 */
@Controller("OpportunitySpaceController")
@RequestMapping("/ucenter/partnershipSpace")
public class OpportunitySpaceController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;
	/**
	 * 机会空间
	 * 
	 * @return
	 */
	@RequestMapping(value = "/opportunity_space")
	public String opportunity_space() {
		return "/ucenter/partnershipSpace/opportunity_space";
	}
	/**
	 * 返回主类别数据
	 */
	@RequestMapping(value = "/main_category", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String main_category() {
		String mainJson = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				OpportunitySpaceService.Client opportunitySpaceService = (OpportunitySpaceService.Client) client;
				String result = opportunitySpaceService.loadOpportunitySpaceCategorys();
				return result;
			}
		});
		return mainJson;
	}
	/**
	 * 返回推荐类别数据
	 */
	@RequestMapping(value = "/recommend_category", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String recommend_category() {
		String recommendJson = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				OpportunitySpaceService.Client opportunitySpaceService = (OpportunitySpaceService.Client) client;
				String result = opportunitySpaceService.loadRecommendCategorys();
				return result;
			}
		});
		return recommendJson;
	}
	/**
	 * 返回推荐类别数量
	 */
	@RequestMapping(value = "/recommend_category_count", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String recommend_category_count(final String categoryId) {
		// categoryId = "4aea47a653a2c7bc0153a2cdca680003";
		String recommendJson = thriftTemplate.execute("RequirementService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				RequirementService.Client opportunitySpaceService = (RequirementService.Client) client;
				String result = opportunitySpaceService.getRequirementCount(categoryId);
				return result;
			}
		});
		return recommendJson;
	}
}
