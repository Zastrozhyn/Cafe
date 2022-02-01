<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Change_password" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_password">
		<input type="hidden" name="userId" value="${user.userId}">
		<fmt:message key="Login" /> <input name="login" required>
		<br/>
		<br/>
		<fmt:message key="Password" /><input name="password" type="password" required>
		<br/>
		<br/>
		<fmt:message key="New_password" /><input name="new_password" type="password" required>
		<br/>
		<br/>
		<fmt:message key="Confirm_new_password" /><input name="confirm_password" type="password" required>
		<br/>
		<p><input type="submit"></p>
	</form>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>