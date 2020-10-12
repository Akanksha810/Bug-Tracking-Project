//Author : Akanksha
//Purpose : Developer class, child of User, to store data about developers.(Can be removed)

package com.code.bean;

public class Developer extends User{

	//default constructor
	public Developer() {
		
		super();
	}

	//parameterized constructor
	public Developer(int userId, String userName, String email, String typeOfUser) {
		
		super(userId, userName, email, typeOfUser);
	}

	// //Overriding to String method
	// @Override
	// public String toString() {
		
	// 	return super.toString();
	// }
}
