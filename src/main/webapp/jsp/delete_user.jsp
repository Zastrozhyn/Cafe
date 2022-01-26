<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete user</title>
</head>
<body>
	<form name="delete" method="POST" action="./controller">
		<input type="hidden" name="command" value="delete_user">
		login:<input name="login" required>
		<br/>
		<br/>
		password:<input name="password" type="password" required>
		<br/>
		<p><input type="submit"></p>
	</form>
	<a href="${pageContext.request.contextPath}/jsp/profile.jsp">PROFILE</a>
</body>
</html>