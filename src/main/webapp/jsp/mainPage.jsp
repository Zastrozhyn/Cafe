<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main page</title>
</head>
<body>
  	<h2>Hello ${sessionScope.user.name}</h2>
	<a href="${pageContext.request.contextPath}/jsp/registration.jsp">REGISTRATION</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/login.jsp">SIGN IN</a>
	<form name="menu" method="GET" action="./controller">
		<input type="hidden" name="command" value="menu">
		<p><input type="submit" value="MENU"></p>
	</form>
	<form name="logout" method="GET" action="./controller">
		<input type="hidden" name="command" value="logout">
		<p><input type="submit" value="LOGOUT"></p>
	</form>
	<br>
	<c:if test="${sessionScope.admin}">
		<a href="${pageContext.request.contextPath}/jsp/administration.jsp">ADMINISTRATION</a>
	</c:if>
	<c:if test="${sessionScope.client}">
	<a href="${pageContext.request.contextPath}/jsp/order.jsp">ORDER</a>
	</c:if>
	<h3>${message}</h3>
</body>
</html>