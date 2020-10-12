//Author : Ankit, Akshay
//Purpose : Interface for registration.
package com.code.dao;

import com.code.exception.UserNotFoundException;

public interface RegisterDao {

	//function to add a new user to the database
	boolean storePassword(String pass,String email, int userid) throws UserNotFoundException;

	//function to check whether the email and role match acc. to the imported user
	int emailExistValidation(String email, String role) throws UserNotFoundException;
	
}
