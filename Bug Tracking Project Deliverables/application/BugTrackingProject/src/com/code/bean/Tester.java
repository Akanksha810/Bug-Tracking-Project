//Author : Akanksha
//Purpose : Tester class, child of User, for details about tester.(Can be removed)

package com.code.bean;

public class Tester extends User{

	//default constructor
	public Tester() {
		
		super();
	}
	
	//parameterized constructor
	public Tester(int userId, String userName, String email, String typeOfUser) {
		
		super(userId, userName, email, typeOfUser);
	}

	// //Overriding toString method
	// @Override
	// public String toString() {
		
	// 	return super.toString();
	// }
}
