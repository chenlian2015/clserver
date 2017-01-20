package com.cnd.greencube.web.appserver.registory;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TServiceClient;

import com.cnd.greencube.web.base.filter.parameter.ParameterWrapper;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.Classes;
import com.cnd.greencube.web.base.util.SpringUtils;
import com.cnd.greencube.web.base.vo.Message;

/**
 * 服务管理类，负责管理每一个服务的接口参数
 * 
 * @author 胡晓光
 * 
 */
public class ServiceRegistory {
	Map<String, Parameters> p = new HashMap<String, Parameters>();
	static ServiceRegistory instance = null;

	private ServiceRegistory() {

	}

	public static ServiceRegistory getInstance() {
		if (null == instance) {
			instance = new ServiceRegistory();
		}
		return instance;
	}

	public String execute(final String serviceName, final String methodName) throws Exception {
		ThriftClientTemplate thriftTemplate = (ThriftClientTemplate) SpringUtils.getBean("ThriftClientTemplate");

		String result = thriftTemplate.execute(serviceName, new ThriftAction() {
			@Override
			public String action(TServiceClient client) {
				Parameters parameter = p.get(serviceName + "." + methodName);

				try {
					if (parameter == null) {
						parameter = findMethod(client, serviceName, methodName);
					}

					if (parameter == null) {
						return null;
					}

					Method m = parameter.getMethod();
					Object[] parameters = makeParameters(parameter.getParameterNames(), parameter.getParameterTypes());
					Object result = m.invoke(client, parameters);
					if (result == null)
						return Message.error();

					return result.toString();
				} catch (Exception e) {
					e.printStackTrace();
					return Message.errorMessage("服务器异常");
				}
			}
		});
		return result;
	}

	Parameters findMethod(Object obj, String serviceName, String methodName) throws Exception {
		if (obj != null) {
			Method[] methods = obj.getClass().getDeclaredMethods();
			Method thisMethod = null;
			for (Method m : methods) {
				if (m.getName().equals(methodName)) {
					thisMethod = m;
					break;
				}
			}
			String[] parameterNames = Classes.getMethodParamNames(obj.getClass(), methodName);
			Class<?>[] types = thisMethod.getParameterTypes();

			Parameters p = new Parameters();
			p.setMethod(thisMethod);
			p.setParameterNames(parameterNames);
			p.setParameterTypes(types);
			this.p.put(serviceName + "." + methodName, p);
			return p;
		}
		return null;
	}

	Object[] makeParameters(String[] parameterNames, Class<?>[] types) {
		String paramName;
		Class<?> clazz;
		List<Object> parameters = new ArrayList<Object>();
		for (int i = 0; i < types.length; i++) {
			paramName = parameterNames[i];
			clazz = types[i];
			parameters.add(getParameter(paramName, clazz));
		}
		return parameters.toArray();
	}

	Object getParameter(String name, Class<?> clazz) {
		// 对ip地址进行处理
		if (name.equals("__remoteAddr")) {
			String ip = ParameterWrapper.getWrapper().getRequest().getRemoteAddr();
			return ip;
		}
		Object obj = ParameterWrapper.getWrapper().get(name);
		if (clazz.isPrimitive()) {
			if ("int".equals(clazz.getName())) {
				if (obj == null)
					return 0;
				String str = obj.toString();
				if (str.startsWith("0x")) {
					return Integer.decode(obj.toString());
				} else {
					return Integer.parseInt(obj.toString());
				}
			} else if ("long".equals(clazz.getName())) {
				if (obj == null)
					return 0;
				return Long.parseLong(obj.toString());
			} else if ("float".equals(clazz.getName())) {
				if (obj == null)
					return 0;
				return Float.parseFloat(obj.toString());
			} else if ("double".equals(clazz.getName())) {
				if (obj == null)
					return 0;
				return Double.parseDouble(obj.toString());
			} else if ("boolean".equals(clazz.getName())) {
				if (obj == null)
					return false;
				return Boolean.parseBoolean(obj.toString());
			}
		} else if (clazz == String.class) {
			return obj == null ? "" : obj.toString();
		} else if (clazz == Integer.class) {
			return obj == null ? null : Integer.valueOf(obj.toString());
		} else if (clazz == Boolean.class) {
			return obj == null ? false : Boolean.valueOf(obj.toString());
		} else if (clazz == Long.class) {
			return obj == null ? null : Long.valueOf(obj.toString());
		} else if (clazz == BigInteger.class) {
			return obj == null ? null : new BigInteger(obj.toString());
		} else if (clazz == Float.class) {
			return obj == null ? null : Float.valueOf(obj.toString());
		} else if (clazz == Double.class) {
			return obj == null ? null : Double.valueOf(obj.toString());
		}
		return null;

	}
}
