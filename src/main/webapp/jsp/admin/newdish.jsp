<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="New_dish" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="add_dish">
		<fmt:message key="Name" /><input name="name" required pattern="[\d\D]{1,255}">
		<br/>
		<br/>
		<fmt:message key="Type" />
		<input type="radio" name="type" value="drink"> <fmt:message key="Drink" /> <br>  
		<input type="radio" name="type" value="snack"> <fmt:message key="Snack" />  <br>  
		<input type="radio" name="type" value="desert"> <fmt:message key="Desert" /> <br> 
		<br/>
		<br/>
		<fmt:message key="Description" /><input name="description" pattern="[\d\D]{1,255}">
		<br/>
		<br/> 
		<fmt:message key="Price" /><input name="price" required pattern="^[^-]\d*.?\d+$">
		<br/>
		<br/>  
		<fmt:message key="Weight" /><input name="weight" required pattern="^[^-]\d*.?\d+$">
		<br/>
		<p><input type="submit" value="<fmt:message key="Add_dish" />"></p>
	</form>
<h3>${message}</h3>
</body>
</html>