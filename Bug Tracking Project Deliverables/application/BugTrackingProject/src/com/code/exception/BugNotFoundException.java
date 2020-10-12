//Author : Hrishita
//Purpose : Exception when a bug is not found.
package com.code.exception;

public class BugNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public BugNotFoundException(String mesg) {
		super(mesg);
	}
}