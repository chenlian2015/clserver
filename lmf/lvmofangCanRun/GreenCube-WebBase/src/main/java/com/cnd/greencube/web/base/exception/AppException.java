package com.cnd.greencube.web.base.exception;

public class AppException extends Exception {
	private static final long serialVersionUID = 0;

	private Status status = null;

	public AppException(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return this.status;
	}
}