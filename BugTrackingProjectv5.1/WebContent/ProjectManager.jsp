<%@ page language="java" import="com.code.bean.User"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		String type = (String) session.getAttribute("type");
	if (!type.equals("project manager")) {
		response.sendRedirect("http://localhost:8080/BugTrackingProject/login.jsp");
	}
	User user = (User) session.getAttribute("user");
	%>

	<div class="container" style="margin-top: 50px; text-align: center;">
		<a class="btn btn-primary" style="margin: 20px; width: 150px;"
			href="CreateProject">Create Project</a><br> <a
			class="btn btn-primary" style="margin: 20px; width: 150px;"
			href="DisplayPMProjects?managerid=<%=user.getUserId()%>">Project
			Details</a>
	</div>
</body>
</html>