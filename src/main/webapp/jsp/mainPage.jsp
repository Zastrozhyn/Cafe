<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Main_page" /></title>
</head>
<body>
    <form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_locale">
		<input type="hidden" name="Locale" value="ru_RU">
		<p><input type="submit" value="RU"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="change_locale">
		<input type="hidden" name="Locale" value="en_US">
		<p><input type="submit" value="EN"></p>
	</form>
  	<h2><fmt:message key="Hello" /> ${user.name}</h2>
  	<c:if test="${sessionScope.client}">
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="logout">
			<p><input type="submit" value="<fmt:message key="Log_out" />"></p>
	</form>
	</c:if>
	<c:if test="${!sessionScope.client}">
		<h4><a href="${abs}/jsp/registration.jsp"><fmt:message key="Registration" /></a></h4>
	</c:if>
	<c:if test="${!sessionScope.client}">
		<h4><a href="${abs}/jsp/login.jsp"><fmt:message key="Authorization" /></a></h4>
	</c:if>
	<h4><a href="${abs}/jsp/menu.jsp"><fmt:message key="Menu" /></a></h4>
	<c:if test="${sessionScope.client}">
		<h4><a href="${abs}/jsp/order.jsp"><fmt:message key="Order" /></a></h4>
		<h4><a href="${abs}/jsp/profile.jsp"><fmt:message key="Profile" /></a></h4>
	</c:if>
	<c:if test="${sessionScope.admin}">
		<h4><a href="${abs}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a></h4>
	</c:if>
	<h3>${message}</h3>
</body>
</html>