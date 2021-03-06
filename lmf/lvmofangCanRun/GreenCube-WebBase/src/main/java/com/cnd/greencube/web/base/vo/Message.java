package com.cnd.greencube.web.base.vo;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.web.base.PageInfo;
import com.cnd.greencube.web.base.util.JsonUtils;

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

	public static String okMessage(String[] keys, String[] values) {
		Message m = new Message();
		m.setSuccess(true);

		JSONObject js = new JSONObject();
		if (keys != null) {
			for (int i = 0; i < keys.length; i++) {
				js.put(keys[i], values[i]);
			}
		}

		m.messageObj.put("data", js);

		return m.toString();
	}

	public static String errorMessage(String message) {
		Message m = new Message();
		m.setSuccess(false);
		m.setMessage(message);
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

	public static <T> List<T> unpackList(String str, Class<T> clazz) {
		JSONObject jo = JsonUtils.String2JSONObject(str);
		if (jo.getBooleanValue("success") == false) {
			return null;
		}

		Object o = jo.get("data");
		if (o instanceof JSONArray) {
			JSONArray ja = jo.getJSONArray("data");
			List<T> result = (List<T>) JsonUtils.String2List(ja.toString(), clazz);
			return result;
		}
		return null;
	}

	public static <T> T unpackObject(String str, Class<T> clazz) {
		JSONObject jo = JsonUtils.String2JSONObject(str);
		if (jo.getBooleanValue("success") == false) {
			return null;
		}

		Object o = jo.get("data");
		if (o instanceof JSONObject) {
			JSONObject obj = jo.getJSONObject("data");
			T result = JsonUtils.String2Bean(obj.toString(), clazz);
			return result;
		}
		return null;
	}
}