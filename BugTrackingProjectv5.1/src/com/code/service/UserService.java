// Authors : Hrushikesh, Ankit
// Purpose : Interface for service layer 
package com.code.service;

import com.code.bean.User;
import com.code.exception.InvalidUserException;

public interface UserService {

	//for validating the user
	User validateUser(String userName, String password) throws InvalidUserException ;

}
