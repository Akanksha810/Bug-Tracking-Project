//Authors : Dharampreet, Adrija
//Purpose : For assigning the developer to a bug.
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

import com.code.bean.User;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

public class PMAssign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		HttpSession session=request.getSession();
		int bugId=Integer.parseInt(request.getParameter("bugId"));
		
		String role=(String) session.getAttribute("type");
		User u=(User) session.getAttribute("user");

		if(role!=null && (role.toLowerCase()).equals("project manager")) {

			int managerId=u.getUserId();
			List<User> userlist=bugTrackService.getUsersByManager(managerId);
			request.setAttribute("userlist", userlist);
			request.setAttribute("bugId", bugId);
			RequestDispatcher rd=request.getRequestDispatcher("PmAssignDev.jsp");
			rd.forward(request, response);
		}

		else {
			out.print("<h4>not logged in</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}

	}
}
