// Authors : Hrushikesh, Ankit
// Purpose : Interface for database layer (dao layer)
package com.code.dao;

//import java.sql.SQLException;

import com.code.bean.User;
import com.code.exception.InvalidUserException;

public interface UserDao {

	//function to validate user
	User validateUser(String userName, String password) throws InvalidUserException;

}
