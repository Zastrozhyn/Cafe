<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Delete_user" /></title>
</head>
<body>
		<form method="POST" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="delete_user">
			<fmt:message key="Login" /> <input name="login" required pattern="[\d\D]{1,25}">
			<br/>
			<br/>
			<fmt:message key="Password" /> <input name="password" type="password" required pattern="[\d\D]{1,25}">
			<br/>
			<p><input type="submit"></p>
	</form>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>