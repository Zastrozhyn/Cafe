<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Edit_profile" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		  <input type="hidden" name="command" value="edit_profile">
		  <input type="hidden" name="userId" value="${user.userId}">
		  <fmt:message key="Name" /> <input name="name" value="${user.name}" required pattern="[A-Za-z]{2,25}">
		  <br/>
		  <br/>
		  <fmt:message key="Last_name" /><input name="last_name" value="${user.lastName}" required pattern="[A-Za-z]{2,25}">
		  <br/>
		  <br/>
		  <fmt:message key="Phone" /><input name="phone" value="${user.phone}" required pattern="^(\+)?((\d{2,3}) ?\d|\d)(([ -]?\d)|( ?(\d{2,3}) ?)){5,12}\d$">
		  <br/>
		  <br/> 
		  <fmt:message key="Email" /><input type="email" name="email" value="${user.email}">
		  <br/>
		  <p><input type="submit"></p>
	  </form>
	  <h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>