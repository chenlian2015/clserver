package com.cnd.greencube.server.protocal;

import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 反馈消息类 JSON方式
 * 
 * @author huxg
 * 
 */
public class Message {
	boolean success = false;
	JSONObject messageObj;

	public Message() {
		this.messageObj = new JSONObject();
	}

	public Message setMessage(String message) {
		messageObj.put("message", message);
		return this;
	}

	public Message setData(Collection<Object> data) {
		messageObj.put("data", data);
		return this;
	}

	public Message setData(Object data) {
		messageObj.put("data", data);
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public Message setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public static String okMessage() {
		Message m = new Message();
		m.setSuccess(true);
		return m.toString();
	}

	public static String okMessage(Object obj) {
		Message m = new Message();
		m.setSuccess(true);

		if (obj instanceof Collection) {
			m.setData((Collection) obj);
		} else {
			m.setData(obj);
		}

		return m.toString();
	}

	public static String okMessage(String[] keys, Object[] objs) {
		Message m = new Message();
		m.setSuccess(true);

		JSONObject jo = new JSONObject();
		for (int i = 0; i < keys.length; i++) {
			jo.put(keys[i], objs[i]);
		}
		m.setData(jo);
		return m.toString();
	}

	public static String okMessage(Object obj, PageInfo pageInfo) {
		Message m = new Message();
		m.setSuccess(true);

		if (obj instanceof Collection) {
			m.setData((Collection) obj);
		} else {
			m.setData(obj);
		}
		m.messageObj.put("page", pageInfo);
		return m.toString();
	}

	public static String errorMessage(String message) {
		Message m = new Message();
		m.setSuccess(false);
		m.setMessage(message);
		return m.toString();
	}

	public static String errorMessage(String[] keys, Object[] objs) {
		Message m = new Message();
		m.setSuccess(false);

		JSONObject jo = new JSONObject();
		for (int i = 0; i < keys.length; i++) {
			jo.put(keys[i], objs[i]);
		}
		m.setData(jo);
		return m.toString();
	}

	public static String error() {
		Message m = new Message();
		m.setSuccess(false);
		return m.toString();
	}

	@Override
	public String toString() {
		JSONObject jo = null;
		if (messageObj == null) {
			return JSON.toJSONString(this);
		} else {
			jo = this.messageObj;
			jo.put("success", this.success);
			return jo.toJSONString();
		}
	}
}
