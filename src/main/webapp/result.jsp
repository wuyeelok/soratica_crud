<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>

	<h1>Search Record Result</h1>

	<%
	if (1 == (int) request.getAttribute("result_row_count")) {
	%>
	<p>Record Exist</p>

	<%
	} else if (false == (boolean) request.getAttribute("canConnectDB")) {
	%>
	<p>Can not connect to DB</p>
	<%
	} else {
	%>
	<p>Record Not Found</p>
	<%
	}
	%>

	<a href="${pageContext.request.contextPath}/">Back to Search Page</a>
</body>
</html>