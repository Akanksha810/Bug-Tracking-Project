<%@ page language="java" import="com.code.bean.*,java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1"
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
	<div class="row justify-content-center form-group">
	<div class="col-8">
	<form action="addproject" method="post">
		<table class="table">
		<%Date dNow = new Date( );
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
			int MILLIS_IN_DAY = 1000 * 60 * 60 * 48;%>
		<tr><td>Project Name : </td><td><input type="text" class="form-control" name="projectName" id="name" minlength="3"  required placeholder="Enter project name"></td><td></td></tr>
		<tr><td>Start date : </td><td><input type="date" class="form-control" name="startDate" id="dt" min="<%=sdf.format(dNow.getTime()+ MILLIS_IN_DAY) %>"  value="<%=sdf.format(dNow.getTime()+ MILLIS_IN_DAY) %>" required placeholder="yyyy-mm-dd"></td><td></td></tr>
		<tr><td>Description :</td><td><textarea rows="3" class="form-control" cols="20" name="description" id="desc"></textarea></td></tr>
		</table>
		<%List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");%>
		<table border="2" class="table"><br>
			<thead class="thead-dark"> <tr><th>UserId</th><th>User Name</th><th>Email Id</th><th>Role</th><th>Select Employee</th></tr> </thead>
			<%for(User u:ulist){ %>   
				<tr><td><%=u.getUserId()%></td>
				<td><%=u.getName()%></td>
				<td><%=u.getEmail()%></td>
				<td><%=u.getType()%></td>
				<td>
					<input type="checkbox" id="userId" name="userId" value="<%= u.getUserId()%>">	    	
				</td>  
				</tr>
				<%} %>
		</table>
					<!-- </div> -->
		<div class="row justify-content-center form group">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
		
	</form>
	</div>
</div>
</body>
</html>