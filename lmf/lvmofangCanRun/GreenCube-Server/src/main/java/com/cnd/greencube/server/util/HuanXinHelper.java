package com.cnd.greencube.server.util;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class HuanXinHelper {

	private static final String net = "https://a1.easemob.com/";
	private static final String APPKEY = "hengjiang#lvmofang";
	private static final String APP_CLIENT_ID = "YXA61skckPASEeWcx3XqpVzsPw";
	private static final String APP_CLIENT_SECRET = "YXA6a1oF1rHPpBj39q3ni53t5bcLTsk";

	private static final String orgName = "hengjiang";
	private static final String appName = "lvmofang";

	private static String token = "";// 需要存放在redis缓存中，同时还要求redis缓存的同步
	private static Date tokenTime = new Date();

	public static void main(String[] args) {
		System.out.println(1111111);
		System.out.println(getToken());
		ImRestUser("1234567890", "111111", "小白");
		addUser("123456789", "1234567890");
		System.out.println(deleteUser("123456789", "1234567890"));
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public static String ImRestUser(String userId, String pwd, String nickName) {
		String url = net + orgName + "/" + appName + "/users";
		JSONObject request = new JSONObject();
		request.put("username", userId);
		request.put("password", pwd);
		request.put("nickname", nickName);
		return HuanXinHttpUtil.doPost(url, request.toString());
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public static String getToken() {

		if (token != null && tokenTime.getTime() - new Date().getTime() % 1000 < 5184000) {
			return token;
		} else {
			tokenTime = new Date();
			JSONObject request = new JSONObject();
			request.put("grant_type", "client_credentials");
			request.put("client_id", APP_CLIENT_ID);
			request.put("client_secret", APP_CLIENT_SECRET);
			String url = net + orgName + "/" + appName + "/token";
			JSONObject j = JSONObject.parseObject(HuanXinHttpUtil.doPost(url, request.toString()));
			return j.getString("access_token");
		}
	}

	/***
	 * 群组删人
	 * 
	 * @param groupId
	 * @param usernames
	 * @return
	 */
	public static boolean GroupDeleteUser(String groupId, String user) {
		String url = net + orgName + "/" + appName + "/chatgroups/" + groupId + "/users/" + user;
		JSONObject request = new JSONObject();
		String result = HuanXinHttpUtil.easeMobDelete(url, request.toString(), getToken());
		JSONObject j = JSONObject.parseObject(result);
		j = j.getJSONObject("data");
		boolean falg = (Boolean) j.get("result");
		return falg;

	}

	/***
	 * 群组加人
	 * 
	 * @param groupId
	 * @param usernames
	 * @return
	 */
	public static String GroupaddUser(String groupId, String[] usernames) {
		String url = net + orgName + "/" + appName + "/chatgroups/" + groupId + "/users";
		JSONObject request = new JSONObject();
		request.put("usernames", usernames);
		return HuanXinHttpUtil.easeMobPost(url, request.toString(), getToken());

	}

	/***
	 * 加为好友
	 * 
	 * @param ownerUsername是要添加好友的用户名
	 * @param friendUsername是被添加的用户名
	 * @return
	 */
	public static String addUser(String ownerUsername, String friendUsername) {
		String url = net + orgName + "/" + appName + "/user/" + ownerUsername + "/contacts/user/" + friendUsername;
		JSONObject request = new JSONObject();
		return HuanXinHttpUtil.easeMobPost(url, request.toString(), getToken());
	}

	/**
	 * 删除好友
	 * 
	 * @param ownerUsername是要添加好友的用户名
	 * @param friendUsername是被添加的用户名
	 */
	public static String deleteUser(String ownerUsername, String friendUsername) {
		String url = net + orgName + "/" + appName + "/users/" + ownerUsername + "/contacts/users/" + friendUsername;
		JSONObject request = new JSONObject();
		return HuanXinHttpUtil.easeMobDelete(url, request.toString(), getToken());
	}
}
