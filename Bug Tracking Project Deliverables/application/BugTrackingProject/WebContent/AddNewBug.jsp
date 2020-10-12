<%@ page language="java" import="com.code.bean.User" contentType="text/html; charset=ISO-8859-1"
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
	<div class="col-7">
		<form action="addbug" method = "post">
			<table class="table">
			<%int projectId = (Integer)request.getAttribute("pid"); 
			//int createdby = (Integer)request.getAttribute("cby"); 
			User user = (User) session.getAttribute("user");%>
			
			<tr><td>Project ID : </td><td><input type="number" class="form-control" name="pid" id="pid" value="<%=projectId %>"  placeholder="Enter Project ID"></td>
			<tr><td>Title : </td><td><input type="text" class="form-control" name="title" id="title" required placeholder="Enter Title"></td>
			<tr><td>Description : </td><td><textarea rows="5" cols="20" class="form-control" name="description" id="description" required placeholder="Enter Bug description"></textarea></td>
			
			<tr><td>Created by : </td><td><input type="number" class="form-control" name="cby" id="cby" value="<%=user.getUserId() %>"></td>
			<tr><td>Open Date : </td><td><input type="date" class="form-control" name="dop" id="dop" required placeholder="yyyy-mm-dd"></td>
			
			<tr>
			<td>Status : </td>
			<td><label class="radio-inline"><input type="radio" name="status" id="open" value="open">Open</label>
			<label class="radio-inline"><input type="radio" name="status" id="closed" value="closed">Close</label>
			</td>
			</tr>
			<tr>
			<td>Severity : </td>
			<td><label class="radio-inline"><input type ="radio" name="severity" id="critical" value="critical">Critical</label>
			<label class="radio-inline"><input type ="radio" name="severity" id="major" value="major">Major</label>
			<label class="radio-inline"><input type ="radio" name="severity" id="minor" value="minor">Minor</label>
			<label class="radio-inline"><input type ="radio" name="severity" id="trivial" value="trivial">Trivial</label>
			</td>
			</tr>
			<tr><td></td><td><button type="submit" class="btn btn-primary" ame ="btn" id="sub" value="reg"> Register Bug</button>
			<button type="reset" class="btn btn-primary" name ="btn" id="reset" value="cancel"> Cancel</button>
			</td></tr>
			
			</table>
		</form>	
	</div>
		
	</div>
	
</div>

</body>
</html>