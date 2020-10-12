//Author : Akanksha
//Purpose : User class, parent of ProjectManager, Tester and Developer, contains details about all users.

package com.code.bean;

public class User {
		
	//data members
	private int userId;
	private String name;
	private String email;
	private String type;
	
	//default constructor
	public User() {
		super();
		userId = 0;
		name = null;
		email = null;
		type = null;
	}


	//parameterized constructor
	public User(int userId, String name, String email, String type) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.type = type;
	}


	//setter and getter methods 
	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// @Override
	// public String toString() {
	// 	return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", type=" + type + "]";
	// }
	
	
}
