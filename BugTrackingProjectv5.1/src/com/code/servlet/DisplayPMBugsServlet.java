//Author : Akanksha
//Purpose : To display bug list for the project chosen by the project manager.

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
import com.code.bean.Project;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class DisplayBugServlet
 */
//@WebServlet("/DisplayBugServlet")
public class DisplayPMBugsServlet extends HttpServlet {
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
		if(role!=null && (role.toLowerCase()).equals("project manager")) {
			
			int projectId=Integer.parseInt(request.getParameter("projectId"));
			List<Bug> bugList=bugTrackService.getAllBugs(projectId);
			request.setAttribute("bugList", bugList);
			RequestDispatcher rd=request.getRequestDispatcher("PMBugs.jsp");
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
