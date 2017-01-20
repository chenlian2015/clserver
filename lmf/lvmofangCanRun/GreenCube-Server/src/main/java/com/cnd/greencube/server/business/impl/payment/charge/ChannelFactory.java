package com.cnd.greencube.server.business.impl.payment.charge;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.cnd.greencube.server.util.StringUtils;

/**
 * 渠道工厂类
 * 
 * @author huxg
 * 
 */
public class ChannelFactory implements IChannelFactory {
	static ChannelFactory instance;

	private ChannelFactory() {

	}

	public static ChannelFactory getFactory() {
		if (null == instance)
			instance = new ChannelFactory();
		return instance;
	}

	Map<String, Class<?>> cache = new HashMap<String, Class<?>>();

	@Override
	public IChannel createChargeChannel(String indefier, String paymentId, int amount, String clientIp, String subject, String body, String description)
			throws Exception {
		Class<?> channelClass = getChargeChannel(indefier);

		Class<?>[] aclassParam = new Class[] { String.class, String.class, int.class, String.class, String.class, String.class, String.class };
		Constructor<?> con = channelClass.getConstructor(aclassParam);

		IChannel c = (IChannel) con.newInstance(indefier, paymentId, amount, clientIp, subject, body, description);
		return c;
	}

	Class<?> getChargeChannel(String indefier) throws Exception {
		Class<?> c = cache.get(indefier);
		if (c == null) {
			String[] p = indefier.split("_");
			String str = "";
			for (String s : p) {
				str += StringUtils.upperCaseTheFirstChar(s);
			}
			str += "Channel";
			try {
				c = Class.forName(this.getClass().getPackage().getName() + "." + str);
			} catch (Exception e) {
				throw new Exception("Unsupported Payment Charge:" + indefier);
			}
			cache.put(indefier, c);
		}
		return c;
	}
}
