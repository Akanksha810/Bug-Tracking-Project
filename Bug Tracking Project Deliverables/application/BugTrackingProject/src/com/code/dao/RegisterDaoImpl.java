//Author Ankit, Akshay
//Purpose : Implementation of registration.
package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.exception.UserNotFoundException;

public class RegisterDaoImpl implements RegisterDao {

	static Connection conn;
	static PreparedStatement pgetbyemail,pgetbypass;	//creating prepared statemnets
	static {
		conn=DBUtil.getMyConnection(); //object of DButil
		try {
			//query to verify email and role existence
			pgetbyemail=conn.prepareStatement("select * from usertable where email=? and type=?");

			//query to store user given password in logintable
			pgetbypass=conn.prepareStatement(" insert into  logintable(email,password,userid)  values(?,?,?)");
		
		} catch (SQLException e) {
			System.out.println("Failed to connect to database");
			//e.printStackTrace();
		}
		
	}
	
	
	//method for verifying existence of email and role given by user for registeration
	public 	int emailExistValidation(String email, String role) throws UserNotFoundException {
		
		int id=0;

		try {
			pgetbyemail.setString(1, email);
			pgetbyemail.setString(2, role);
			ResultSet rs=pgetbyemail.executeQuery();
//			System.out.println(email);
//			System.out.println(role);
			//returns boolean status for validation of email and role existence
			if(rs.next()) {

//				System.out.println("USERID :"+rs.getString(1)+"\t"+rs.getString(3)+"\twith role as\t"+rs.getString(4)+"\teixsts");
//				System.out.println("email  validated");
				id=Integer.parseInt(rs.getString(1));//Retrieving the user id and returning for validation

//				System.out.println(rs.getString(3)+"\twith role as\t"+rs.getString(4)+"\teixsts");
//				System.out.println("email  validated");
				return id;
			}
				
				
			
		} catch (SQLException e) {
			throw new UserNotFoundException("User not found ! Please try again !");
		}

		return 0;

	}
	
	
	
	//method for storing password in logintable
	//returns boolean status of password storage
	@Override

	public boolean storePassword(String pass,String email,int userid) throws UserNotFoundException {

		boolean status=false;
		int i=0;
				try { 
					

					pgetbypass.setString(1,email);
					pgetbypass.setString(2,pass);
					pgetbypass.setLong(3,userid);

//					pgetbypass.setString(1,pass);
//					pgetbypass.setString(2,email);

					 i=pgetbypass.executeUpdate();
					if(i!=0) {
						status=true;
					}
					
				
				} catch (SQLException e) {
					throw new UserNotFoundException("User not found ! Please try again !");
				}
				
			return status;	
	}

}
