//Author : Akanksha
//Purpose : Exception when the user is not found.

package com.code.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String mesg) {
		super(mesg);
	}
}
