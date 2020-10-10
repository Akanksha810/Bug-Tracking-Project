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

/**
 * Servlet implementation class DisplayTesterProojectsServlet
 */
//@WebServlet(name = "DisplayTesterProjectsServlet", urlPatterns = { "/DisplayTesterProjectsServlet" })
public class DisplayTesterProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			BugTrackService bugTrackService=new BugTrackServiceImpl();
			//HttpSession session=request.getSession();
			
			//String role=(String) session.getAttribute("role");
			//if((role.toLowerCase()).equals("project manager")) {
				
				//int pid=Integer.parseInt(request.getParameter("projectId"));-------bugServlet
				int testerid=Integer.parseInt(request.getParameter("testerid"));//outside if
				List<Project> projectList=bugTrackService.getAllProjects(testerid);
				request.setAttribute("projectList", projectList);
				RequestDispatcher rd=request.getRequestDispatcher("TesterProjects.jsp");
				rd.forward(request, response);
				
			//}
			/*else {
				out.print("<h4>not admin</h4>");
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}*/
	}
}
