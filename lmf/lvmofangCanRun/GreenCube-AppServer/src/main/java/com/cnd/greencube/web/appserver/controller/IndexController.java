package com.cnd.greencube.web.appserver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 主页
 * @author huxg
 *
 */
@Controller("IndexController")
@RequestMapping("/")
public class IndexController  extends BaseController {
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
