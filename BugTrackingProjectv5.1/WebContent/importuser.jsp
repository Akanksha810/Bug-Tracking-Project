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
	<div class="container" style="width: 30%; margin-top: 50px;">
		<h2 style="text-align: center">Upload File</h2>
		<form action="importusers" method="post" enctype="multipart/form-data"
			id="importusers">
			<div class="form-group">
				<input type="file" class="form-control-file border" name="file"
					id="file" style="border: 1px">
			</div>
			<div style="text-align: center; margin-top: 30px;">
				<button type="submit" class="btn btn-primary">Upload</button>
			</div>
		</form>
	</div>
</body>
</html>