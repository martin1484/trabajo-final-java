package com.dary.control.exception;

public class BusinessExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessExcepion() {
		super();
	}

	public BusinessExcepion(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BusinessExcepion(String arg0) {
		super(arg0);
	}

	public BusinessExcepion(Throwable arg0) {
		super(arg0);
	}	

}
