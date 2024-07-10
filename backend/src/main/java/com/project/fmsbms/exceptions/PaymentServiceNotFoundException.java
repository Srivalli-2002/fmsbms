package com.project.fmsbms.exceptions;

public class PaymentServiceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentServiceNotFoundException() {
		super();
	}

	public PaymentServiceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PaymentServiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentServiceNotFoundException(String message) {
		super(message);
	}

	public PaymentServiceNotFoundException(Throwable cause) {
		super(cause);
	}

}
