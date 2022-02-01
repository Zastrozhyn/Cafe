<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${abs}/jsp/css/style.css">
</head>
<body>
	<div class="header">
		<div class= "header_section">
			<div class="header_item header_logo">
				<a href="${abs}/jsp/user/mainPage.jsp"><fmt:message key="Main_page" /></a>			
			</div>
			<div class= "header_item header_button"><a href="${abs}/controller?command=menu&type="><fmt:message key="Menu" /></a>
			</div>
			<c:if test="${client}">
				<div class= "header_item header_button"><a href="${abs}/jsp/user/profile.jsp"><fmt:message key="Profile" /></a>
				</div>
				<div class= "header_item header_button"><a href="${abs}/jsp/user/order.jsp"><fmt:message key="Order" /></a>
				</div>
			</c:if>
			<c:if test="${admin}">
			<div class= "header_item header_button"><a href="${abs}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a>
			</div>
			</c:if>	
		</div>
		<div class= "header_section">
			<c:if test="${!client}">
				<div class= "header_item header_button"><a href="${abs}/jsp/user/registration.jsp"><fmt:message key="Registration" /></a>
				</div>
				<div class= "header_item header_button"><a href="${abs}/jsp/user/login.jsp"><fmt:message key="Authorization" /></a>
				</div>
			</c:if>
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
		  	<c:if test="${sessionScope.client}">
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="logout">
			<p><input type="submit" value="<fmt:message key="Log_out" />"></p>
		</form>
	</c:if>
		</div>
	</div>
	<br>
	<br>
</body>
</html>