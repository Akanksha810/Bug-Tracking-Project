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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
</head>
<body>
	<%
		/* response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); */
	User user = null;
	String type = (String) session.getAttribute("type");
	if (type != null && type.equals("developer") || type.equals("project manager") || type.equals("tester")) {
		user = (User) session.getAttribute("user");
	} else {
		response.sendRedirect("/login.jsp");
	}
	%>
	<div class="jumbotron" style="padding: 15px;">
		<div class="row">
			<div class="col-sm-8">
				<h2>
					Bug Tracking System
					<h4>
						(<%=type%>)
					</h4>
				</h2>
			</div>
			<div class="col-sm-2" style="margin-top: 10px;">
				<h4>
					<%=user.getEmail()%>
					<h5>
						<%=user.getName()%>
					</h5>
				</h4>
			</div>
			<div class="col-sm-2">
				<a class="btn btn-primary" style="margin: 20px; width: 100px;"
				href="logout">Logout</a>
			</div>
		</div>
	</div>

</body>
</html>