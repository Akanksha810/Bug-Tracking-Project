//Authors : Ankit, Akshay
//Purpose : For registering a user.

package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.exception.UserNotFoundException;
import com.code.service.RegisterService;
import com.code.service.RegisterServiceImpl;


public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    RegisterService registerService=new RegisterServiceImpl();
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//taking values from html
		String email=request.getParameter("email").toLowerCase();
		String role=request.getParameter("role").toLowerCase();
		String pass=request.getParameter("pass").toLowerCase();
		//System.out.println(role);
		boolean flag=false;
		int userid = 0;
		try {
			userid = registerService.validateEmail(email,role);
		} catch (UserNotFoundException e) {
			out.println("<br><h5>User not Found</h5>");
			//e.printStackTrace();
		}
		//method to check if the user email and role is imported 
		if(userid!=0) {
			System.out.println("email verified");
		//create password method called to create new password for imported user
			try {
				flag=registerService.createPassword(pass,email,userid);
			} catch (UserNotFoundException e) {
				out.println("<br>already registered user");
				RequestDispatcher rd1=request.getRequestDispatcher("Registration.html");
				rd1.include(request, response);
				//e.printStackTrace();
			}
			

			if(flag) {
				out.println("<br>User Resgistered Sucessfully");
				out.println("<br>Your Password has been Successfully Created");
				//request dispatcher to redirect to login page after successful registration
				RequestDispatcher rd1=request.getRequestDispatcher("login.jsp");
				rd1.include(request, response);
			}
			
			
		}
		else {
			//request dispatcher to redirect to login page after unsuccessful registration
			out.println("<br>User not found");
			RequestDispatcher rd1=request.getRequestDispatcher("Registration.html");
			rd1.include(request, response);
		}	
		
	}

}
