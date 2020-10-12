//Authors : Hrishita, Dharampreet
//for creating a new project
package com.code.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.code.bean.User;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

//@WebServlet("/createProject")
public class PMCreateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String role=(String) session.getAttribute("type");
			
		if(role!=null && (role.toLowerCase()).equals("project manager")) {
			User user = (User) session.getAttribute("user");
			//Integer managerId=user.getUserId();
			BugTrackService bService=new BugTrackServiceImpl();
			//List<User> ulist=new ArrayList<>();
			List<User> ulist=bService.getAllEmployees(user.getUserId());
			if(ulist!=null) {
				request.setAttribute("ulist",ulist);
				//call to create project
				RequestDispatcher rd=request.getRequestDispatcher("CreateProject.jsp");//PMcreateproject.jsp
				rd.forward(request, response);
			}
					
			else {
				out.println("<h5>No developers or testers are available at the moment</h5>");
				RequestDispatcher rd=request.getRequestDispatcher("ProjectManager.jsp");//PMcreateproject.jsp
				rd.include(request, response);
			}
							
		}
			
		else {
			out.println("you are not authorized user");
			session.invalidate();
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
			
		}
	}
}
