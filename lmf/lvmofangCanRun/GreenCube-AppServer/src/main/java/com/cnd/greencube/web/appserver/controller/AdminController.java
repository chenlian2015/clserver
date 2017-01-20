package com.cnd.greencube.web.appserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnd.greencube.web.base.web.controller.BaseController;

@Controller("AdminController")
@RequestMapping("/admin")
public class AdminController  extends BaseController {
	/**
	 * 首页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String index() {
		return "/admin/index";
	}
}
