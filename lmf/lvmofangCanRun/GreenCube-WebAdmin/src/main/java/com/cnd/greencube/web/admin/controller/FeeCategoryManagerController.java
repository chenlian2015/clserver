package com.cnd.greencube.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.entity.FiMemberFeeCategory;
import com.cnd.greencube.server.entity.FiPaymentMethod;
import com.cnd.greencube.server.service.MemberFeeCategoryService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 费用分类管理控制器
 * 
 * @author huxg
 * 
 */
@Controller("FeeCategoryManagerController")
@RequestMapping("/admin/fee_category")
public class FeeCategoryManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 费用分类列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_list")
	public String category_list() {
		String result = thriftTemplate.execute("MemberFeeCategoryService", new ThriftAction() {
			@Override
			public String action(TServiceClient cms) throws Exception {
				return ((MemberFeeCategoryService.Client) cms).loadFeeCategory(null);
			}
		});
		List<FiMemberFeeCategory> categories = Message.unpackList(result, FiMemberFeeCategory.class);
		setParameter("categories", categories);
		return "/admin/fee_category/category_list";
	}

	/**
	 * 编辑一个费用分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_edit")
	public String category_edit() {
		final String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			String result = thriftTemplate.execute("MemberFeeCategoryService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((MemberFeeCategoryService.Client) ps).getFeeCategory(id);
				}
			});

			FiMemberFeeCategory category = Message.unpackObject(result, FiMemberFeeCategory.class);
			category.setFee(category.getFee());
			setParameter("m", category);
		}

		return "/admin/fee_category/category_edit";
	}

	/**
	 * 更新费用分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_update")
	public @ResponseBody
	String category_update() {
		try {
			final String id = (String) getParameter("id");

			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("MemberFeeCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						MemberFeeCategoryService.Client categoryService = (MemberFeeCategoryService.Client) ps;
						String result = categoryService.getFeeCategory(id);
						FiMemberFeeCategory category = Message.unpackObject(result, FiMemberFeeCategory.class);
						registerProperty("m", category);
						
						category.setFee(category.getFee());
						
						return categoryService.updateFeeCategory(JsonUtils.bean2String(category));
					}
				});
			} else {
				registerProperty("m", FiPaymentMethod.class);
				FiPaymentMethod method = (FiPaymentMethod) getParameter("m");
				method.setOrder(99);
				method.setEnable(1);

				return thriftTemplate.execute("MemberFeeCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						MemberFeeCategoryService.Client categoryService = (MemberFeeCategoryService.Client) ps;
						registerProperty("m", FiMemberFeeCategory.class);
						FiMemberFeeCategory category = (FiMemberFeeCategory) getParameter("m");
						category.setFee(category.getFee());
						return categoryService.createFeeCategory(JsonUtils.bean2String(category));
					}
				});
			}
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 删除一个费用分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_delete")
	public @ResponseBody
	String category_delete() {
		final String id = (String) getParameter("id");
		try {
			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("MemberFeeCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						MemberFeeCategoryService.Client categoryService = (MemberFeeCategoryService.Client) ps;
						return categoryService.deleteFeeCategory(id);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}
}
