//Author : Bharadwaj
//Purpose : For redirecting the page and autofilling some details.

package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TempBugServlet
 */
//@WebServlet("/reportbug")
public class TempBugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String role=(String) session.getAttribute("type");

		if(role!=null && (role.toLowerCase()).equals("tester")) {

			int projectId = Integer.parseInt(request.getParameter("projectId"));
			int createdBy = Integer.parseInt(request.getParameter("createdBy"));
			
			request.setAttribute("pid", projectId);
			//request.setAttribute("cby", createdBy);
			RequestDispatcher rd=request.getRequestDispatcher("AddNewBug.jsp");
			rd.forward(request, response);
		}else {
			out.print("<h4>you are not authorised to view this page</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}
		
	}

}
