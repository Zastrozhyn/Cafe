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
<title><fmt:message key="Authorization" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="login">
		<fmt:message key="Login" /><input name="login" required pattern="[\d\D]{1,25}">
		<br/>
		<br/>
		<fmt:message key="Password" /><input name="password" type="password" required pattern="[A-Za-z0-9]{5,20}">
		<br/>
		<p><input type="submit"></p>
	</form>
	<h3>${message}</h3>
	<a href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message key="Registration" /></a>
</body>
</html>