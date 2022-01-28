<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${Locale}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Main_page" /></title>
</head>
<body>
    <form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_locale">
		<input type="hidden" name="session_locale" value="ru_RU">
		<p><input type="submit" value="RU"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_locale">
		<input type="hidden" name="session_locale" value="en_US">
		<p><input type="submit" value="EN"></p>
	</form>

    
  	<h2><fmt:message key="Hello" /> ${sessionScope.user.name}</h2>
  	<c:if test="${sessionScope.client}">
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="logout">
			<p><input type="submit" value="LOGOUT"></p>
	</form>
	</c:if>
	<c:if test="${!sessionScope.client}">
		<a href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message key="Registration" /></a>
	</c:if>
	<br>
	<br>
	<c:if test="${!sessionScope.client}">
		<a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="Authorization" /></a>
	</c:if>
	<br>
	<p><a href="${pageContext.request.contextPath}/jsp/menu.jsp"><fmt:message key="Menu" /></a></p>
	<c:if test="${sessionScope.admin}">
		<p><a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a></p>
	</c:if>
	<c:if test="${sessionScope.client}">
		<p><a href="${pageContext.request.contextPath}/jsp/order.jsp"><fmt:message key="Order" /></a></p>
		<p><a href="${pageContext.request.contextPath}/jsp/profile.jsp"><fmt:message key="Profile" /></a></p>
	</c:if>
	<h3>${message}</h3>
</body>
</html>