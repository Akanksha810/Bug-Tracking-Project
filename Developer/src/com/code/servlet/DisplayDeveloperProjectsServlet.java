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

import com.code.bean.Project;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

//@WebServlet("/DisplayDeveloperProjectsServlet")
public class DisplayDeveloperProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In display servlet");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		//To get parameters from view layer
		int developerid=Integer.parseInt(request.getParameter("developerid"));
		System.out.println(developerid);
		//To get list of projects from service layer
		List<Project> projectList=bugTrackService.getAllProjects(developerid);
		request.setAttribute("projectList", projectList);
		RequestDispatcher rd=request.getRequestDispatcher("DeveloperProjects.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
