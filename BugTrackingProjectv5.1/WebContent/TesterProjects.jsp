<!-- Author : Akanksha Shrivastava,Bharadwaj Divate
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
		<%User user = (User) session.getAttribute("user"); %>
		 <%-- Name = <%=user.getName() %>
		 Role = <%=user.getType() %>
		 Email = <%=user.getEmail() %>	 --%>
	</div>
	
	 <% List<Project> projectList= (ArrayList<Project>)request.getAttribute("projectList"); %>
		<%if (projectList ==null){%>
		<p> You are not assigned to any project</p>
		<%}else{ %>
		<div class="row justify-content-center">
			<div class="col-9">
				<table class="table" border="2">
					<thead class="thead-dark"><tr><th>Project Name</th> <th>Action</th></tr></thead>
					<%for(Project p:projectList){ %>	
						<tr>
							<td><a href="DisplayTesterBugs?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a></td>
							<td><a href= "reportbug?projectId=<%=p.getProjectId()%>&createdBy=<%=user.getUserId()%>">Report new bug</a></td>
						</tr>
					<%} }%>
				</table>
			</div>
			
		</div>	
	
</div>

</body>
</html>