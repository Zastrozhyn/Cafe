<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${Locale}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Delete_user" /></title>
</head>
<body>
	<form name="delete" method="POST" action="./controller">
		<input type="hidden" name="command" value="delete_user">
		<fmt:message key="Login" /> <input name="login" required pattern="[A-Za-z]{3,25}">
		<br/>
		<br/>
		<fmt:message key="Password" /> <input name="password" type="password" required pattern="[A-Za-z]{3,25}">
		<br/>
		<p><input type="submit"></p>
	</form>
	<a href="${pageContext.request.contextPath}/jsp/profile.jsp"><fmt:message key="Profile" /></a>
</body>
</html>