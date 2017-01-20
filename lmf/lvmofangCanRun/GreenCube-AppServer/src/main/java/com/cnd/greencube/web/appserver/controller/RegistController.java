package com.cnd.greencube.web.appserver.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.service.RegistService;
import com.cnd.greencube.server.service.UserService;
import com.cnd.greencube.web.base.controller.CommonController;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.SpringUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 注册控制器
 * 
 * @author huxg
 * 
 */
@Controller("RegistController")
@RequestMapping("/regist")
public class RegistController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	@RequestMapping(value = "/appRegist", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
	String appRegist() {
		try {
			final String mobile = (String) getParameter("mobile");
			final String verifyCode = (String) getParameter("verifyCode");
			final String imei = (String) getParameter("imei");
			final String password = (String) getParameter("password");

			if (StringUtils.isEmptyAfterTrim(password))
				return Message.errorMessage("密码不能为空");

			if (StringUtils.isEmptyAfterTrim(mobile))
				return Message.errorMessage("手机号码不能为空");

			if (StringUtils.isEmptyAfterTrim(imei))
				return Message.errorMessage("手机串号不能为空");

			String result = thriftTemplate.execute("UserService", new ThriftAction() {
				@Override
				public String action(TServiceClient userService) throws Exception {
					UserService.Client client = ((UserService.Client) userService);

					String existUser = client.getUserByMobile(mobile);

					User user = Message.unpackObject(existUser, User.class);
					if (user != null) {
						return Message.errorMessage("该手机号已注册，不能重复注册");
					}

					existUser = client.getUserByUserName(mobile);
					user = Message.unpackObject(existUser, User.class);
					if (user != null) {
						return Message.errorMessage("该手机号已注册，不能重复注册");
					}
					return Message.okMessage();
				}
			});

			JSONObject jo = JsonUtils.String2JSONObject(result);
			if (jo.getBooleanValue("success") == false) {
				return result;
			}

			// 检查手机校验码是否正确
			// 检查手机校验码
			if (!StringUtils.isEmpty(verifyCode)) {
				CommonController ws = (CommonController) SpringUtils.getBean("CommonController");
				try {
					ws.checkMobileVerifyCode(mobile, verifyCode);
				} catch (Exception e) {
					return Message.errorMessage("手机验证码有误，请重新发送");
				}
			}

			result = thriftTemplate.execute("RegistService", new ThriftAction() {
				@Override
				public String action(TServiceClient registService) throws Exception {
					return ((RegistService.Client) registService).appRegist(mobile, verifyCode, imei, password);
				}
			});

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Message.errorMessage("服务器繁忙，请稍后再试");
		}
	}
}
