<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="registration">
		Name:<input name="name" value=""required>
		<br/>
		<br/>
		Last Name:<input name="last_name" value=""required>
		<br/>
		<br/>
		Phone:<input name="phone" value=""required>
		<br/>
		<br/> 
		email:<input type="email" name="email" value=""required>
		<br/>
		<br/>  
		login:<input name="login" required>
		<br/>
		<br/>  
		password:<input name="password" type="password" required>
		<br/>
		<br/>
		confirm password:<input name="confirm_password" type="password" value=""required>
		<p><input type="submit"></p>
	</form>
	<h3>${registration_result}</h3>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>