<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Earthquake Table Data</title>
</head>
<body>
	<h2>Search Earthquake Table Data</h2>

	<form action="Hello" method="post">
		<div>
			<label for="earthquake_id">Earthquake ID: </label> <input
				type="number" name="earthquake_id" id="earthquake_id" required>

		</div>
		<input type="submit" value="Search">
	</form>
</body>
</html>