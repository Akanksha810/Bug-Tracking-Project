//Author : Dharampreet
//Purpose : Exception for handling error in execution of code.

package com.code.exception;

public class ErrorInExecutionException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorInExecutionException (String mesg) {
		super(mesg);
	}
}