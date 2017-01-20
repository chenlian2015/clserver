package com.cnd.greencube.web.appserver.controller.ucenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 商品管理
 * @author cndini
 *
 */
@Controller("GoodsManagerController")
@RequestMapping("/ucenter/goods/manager")
public class GoodsManagerController extends BaseController {
	/**
	 * 店铺管理主界面
	 * @return
	 */
	@RequestMapping(value = "")
	public String index() {
		return "/ucenter/goods/manager/index";
	}
	
	/**
	 * 创建商品
	 * @return
	 */
	@RequestMapping(value = "/create")
	public String create() {
		return "/ucenter/goods/manager/create";
	}
}