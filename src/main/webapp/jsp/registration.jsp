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
<title><fmt:message key="Registration" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="registration">
		<fmt:message key="Name" /><input name="name" required pattern="[A-Za-z]{2,25}">
		<br/>
		<br/>
		<fmt:message key="Last_name" /> <input name="last_name" value=""required pattern="[A-Za-z]{2,25}">
		<br/>
		<br/>
		<fmt:message key="Phone" /> <input name="phone" value=""required pattern="^(\+)?((\d{2,3}) ?\d|\d)(([ -]?\d)|( ?(\d{2,3}) ?)){5,12}\d$">
		<br/>
		<br/> 
		<fmt:message key="Email" /> <input type="email" name="email" value=""required>
		<br/>
		<br/>  
		<fmt:message key="Login" /> <input name="login" required pattern="[A-Za-z]{3,25}">
		<br/>
		<br/>  
		<fmt:message key="Password" /> <input name="password" type="password" required pattern="[A-Za-z0-9]{5,20}">
		<br/>
		<br/>
		<fmt:message key="Confirm_password" /> <input name="confirm_password" type="password" value=""required>
		<p><input type="submit"></p>
	</form>
	<h3>${registration_result}</h3>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a>
</body>
</html>