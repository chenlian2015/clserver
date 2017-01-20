package com.cnd.greencube.server.business.impl.payment.transaction;

public interface ITransactionFactory {
	ITransaction getTransaction(String transactionType) throws Exception;
}
