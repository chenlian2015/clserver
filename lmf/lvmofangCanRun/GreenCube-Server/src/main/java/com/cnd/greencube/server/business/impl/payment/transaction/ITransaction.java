package com.cnd.greencube.server.business.impl.payment.transaction;


/**
 * 交易接口
 * 
 * @author huxg
 * 
 */
public interface ITransaction {
	/**
	 * 交易之前操作
	 * @param params
	 */
	void beforeDeal(Object ... params );

	/**
	 * 完成交易操作
	 * @param params
	 */
	void afterDeal(Object ...params);
	
	/**
	 * 取消操作
	 * @param params
	 */
	void cancelDeal(Object ...params);
}
