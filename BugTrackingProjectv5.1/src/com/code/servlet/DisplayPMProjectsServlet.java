//Author : Adrija
//Purpose : To display the projects created by the project manager.

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
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;


/**
 * Servlet implementation class DisplayProjectServlet
 */
//@WebServlet("/DisplayProjectServlet")
public class DisplayPMProjectsServlet extends HttpServlet {
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
		if(role!=null && (role.toLowerCase()).equals("project manager")) {
			
			
			int managerid=Integer.parseInt(request.getParameter("managerid"));//outside if
			List<Project> projectList=bugTrackService.getAllPMProjects(managerid);
			request.setAttribute("projectList", projectList);
			RequestDispatcher rd=request.getRequestDispatcher("PMProjects.jsp"); //to display the list of projects in html
			rd.forward(request, response);
			
		}
		else {
			out.print("<h4>not a project manager</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}
	
	}

}
