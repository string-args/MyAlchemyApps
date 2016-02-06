<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<title>Alchemy API - Feature Extraction and Image Analysis </title>
</head>
<meta charset="utf-8">
	<body>
		<h1> Hello World! </h1>
		<form action="FServlet" method="POST">
			<input type="submit" value="Submit">
		</form>
		<%
			if (request.getParameter("sample") != null){
				out.println("<h1>" + request.getParameter("sample") + "</h1>");
			}
		%>
	</body>
</html>