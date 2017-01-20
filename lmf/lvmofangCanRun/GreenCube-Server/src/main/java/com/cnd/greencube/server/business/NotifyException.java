package com.cnd.greencube.server.business;

/**
 * 通知异常
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("serial")
public class NotifyException extends RuntimeException {
	String message;

	public NotifyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
