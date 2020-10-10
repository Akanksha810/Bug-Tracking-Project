/* Author : Akanksha Shrivastava,Gautami Ravindra Rahane
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

import com.code.bean.Bug;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class DisplayDeveloperBugsServlet
 */
@WebServlet("/DisplayDeveloperBugsServlet")
public class DisplayDeveloperBugsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		//To get parameters from view layer
		int projectId=Integer.parseInt(request.getParameter("projectId"));
		//To get list of all bugs from service layer
		List<Bug> bugList=bugTrackService.getAllBugs(projectId);
		request.setAttribute("bugList", bugList);
		RequestDispatcher rd=request.getRequestDispatcher("DeveloperBugs.jsp");
		rd.forward(request, response);
			
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
