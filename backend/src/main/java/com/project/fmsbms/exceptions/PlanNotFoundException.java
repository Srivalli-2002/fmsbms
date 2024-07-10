package com.project.fmsbms.exceptions;

public class PlanNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PlanNotFoundException() {
		super();
	}

	public PlanNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PlanNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlanNotFoundException(String message) {
		super(message);
	}

	public PlanNotFoundException(Throwable cause) {
		super(cause);
	}

}	
