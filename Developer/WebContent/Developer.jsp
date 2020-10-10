<!-- Author : Akanksha Shrivastava, Gautami Ravindra Rahane
	Purpose :  Welcome Page for Developer -->
<%@ page language="java" import="com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Developer</title>
</head>
<body>

<%User u1=new User(101,"Gautami","gautami@gmail.com","Developer"); %>
<h1>Welcome, Developer <%=u1.getName()%></h1>
<br>
<!-- Link to view project assigned to the developer -->
<a href="DisplayDeveloperProjects?developerid=<%=u1.getUserId()%>">ViewProjects</a>

</body>
</html>