package com.cnd.greencube.web.appserver.vo;

import java.util.ArrayList;
import java.util.List;

public class Service {
	String name;
	String desc;
	List<Inter> interfaces = new ArrayList<Inter>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Inter> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Inter> interfaces) {
		this.interfaces = interfaces;
	}

}
