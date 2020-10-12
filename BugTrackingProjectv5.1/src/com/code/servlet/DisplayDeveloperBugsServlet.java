/* Author : Akanksha, Gautami
	Purpose : Servlet to display bugs from a specific project*/
package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.bean.Bug;
import com.code.bean.User;
import com.code.exception.ErrorInExecution;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class DisplayDeveloperBugsServlet
 */
//@WebServlet("/DisplayDeveloperBugsServlet")
public class DisplayDeveloperBugsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		HttpSession session=request.getSession();
		
		String role=(String) session.getAttribute("type");
		if(role!=null && (role.toLowerCase()).equals("developer")) {
			
			//int pid=Integer.parseInt(request.getParameter("projectId"));-------bugServlet
			User user=(User) session.getAttribute("user");
			int userId=user.getUserId();
			int projectId=Integer.parseInt(request.getParameter("projectId"));
			List<Bug> bugList;
			try {
				bugList = bugTrackService.getAllDevBugs(projectId,userId);
				
				if(bugList!=null) {
					request.setAttribute("bugList", bugList);
					RequestDispatcher rd=request.getRequestDispatcher("DeveloperBugs.jsp");
					rd.forward(request, response);
				}
				else
					{out.println("<h5>No bugs reported</h5>");
					RequestDispatcher rd=request.getRequestDispatcher("DeveloperProjects.jsp");
					rd.include(request, response);}
			} catch (ErrorInExecutionException e) {
				out.println(e.getMessage());
				RequestDispatcher rd=request.getRequestDispatcher("Developer.jsp");
				rd.include(request, response);
			}
			
		}
		else {
			out.print("<h4>you are not authorised</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}
	
	}

}
