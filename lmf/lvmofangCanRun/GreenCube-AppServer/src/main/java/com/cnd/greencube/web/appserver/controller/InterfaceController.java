package com.cnd.greencube.web.appserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.web.appserver.vo.Inter;
import com.cnd.greencube.web.appserver.vo.Parameter;
import com.cnd.greencube.web.appserver.vo.Service;
import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.filter.parameter.ParameterWrapper;
import com.cnd.greencube.web.base.util.HttpUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.util.dom4j.XmlUtils;
import com.cnd.greencube.web.base.util.uuid.Key;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;
import com.cnd.greencube.web.base.web.servlet.SysLoaderServlet;

@Controller("InterfaceController")
@RequestMapping("/admin/intf")
public class InterfaceController extends BaseController {
	List<Service> services = new ArrayList<Service>();

	/**
	 * 接口列表界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		if (services == null || services.size() == 0) {
			doReload();
		}

		String selectedService = (String) getParameter("service");
		if (!StringUtils.isEmpty(selectedService)) {
			List<Service> selectedServices = new ArrayList<Service>();
			for (Service s : services) {
				if (s.getName().equals(selectedService)) {
					selectedServices.add(s);
				}
			}
			setParameter("services", selectedServices);
			setParameter("allServices", services);
		} else {
			setParameter("services", services);
			setParameter("allServices", services);
		}

		return "/admin/intf/list";
	}

	@RequestMapping(value = "/reload")
	public @ResponseBody
	String reload() {
		doReload();
		return Message.okMessage();
	}

	/**
	 * 装载接口文件信息
	 */
	@SuppressWarnings("rawtypes")
	void doReload() {
		services.clear();
		String infXmlLocation = SysLoaderServlet.getRealPath() + "/WEB-INF/classes/intf.xml";

		XmlUtils xml = new XmlUtils();
		try {
			Document doc = xml.load(new FileInputStream(new File(infXmlLocation)));
			Iterator iter = doc.getRootElement().elementIterator();
			while (iter.hasNext()) {
				Element elService = (Element) iter.next();
				Service service = parseService(elService);
				if (service == null)
					continue;
				services.add(service);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析service
	 * 
	 * @param elService
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Service parseService(Element elService) {
		Service service = new Service();
		service.setDesc(elService.attributeValue("desc"));
		service.setName(elService.attributeValue("name"));

		List<Inter> iters = new ArrayList<Inter>();
		Iterator iter = elService.elementIterator();
		while (iter.hasNext()) {
			Element elIter = (Element) iter.next();
			Inter i = parseInterface(elIter);
			iters.add(i);
		}
		service.setInterfaces(iters);
		return service;
	}

	@SuppressWarnings("rawtypes")
	Inter parseInterface(Element elInter) {
		Inter i = new Inter();
		i.setId(Key.key());
		i.setDesc(elInter.attributeValue("desc"));
		i.setMethod(elInter.attributeValue("method"));
		i.setUrl(elInter.attributeValue("url"));

		List<Parameter> parameters = new ArrayList<Parameter>();
		Iterator iter = elInter.element("parameters").elementIterator();
		while (iter.hasNext()) {
			Element elIter = (Element) iter.next();
			Parameter parameter = parseParameter(elIter);
			parameters.add(parameter);
		}
		i.setParameters(parameters);

		Element elReturnValue = elInter.element("returnVal");
		String returnValue = parseReturnValue(elReturnValue);
		i.setReturnValue(returnValue);

		Element elmemo = elInter.element("memo");
		String strMemo = elmemo.getData().toString();
		i.setMemo(strMemo);

		return i;
	}

	Parameter parseParameter(Element elParameter) {
		Parameter p = new Parameter();
		p.setDesc(elParameter.attributeValue("desc"));
		p.setName(elParameter.attributeValue("name"));
		p.setType(elParameter.attributeValue("string"));
		p.setIsRequired(elParameter.attributeValue("isRequired"));
		p.setDefaultValue(elParameter.attributeValue("defaultValue"));
		return p;
	}

	String parseReturnValue(Element elReturnValue) {
		return elReturnValue.getData().toString();
	}

	/**
	 * 接口详情界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail() {
		String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			Inter it = getInterById(id);
			setParameter("inter", it);
		}
		return "/admin/intf/detail";
	}

	Inter getInterById(String id) {
		Inter it = null;
		for (Service s : services) {
			List<Inter> iterfaces = s.getInterfaces();
			Iterator<Inter> iter = iterfaces.iterator();

			while (iter.hasNext()) {
				Inter i = iter.next();
				if (id.equals(i.getId())) {
					it = i;
					break;
				}
			}
		}
		return it;
	}

	/**
	 * 执行接口界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/execute")
	public @ResponseBody
	String execute() {
		try {
			// 创建一个Http链接，并提交请求至服务器
			String id = (String) getParameter("id");
			if (!StringUtils.isEmpty(id)) {
				Inter iter = getInterById(id);

				String rootUrl = SysConfiguration.getProperty("site.url");
				String url = rootUrl + iter.getUrl();

				if (iter.getMethod().equalsIgnoreCase("post")) {
					// post请求
					Map<String, String> params = makeParameter();
					try {
						JSONObject jo = HttpUtils.post4JsonObject(url, params);
						return jo.toString();
					} catch (Exception e) {
						e.printStackTrace();
						return Message.okMessage(e.getMessage());
					}
				} else {
					// get请求
					// post请求
					Map<String, String> params = makeParameter();
					try {
						JSONObject jo = HttpUtils.get4JsonObject(url, params);
						return jo.toString();
					} catch (Exception e) {
						e.printStackTrace();
						return Message.okMessage(e.getMessage());
					}
				}
			}
			return Message.errorMessage("接口有误，无法完成调用");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.errorMessage(e.getMessage());
		}

	}

	Map<String, String> makeParameter() {
		HttpServletRequest req = ParameterWrapper.getWrapper().getRequest();
		Map<String, String> params = new HashMap<String, String>();

		Enumeration<?> e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if ("id".equals(key))
				continue;
			String value = req.getParameter(key);
			params.put(key, value);
		}

		return params;
	}
}
