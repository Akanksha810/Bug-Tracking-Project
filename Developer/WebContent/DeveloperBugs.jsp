<!-- Author : Akanksha Shrivastava, Gautami Ravindra Rahane
	Purpose : View for bugs for the projects assigned to developer -->

<%@ page language="java" import="java.util.List,com.code.bean.Bug,java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Bugs</title>
</head>
<body>

<% List<Bug> bugList= (ArrayList<Bug>)request.getAttribute("bugList"); %>
<!-- Form to mark bug for closing and to add remark -->
<form action="AddRemarks" method="post">
<table border="1">
	<tr>
		<th>Bug ID</th>
		<th>Bug Title</th>
		<th>Description</th>
		<th>Project ID</th>
		<th>Created By</th>
		<th>Open Date</th>
		<th>Assigned To</th>
		<th>Marked For Closing</th>
		<th>Closed By</th>
		<th>Closed On</th>
		<th>Status</th>
		<th>Severity Level</th>
		<th>Remarks</th>
	</tr>
	<%for(Bug bug:bugList){ %>
	<tr>
		<td><input type="text" name="bugId" value="<%=bug.getBugId() %>" readonly></td>
		<td><input type="text" name="bugTitle" value="<%=bug.getBugTitle() %>" readonly></td>
		<td><input type="text" name="bugDescription" value="<%=bug.getBugDescription() %>" readonly></td>
		<td><input type="text" name="projectId" value="<%=bug.getProjectId() %>" readonly></td>
		<td><input type="text" name="createdBy" value="<%=bug.getCreatedBy() %>" readonly></td>
		<td><input type="text" name="openDate" value="<%=bug.getOpenDate() %>" readonly></td>
		<td><input type="text" name="assignedTo" value="<%=bug.getAssignedTo() %>" readonly></td>
		<td><input type="checkbox" name="markedforclosing" id="marked" value="false">Mark for Closing(<%=bug.getMarkedForClosing() %>)</td>
		<td><input type="text" name="closedBy" value="<%=bug.getClosedBy() %>" readonly></td>
		<td><input type="text" name="closedOn" value="<%=bug.getClosedOn() %>" readonly></td>
		<td><input type="text" name="status" value="<%=bug.getStatus() %>" readonly></td>
		<td><input type="text" name="severityLevel" value="<%=bug.getSeverityLevel() %>" readonly></td>
		<td><textarea cols="20" rows="5" name="remarks" id="remarks"></textarea></td>
		<td><input type="submit" name="btn" value="Submit"></td>
	</tr>
	<%} %>
</table>
</form>
</body>
</html>