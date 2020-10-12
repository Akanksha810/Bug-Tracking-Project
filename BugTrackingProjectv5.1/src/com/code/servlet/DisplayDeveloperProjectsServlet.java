//Author : Akanksha
//Purpose : To display projects assigned to the developer.

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

import com.code.bean.Project;
import com.code.bean.User;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class DisplayDeveloperProjectsServlet
 */
//@WebServlet("/DisplayDeveloperProjectsServlet")
public class DisplayDeveloperProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		HttpSession session=request.getSession();
		
		String role=(String) session.getAttribute("type");
		User user=(User) session.getAttribute("user");
		if(role!= null && (role.toLowerCase()).equals("developer")) {
			int developerid=Integer.parseInt(request.getParameter("developerid"));//outside if
			List<Project> projectList=bugTrackService.getAllProjects(developerid);
			request.setAttribute("projectList", projectList);
			RequestDispatcher rd=request.getRequestDispatcher("DeveloperProjects.jsp");
			rd.forward(request, response);
			
		}
		else {
			out.print("<h4>not a developer</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}
	}

}
