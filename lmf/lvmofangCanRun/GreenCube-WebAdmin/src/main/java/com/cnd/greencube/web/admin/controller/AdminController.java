package com.cnd.greencube.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.web.controller.BaseController;

@Controller("AdminController")
@RequestMapping("/admin")
public class AdminController extends BaseController {

	/**
	 * 首页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String index() {
		String parentId = (String) getParameter("id");
		if (!StringUtils.isEmptyAfterTrim(parentId)) {
			setParameter("parentId", parentId);
		}
		setParameter("content", "content");
		return "/admin/index";
	}

	/**
	 * 统计分析结果
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stat")
	public String stat() {
		return "/admin/stat";
	}

	/**
	 * 初始化导航
	 */
	public void initNavigator() {

	}

	@RequestMapping(value = "/editPwd")
	public String editPwd() {
		return "/admin/editPwd";
	}
}
