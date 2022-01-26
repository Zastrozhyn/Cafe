<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_password">
		<input type="hidden" name="userId" value="${user.userId}">
		login:<input name="login" required>
		<br/>
		<br/>
		password:<input name="password" type="password" required>
		<br/>
		<br/>
		new password:<input name="new_password" type="password" required>
		<br/>
		<br/>
		confirm new password:<input name="confirm_password" type="password" required>
		<br/>
		<p><input type="submit"></p>
	</form>
	<h3>${message}</h3>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>