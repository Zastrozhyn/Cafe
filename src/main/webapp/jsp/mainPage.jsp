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
  	<c:if test="${sessionScope.client}">
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="logout">
			<p><input type="submit" value="LOGOUT"></p>
	</form>
	</c:if>
	<c:if test="${!sessionScope.client}">
		<a href="${pageContext.request.contextPath}/jsp/registration.jsp">REGISTRATION</a>
	</c:if>
	<br>
	<br>
	<c:if test="${!sessionScope.client}">
		<a href="${pageContext.request.contextPath}/jsp/login.jsp">SIGN IN</a>
	</c:if>
	<br>
	<p><a href="${pageContext.request.contextPath}/jsp/menu.jsp">MENU</a></p>
	<c:if test="${sessionScope.admin}">
		<p><a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp">ADMINISTRATION</a></p>
	</c:if>
	<c:if test="${sessionScope.client}">
		<p><a href="${pageContext.request.contextPath}/jsp/order.jsp">ORDER</a></p>
		<p><a href="${pageContext.request.contextPath}/jsp/profile.jsp">PROFILE</a></p>
	</c:if>
	<h3>${message}</h3>
</body>
</html>