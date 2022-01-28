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
<title><fmt:message key="New_dish" /></title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="add_dish">
		<fmt:message key="Name" /><input name="name" required pattern="[A-Za-z]{2,25}">
		<br/>
		<br/>
		<fmt:message key="Type" />
		<input type="radio" name="type" value="drink"> <fmt:message key="Drink" /> <br>  
		<input type="radio" name="type" value="snack"> <fmt:message key="Snack" />  <br>  
		<input type="radio" name="type" value="desert"> <fmt:message key="Desert" /> <br> 
		<br/>
		<br/>
		<fmt:message key="Description" /><input name="description" >
		<br/>
		<br/> 
		<fmt:message key="Price" /><input name="price" required pattern="^[^-]\d*.?\d+$">
		<br/>
		<br/>  
		<fmt:message key="Weight" /><input name="weight" required pattern="^[^-]\d*.?\d+$">
		<br/>
		<p><input type="submit" value="<fmt:message key="Add_dish" />"></p>
	</form>
<a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a>
<h3>${message}</h3>
</body>
</html>