package com.cnd.greencube.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 * @author huxg
 *
 */
@Controller("IndexController")
@RequestMapping("/")
public class IndexController {
	/**
	 * 首页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(ModelMap model, HttpServletRequest request) {
		return "redirect:/admin";
	}
}
