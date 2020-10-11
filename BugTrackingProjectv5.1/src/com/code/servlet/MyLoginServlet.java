// Authors : Hrushikesh, Adrija
// Purpose : servlet for login (session created)

package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.service.UserServiceImpl;
import com.code.bean.User;
import com.code.exception.InvalidUserException;

//@WebServlet("/MyLoginServlet")
public class MyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String userName = request.getParameter("uname");
		String password = request.getParameter("pass");

		UserServiceImpl userService = new UserServiceImpl();
		User user = null;
		//try {
			try {
				user = userService.validateUser(userName, password);
			} catch (InvalidUserException e) {
				out.println("<br> Invalid credentials user not found");
				//e.printStackTrace();
			}
			if (user != null) {

				HttpSession session = request.getSession();
				session.setAttribute("type", user.getType().toLowerCase());
				session.setAttribute("user", user);
				RequestDispatcher dispatcher = null;
				switch (user.getType().toLowerCase()) {

					case "project manager":
						dispatcher = request.getRequestDispatcher("ProjectManager.jsp");
						dispatcher.forward(request, response);
						break;

					case "developer":
						dispatcher = request.getRequestDispatcher("Developer.jsp");
						dispatcher.forward(request, response);
						break;

					case "tester":
						dispatcher = request.getRequestDispatcher("Tester.jsp");
						dispatcher.forward(request, response);
						break;

					default:
						break;
				}
			} else {
				
				out.println("<h4 style='color=red'>Invalid User Name and Passsword</h4>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.include(request, response);
			}
	}

}
