<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit profile</title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		  <input type="hidden" name="command" value="edit_profile">
		  <input type="hidden" name="userId" value="${user.userId}">
		  Name:<input name="name" value="${user.name}">
		  <br/>
		  <br/>
		  Last Name:<input name="last_name" value="${user.lastName}">
		  <br/>
		  <br/>
		  Phone:<input name="phone" value="${user.phone}">
		  <br/>
		  <br/> 
		  email:<input type="email" name="email" value="${user.email}">
		  <br/>
		  <p><input type="submit"></p>
	  </form>
	  <a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>