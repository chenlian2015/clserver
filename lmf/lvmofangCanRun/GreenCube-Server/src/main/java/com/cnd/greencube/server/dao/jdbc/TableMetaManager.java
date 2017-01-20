/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

/**
 * 表结构管理器
 * 
 * @author Huxg
 * 
 */
public class TableMetaManager {
	private static TableMetaManager instance;
	private Map<String, Table> mp;

	public static TableMetaManager getInstance() {
		if (instance == null) {
			instance = new TableMetaManager();
			instance.init();
		}
		return instance;
	}

	private void init() {
		mp = new HashMap<String, Table>();
	}

	public void addMeta(Table t) {
		mp.put(t.getTableName(), t);
	}
	
	public Table getTable(String tableName){
		return mp.get(tableName);
	}
}
