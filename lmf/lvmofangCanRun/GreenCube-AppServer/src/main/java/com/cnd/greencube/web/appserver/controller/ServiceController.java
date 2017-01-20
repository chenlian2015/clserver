package com.cnd.greencube.web.appserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.web.appserver.registory.ServiceRegistory;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 服务控制器
 * @author huxg
 *
 */
@Controller("ServiceController")
@RequestMapping("/service")
public class ServiceController extends BaseController {
	/**
	 * 接收客户端并处理客户端的服务请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{serviceName}/{methodName}", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String index(@PathVariable String serviceName, @PathVariable String methodName) {
		try {
			return ServiceRegistory.getInstance().execute(serviceName, methodName);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}
	
}
