package com.cnd.greencube.server.business.impl.payment.transaction;

import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

public class TransactionFactory implements ITransactionFactory {
	static TransactionFactory instance;

	private TransactionFactory() {

	}

	public static TransactionFactory getFactory() {
		if (null == instance)
			instance = new TransactionFactory();
		return instance;
	}

	@Override
	public ITransaction getTransaction(String transactionType) throws Exception {
		String[] p = transactionType.split("_");
		String str = "";
		for (String s : p) {
			str += StringUtils.upperCaseTheFirstChar(s);
		}
		str += "Transaction";
		ITransaction tran = (ITransaction) SpringUtils.getBean(str);
		return tran;
	}
}
