<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Registration" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="registration">
		<fmt:message key="Name" /><input name="name" required value="${name}"pattern="[A-Za-z]{2,25}">
		<br/>
		<br/>
		<fmt:message key="Last_name" /> <input name="last_name" value="${lastName}"required pattern="[A-Za-z]{2,25}">
		<br/>
		<br/>
		<fmt:message key="Phone" /> <input name="phone" value="${phone}"required pattern="^(\+)?((\d{2,3}) ?\d|\d)(([ -]?\d)|( ?(\d{2,3}) ?)){5,12}\d$">
		<br/>
		<br/> 
		<fmt:message key="Email" /> <input type="email" name="email" value="${email}"required>
		<br/>
		<br/>  
		<fmt:message key="Login" /> <input name="login" required pattern="[\d\D]{1,25}">
		<br/>
		<br/>  
		<fmt:message key="Password" /> <input name="password" type="password" required pattern="[\d\D]{1,25}">
		<br/>
		<br/>
		<fmt:message key="Confirm_password" /> <input name="confirm_password" type="password" value=""required>
		<p><input type="submit"></p>
	</form>
	<h3>${registration_result}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>