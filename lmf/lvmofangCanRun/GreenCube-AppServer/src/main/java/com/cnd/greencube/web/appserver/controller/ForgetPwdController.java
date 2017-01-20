package com.cnd.greencube.web.appserver.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.User;
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
@Controller("ForgetPwdController")
@RequestMapping("/forgetpwd")
public class ForgetPwdController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	@RequestMapping(value = "", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
	String appRegist() {
		try {
			final String mobile = (String) getParameter("mobile");
			final String verifyCode = (String) getParameter("verifycode");
			final String newpwd = (String) getParameter("newpwd");

			if (StringUtils.isEmptyAfterTrim(mobile))
				return Message.errorMessage("手机号码不能为空");

			if (StringUtils.isEmptyAfterTrim(newpwd))
				return Message.errorMessage("密码不能为空");

			if (StringUtils.isEmptyAfterTrim(verifyCode))
				return Message.errorMessage("验证码不能为空");

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
			
			String result = thriftTemplate.execute("UserService", new ThriftAction() {
				@Override
				public String action(TServiceClient userService) throws Exception {
					UserService.Client client = ((UserService.Client) userService);

					String existUser = client.getUserByMobile(mobile);

					User user = Message.unpackObject(existUser, User.class);
					if (user == null) {
						return Message.errorMessage("手机号码有误，请重新输入！");
					}
					return Message.okMessage();
				}
			});
			
			JSONObject jo = JsonUtils.String2JSONObject(result);
			if (jo.getBooleanValue("success") == false) {
				return result;
			}
			
			result = thriftTemplate.execute("UserService", new ThriftAction() {
				@Override
				public String action(TServiceClient userService) throws Exception {
					return ((UserService.Client) userService).forgetPwd(mobile, newpwd);
				}
			});

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Message.errorMessage("服务器繁忙，请稍后再试");
		}
	}
}
