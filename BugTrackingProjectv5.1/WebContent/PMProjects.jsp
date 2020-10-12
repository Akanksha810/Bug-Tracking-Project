<!-- Author : Adrija Ghansiyal
	Purpose :  -->

<%@ page language="java" import="java.util.List,com.code.bean.Project,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	td,th{
		text-align : center;
	}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-4 align-content-center">
				<table class="table" border="2">
					<thead class="thead-dark">
						<tr><th>Project Name</th></tr>
					</thead>
					<% List<Project> projectList= (ArrayList<Project>)request.getAttribute("projectList"); %>
					<%for(Project p:projectList){ %>
						<tr><td><a href="DisplayPMBugs?projectId=<%=p.getProjectId()%>" ><%=p.getProjectName()%></a></td></tr>
					<%} %>
				</table>
				
							
			</div>
		</div>
	</div>

</body>
</html>