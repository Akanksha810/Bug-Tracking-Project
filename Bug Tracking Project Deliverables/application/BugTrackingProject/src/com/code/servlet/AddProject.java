//Author : Dharampreet, Hrishita
//Purpose : Creation of new project by manager. 

package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.bean.Project;
import com.code.bean.User;
import com.code.exception.ErrorInExecutionException;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;


/**
 * Servlet implementation class AddProject
 */
//@WebServlet("/addproject")
public class AddProject extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static SimpleDateFormat sdf;

	public void init() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String role=(String) session.getAttribute("type");
		User u=(User) session.getAttribute("user");

		if(role!=null && (role.toLowerCase()).equals("project manager")) {

			int managerid=u.getUserId();
			String projectName=request.getParameter("projectName");
			String date=request.getParameter("startDate");
			String description=request.getParameter("description");
			String status=	"in progress";
			String[] str = request.getParameterValues("userId");
			int[] userIdChecked = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
			Date opDate=null;
			try {
				opDate=sdf.parse(date);
				
			} catch (ParseException e) {
				
				out.println("Failed to parse the date");
				//e.printStackTrace();
			}
			
			Project project=new Project(0,projectName,description,opDate,status,managerid);
			BugTrackService bugTrackService=new BugTrackServiceImpl();

			boolean flag=false;
			try {
				flag = bugTrackService.addProject(project);
			} catch (ErrorInExecutionException e1) {
				out.println(e1.getMessage());
			}
			if(flag) {
				
				try {
					bugTrackService.updateNoOfProjects(userIdChecked);
					int teamManager[]= {managerid};
					bugTrackService.updateNoOfProjects(teamManager);
					bugTrackService.addToTeam(projectName,userIdChecked,managerid);
				} catch (ErrorInExecutionException e) {
					out.println(e.getMessage());
				}
				out.println("Project successfully added");
				RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");
				rd.include(request, response);
			}
			else {
				out.println("Project not added");
				RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");
				rd.include(request, response);
				
			}
				
		}
		else {
			out.println("you are not authorized user");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
			//out.println("testing");
			
			
		}
	}

}
