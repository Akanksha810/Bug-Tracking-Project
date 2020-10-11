<!-- Author : Akanksha Shrivastava
	Purpose :  -->

<%@ page language="java" import="java.util.List,com.code.bean.Bug,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
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
		<% List<Bug> bugList= (ArrayList<Bug>)request.getAttribute("bugList"); %>
		<div class="row justify-content-center">
			<div class="col-12">
				<table border="1" class="table">
				<thead class="thead-dark"><tr><th>Bug ID</th><th>Bug Title</th><th>Description</th><th>Project ID</th><th>Created By</th><th>Open Date</th><th>Assigned To</th>
				<th>Marked For Closing</th><th>Closed By</th><th>Closed On</th><th>Status</th><th>Severity Level</th><th>Task</th></tr></thead>
				<%for(Bug bug:bugList){ %>
					<tr><td><%=bug.getBugId() %></td><td><%=bug.getBugTitle() %></td><td><%=bug.getBugDescription()%></td><td><%=bug.getProjectId()%></td>
					<td><%=bug.getCreatedBy()%></td><td><%=bug.getOpenDate()%></td><td><%=bug.getAssignedTo()%></td><td><%=bug.getMarkedForClosing()%></td>
					<td><%=bug.getClosedBy()%></td><td><%=bug.getClosedOn()%></td><td><%=bug.getStatus()%></td><td><%=bug.getSeverityLevel()%></td>
					</tr>
				<%} %>
		</table>	
			</div>
		</div>
		
	</div>

</body>
</html>