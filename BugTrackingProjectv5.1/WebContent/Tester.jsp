<%@page import="com.code.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	User user = (User) session.getAttribute("user");
	if (!type.equals("tester")) {
		response.sendRedirect("http://localhost:8080/BugTrackingProject/login.jsp");
	}
	%>

	<div class="container" style="margin-top: 50px; text-align: center;">
		<a class="btn btn-primary" style="margin: 20px; width: 150px;"
			href="DisplayTesterProjects?testerid=<%=user.getUserId()%>">Assigned
			Projects</a>
	</div>
</body>
</html>