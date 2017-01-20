package com.cnd.greencube.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.entity.FiPaymentMethod;
import com.cnd.greencube.server.service.PaymentService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.util.encrypt.Encryption;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 支付方式管理控制器
 * 
 * @author TaoJijia
 */
@Controller("PaymentMethodController")
@RequestMapping("/admin/payment/method")
public class PaymentMethodController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 取得支付方式列表
	 */
	@RequestMapping(value = "/method_list")
	public String method_list() throws Exception {
		String result = thriftTemplate.execute("PaymentService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((PaymentService.Client) ps).loadAvaliablePaymentMethod(null);
			}
		});

		List<FiPaymentMethod> methods = Message.unpackList(result, FiPaymentMethod.class);

		setParameter("methods", methods);
		return "/admin/payment/method/method_list";
	}

	/**
	 * 编辑一种支付方式
	 */
	@RequestMapping(value = "/method_edit")
	public String method_edit() throws Exception {

		final String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			String result = thriftTemplate.execute("PaymentService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((PaymentService.Client) ps).getPaymentMethod(id);
				}
			});

			FiPaymentMethod method = Message.unpackObject(result, FiPaymentMethod.class);
			setParameter("m", method);
		}
		return "/admin/payment/method/method_edit";
	}

	/**
	 * 更新一种支付方式
	 */
	@RequestMapping(value = "/method_update")
	public @ResponseBody
	String method_update() throws Exception {
		try {
			final String id = (String) getParameter("id");

			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("PaymentService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						PaymentService.Client paymentService = (PaymentService.Client) ps;
						String result = paymentService.getPaymentMethod(id);
						FiPaymentMethod method = Message.unpackObject(result, FiPaymentMethod.class);
						registerProperty("m", method);

						return paymentService.updatePaymentMethod(JsonUtils.bean2String(method));
					}
				});
			} else {
				registerProperty("m", FiPaymentMethod.class);
				FiPaymentMethod method = (FiPaymentMethod) getParameter("m");
				final String methodStr = JsonUtils.bean2String(method);
				return thriftTemplate.execute("PaymentService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						PaymentService.Client paymentService = (PaymentService.Client) ps;
						return paymentService.savePaymentMethod(methodStr);
					}
				});
			}
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 删除支付方式
	 */
	@RequestMapping(value = "/method_delete")
	public @ResponseBody
	String method_delete() throws Exception {
		final String id = (String) getParameter("id");
		try {
			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("PaymentService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						PaymentService.Client paymentService = (PaymentService.Client) ps;
						return paymentService.deletePaymentMethod(id);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 启用支付方式
	 */
	@RequestMapping(value = "/method_enable")
	public @ResponseBody
	String method_enable() throws Exception {
		final String id = (String) getParameter("id");
		try {
			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("PaymentService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						PaymentService.Client paymentService = (PaymentService.Client) ps;
						return paymentService.enablePaymentMethod(id);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 禁用支付方式
	 */
	@RequestMapping(value = "/method_disable")
	public @ResponseBody
	String method_disable() throws Exception {
		final String id = (String) getParameter("id");
		try {

			if (!StringUtils.isEmpty(id)) {
				return thriftTemplate.execute("PaymentService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						PaymentService.Client paymentService = (PaymentService.Client) ps;
						return paymentService.disablePaymentMethod(id);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Encryption.encodeMD5("6bcM3x5J"));
	}
}
