//Author : Akanksha
//Purpose : Project Manager class, child class for User, to store details about project managers.(Can be removed)

package com.code.bean;

public class ProjectManager extends User{

	//default constructor
	public ProjectManager() {
		
		super();
	}

	//parameterized constructor
	public ProjectManager(int userId, String userName, String email, String typeOfUser) {
		
		super(userId, userName, email, typeOfUser);
	}

	// //Overriding toString method
	// @Override
	// public String toString() {
	// 	return super.toString();
	// }	
}
