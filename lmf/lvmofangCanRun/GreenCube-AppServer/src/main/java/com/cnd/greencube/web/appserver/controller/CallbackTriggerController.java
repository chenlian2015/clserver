package com.cnd.greencube.web.appserver.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.service.PaymentService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 回调处理
 * 
 * @author huxg
 * 
 */
@Controller("CallbackTriggerController")
@RequestMapping("/cbtrigger")
public class CallbackTriggerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 处理支付成功请求
	 * 
	 * @param paymentId
	 * @return
	 */
	@RequestMapping(value = "/payment/success/{paymentId}")
	public @ResponseBody
	String paymentSuccess(@PathVariable final String paymentId) {
		try {
			return thriftTemplate.execute("PaymentService", new ThriftAction() {
				@Override
				public String action(TServiceClient paymentService) throws Exception {
					return ((PaymentService.Client) paymentService).finishPayment(paymentId);
				}
			});
		} catch (Exception e) {
			return Message.okMessage();
		}
	}

	/**
	 * 处理取消支付请求
	 * 
	 * @param paymentId
	 * @return
	 */
	@RequestMapping(value = "/payment/cancel/{paymentId}")
	public @ResponseBody
	String paymentCancel(@PathVariable final String paymentId) {
		try {
			return thriftTemplate.execute("PaymentService", new ThriftAction() {
				@Override
				public String action(TServiceClient paymentService) throws Exception {
					return ((PaymentService.Client) paymentService).cancelPayment(paymentId);
				}
			});
		} catch (Exception e) {
			return Message.okMessage();
		}
	}
}
