<%@page import="com.code.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		String type = (String) session.getAttribute("type");
	if (!type.equals("developer")) {
		response.sendRedirect("http://localhost:8080/BugTrackingProject/login.jsp");
	}
	%>
	<a href="logout">Log Out</a>
</body>
</html>