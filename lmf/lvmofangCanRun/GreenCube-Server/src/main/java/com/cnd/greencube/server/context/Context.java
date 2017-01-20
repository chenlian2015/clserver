/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.context;

import java.io.Serializable;

/**
 * 上下文环境类
 * @author huxg
 *
 */
public class Context implements Serializable{
	private static final long serialVersionUID = 4964467123192295987L;
	private String  dataSourceName=null;
	private boolean isRead=false;
	public Context(boolean isRead,String dataSourceName) {
		super();
		this.isRead=isRead;
		this.dataSourceName = dataSourceName;
	}
	public Context(boolean isRead){
		super();
		this.isRead=isRead;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
}
