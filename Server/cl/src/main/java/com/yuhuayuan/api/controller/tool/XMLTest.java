package com.yuhuayuan.api.controller.tool;

import org.json.XML;

import com.alibaba.fastjson.JSONObject;

public class XMLTest {

	public static String DealRequest(String request)
	{
		org.json.JSONObject joROOT = XML.toJSONObject(request);
		org.json.JSONObject joXML = (org.json.JSONObject) joROOT.get("xml");
		String FromUserName = (String)joXML.get("ToUserName");
		String ToUserName = (String)joXML.get("FromUserName");
		String CreateTime = ""+System.currentTimeMillis();
		String template = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><FromUserName><![CDATA[%2$s]]></FromUserName><CreateTime>%3$s</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[%4$s]]></MediaId></Image></xml>";
		
		String result = String.format(template, ToUserName, FromUserName, CreateTime, "2cjk8UDYVvkl1sZLSmbqoFCkf0hxAT8uscn_fCpDzLxP0S2mjWwCGCykuXlpmko9");
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlData = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		DealRequest(xmlData);
	}
}
