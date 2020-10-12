//Author : Bharadwaj Divate
//Purpose : Used to get info about the new bug from addbug.jsp form.
package com.code.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.bean.Bug;
import com.code.bean.User;
import com.code.exception.ErrorInExecution;
import com.code.service.BugTrackService;
import com.code.service.BugTrackServiceImpl;

/**
 * Servlet implementation class AddBugServlet
 */
//@WebServlet("/addbug")
public class GetBugInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static SimpleDateFormat sdf;

	public void init() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		User u=(User)session.getAttribute("user");
		String role = (String) session.getAttribute("type");

		if (role != null && (role.toLowerCase()).equals("tester")) {
			
			int projId = Integer.parseInt(request.getParameter("pid"));
			String title = request.getParameter("title");
			String descrp = request.getParameter("description");

			int createdBy = Integer.parseInt(request.getParameter("cby"));
			String openDate = request.getParameter("dop");

			String status = request.getParameter("status");
			String severity = request.getParameter("severity");
			Bug bug = new Bug();

			bug.setBugTitle(title);
			bug.setBugDescription(descrp);
			bug.setProjectId(projId);
			bug.setCreatedBy(createdBy);
			bug.setStatus(status);
			bug.setSeverityLevel(severity);
			
			try {
				Date opDate=sdf.parse(openDate);
				bug.setOpenDate(opDate);
			} catch (ParseException e) {
			
				out.println("Failed to parse date");
				//e.printStackTrace();
			}

			BugTrackService bugTrack = new BugTrackServiceImpl();
			
			try {
				bugTrack.addBug(bug);
				out.println("Bug reported");
			} catch (ErrorInExecutionException e) {
				out.println(e.getMessage());
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("DisplayTesterProjects?testerid="+u.getUserId());
			rd.include(request, response);

		} else {
			out.print("<h4>you are not authorised to view this page</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			session.invalidate();
			rd.include(request, response);
		}

	}
}