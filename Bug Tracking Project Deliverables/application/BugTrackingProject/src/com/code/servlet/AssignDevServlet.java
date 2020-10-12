//Author : Dharampreet, Adrija
//Purpose : To get the list of developers for assigning them to a bug.

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

import com.code.exception.UserNotFoundException;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

public class AssignDevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		HttpSession session=request.getSession();
		int bugId=Integer.parseInt(request.getParameter("bugId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		
		String role=(String) session.getAttribute("type");
		if(role!=null && (role.toLowerCase()).equals("project manager")) {
			boolean flag=false;
			try {
			flag= bugTrackService.assignDev(bugId,userId);
			out.print("Assignment is succesful");
			RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");
			rd.include(request, response);
			}catch(UserNotFoundException user) {
				out.println(user.getMessage());
				RequestDispatcher rd=request.getRequestDispatcher("PMBugs.jsp");
				rd.include(request, response);
			}
	
		}
		else {
			out.print("<h4>not a project manager</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}

	}
}
