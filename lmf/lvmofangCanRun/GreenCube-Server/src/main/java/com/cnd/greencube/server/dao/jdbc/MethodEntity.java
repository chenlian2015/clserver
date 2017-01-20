/*
 * Copyright 2005-2020 GreenTube Team All rights reserved.
 * Support: Huxg
 * License: CND team license
 */
package com.cnd.greencube.server.dao.jdbc;

import java.util.ArrayList;

/**
 * 方法实体
 * @author huxg
 *
 */
public class MethodEntity {
	// 方法名称

	private String methodName;

	// 重载方法个数

	private int repeatMethodNum = 1;

	// 方法参数类型列表

	@SuppressWarnings("rawtypes")
	private Class[] methodParamTypes;

	// 存放重载方法参数
	@SuppressWarnings("rawtypes")
	private ArrayList repeatMethodsParamTypes;

	/**
	 * 
	 * 获取参数名称
	 * 
	 * 
	 * 
	 * @return
	 */

	public String getMethodName() {

		return methodName;

	}

	/**
	 * 
	 * 获取方法参数类型列表
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public Class[] getMethodParamTypes() {

		return methodParamTypes;

	}

	/**
	 * 
	 * 设置参数名称
	 * 
	 * 
	 * 
	 * @param string
	 */

	public void setMethodName(String string) {

		methodName = string;

	}

	/**
	 * 
	 * 设置参数类型列表
	 * 
	 * 
	 * 
	 * @param classes
	 */

	@SuppressWarnings("rawtypes")
	public void setMethodParamTypes(Class[] classes) {
		methodParamTypes = classes;

	}

	/**
	 * 
	 * 获取重载方法个数
	 * 
	 * 
	 * 
	 * @return
	 */

	public int getRepeatMethodNum() {

		return repeatMethodNum;

	}

	/**
	 * 
	 * 获取第i个重载方法参数列表
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public Class[] getRepeatMethodsParamTypes(int i) {

		int count = this.repeatMethodsParamTypes.size();

		if (i <= count) {

			return (Class[]) this.repeatMethodsParamTypes.get(i);

		} else {

			throw new ArrayIndexOutOfBoundsException();

		}

	}

	/**
	 * 
	 * 设置重载方法个数
	 * 
	 * 
	 * 
	 * @param i
	 */

	public void setRepeatMethodNum(int i) {

		repeatMethodNum = i;

	}

	/**
	 * 
	 * 设置重载方法参数类型
	 * 
	 * 
	 * 
	 * @param list
	 */

	@SuppressWarnings("rawtypes")
	public void setRepeatMethodsParamTypes(ArrayList list) {
		repeatMethodsParamTypes = list;

	}

	/**
	 * 
	 * 获取重载方法类型
	 * 
	 * 
	 * 
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public ArrayList getRepeatMethodsParamTypes() {
		return repeatMethodsParamTypes;

	}

	/**
	 * 
	 * 设置重载方法参数类型列表
	 * 
	 * 
	 * 
	 * @param paramTypes
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRepeatMethodsParamTypes(Class[] paramTypes) {
		if (this.repeatMethodsParamTypes == null)
			this.repeatMethodsParamTypes = new ArrayList();
		repeatMethodsParamTypes.add(paramTypes);
	}

}
