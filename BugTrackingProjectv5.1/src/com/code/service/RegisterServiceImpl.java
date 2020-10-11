//Author : Ankit, Akshay
//Purpose : Provides business logic for registration.
package com.code.service;

import com.code.dao.RegisterDao;
import com.code.dao.RegisterDaoImpl;
import com.code.exception.UserNotFoundException;

public class RegisterServiceImpl implements RegisterService{

	RegisterDao ob=new RegisterDaoImpl();

	//Function to create a new password
	@Override
	public boolean createPassword(String pass,String email,int userid) throws UserNotFoundException {
		return ob.storePassword(pass,email,userid);
	}

	//Function to validate the email
	@Override
	public int validateEmail(String email, String role) throws UserNotFoundException {
		return ob.emailExistValidation(email,role);
	}
	
}
