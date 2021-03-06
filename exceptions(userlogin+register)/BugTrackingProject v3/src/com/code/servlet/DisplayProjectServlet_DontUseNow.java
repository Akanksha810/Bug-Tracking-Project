

// _________________________________used for optimizing the code (in the end)________________________

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
public class DisplayProjectServlet_DontUseNow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		BugTrackService bugTrackService=new BugTrackServiceImpl();
		//HttpSession session=request.getSession();
		
		//String role=(String) session.getAttribute("role");
		//if((role.toLowerCase()).equals("project manager")) {
		
			//getting manager id through url from the anchor tag of login page of project manager
			int managerid=Integer.parseInt(request.getParameter("managerid"));
			List<Project> projectList=bugTrackService.getAllProjects(managerid);
			request.setAttribute("projectList", projectList);
			RequestDispatcher rd=request.getRequestDispatcher("project_DontUseNow.jsp"); //calling the jsp, and sending projectList to projectjsp
			rd.forward(request, response);
			
		//}
		/*else {
			out.print("<h4>not admin</h4>");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}*/
	
	}

}
