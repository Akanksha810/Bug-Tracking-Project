//Author : Ankit, Akshay
//Purpose : Interface for business logic for registration.
package com.code.service;

import com.code.exception.UserNotFoundException;

public interface RegisterService {


	//for validation of email
	int validateEmail(String email, String role) throws UserNotFoundException;

	//for creating new password
	boolean createPassword(String pass,String email,int userid) throws UserNotFoundException;

}
