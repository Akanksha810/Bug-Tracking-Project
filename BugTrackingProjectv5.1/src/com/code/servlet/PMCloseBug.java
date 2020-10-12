//Author : Akanksha Shrivastava, Adrija, Dharampreet
//Purpose : For closing the bugs marked by the developers.

package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.exception.BugNotFoundException;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

public class PMCloseBug extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		HttpSession session=request.getSession();
		int bugId=Integer.parseInt(request.getParameter("bugId"));
		
		String role=(String) session.getAttribute("type");

		if(role!=null && (role.toLowerCase()).equals("project manager")) {
			try {
				bugTrackService.closeBug(bugId);	
				out.println("Successfully closed");
				RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");
				rd.include(request, response);
				}catch(BugNotFoundException bug) {
				
					out.println(bug.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");
					rd.include(request, response);
					
			}
		}
		else {
			out.print("<h4>not admin</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}

	}
}