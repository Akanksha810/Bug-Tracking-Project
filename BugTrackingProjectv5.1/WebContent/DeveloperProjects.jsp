<!-- Author : Akanksha Shrivastava,Gautami Ravindra Rahane
	Purpose :  -->

<%@ page language="java" import="java.util.List,com.code.bean.Project,java.util.ArrayList,com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
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
<div class="container">
	<div class="row justify-content-center">
		<div class="col-9">
			<table class="table" border="2">
							<thead class="thead-dark"><tr>
								<th>Project Name </th>
								<th>Manager </th>
								<th>Start Date </th>
								<th>Bugs</th>
							</tr></thead>
			<% List<Project> projectList= (ArrayList<Project>) request.getAttribute("projectList");
			List<User> ulist=(ArrayList<User>) request.getAttribute("userList");
				if(projectList!=null){ 
					for(Project p:projectList){ %>
							<tr>
								<td><%=p.getProjectName() %></td>
								<td><%=p.getManagerId() %></td>
								<td><%=p.getStartDate() %></td>
								<td><a href="DisplayDeveloperBugs?projectId=<%=p.getProjectId()%>" >View Bugs</a></td>
							</tr>
				<%}}else { %>
					<h1>You are not assigned to a Project.</h1>
				<% } %>	
				</table>
		</div>
	</div>
	
	
</div>	

</body>
</html>