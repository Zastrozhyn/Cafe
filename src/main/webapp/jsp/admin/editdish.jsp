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
<title><fmt:message key="Edit_dish" /></title>
</head>
<body>
 	<form method="GET" action="<c:url value="/controller"/>">
    	  <input type="hidden" name="command" value="edit_dish">
 		  <fmt:message key="Id" /> "${dish.id}"
		  <input type="hidden" name="dishId" value="${dish.id}"required>
	      <br/>
		  <br/>
		  <fmt:message key="Name" /><input name="name" value="${dish.name}"required pattern="[\d\D]{1,255}">
		  <br/>
		  <br/>
		  <fmt:message key="Type" />
		  <input type="radio" name="type" value="drink" required> <fmt:message key="Drink" /> <br>  
		  <input type="radio" name="type" value="snack"> <fmt:message key="Snack" /> <br>  
		  <input type="radio" name="type" value="desert"> <fmt:message key="Desert" /> <br> 
		  <br/>
		  <br/>
		  <fmt:message key="Description" /><input name="description" value="${dish.description}">
		  <br/>
		  <br/> 
		  <fmt:message key="Price" /><input name="price" value="${dish.price}" required pattern="^[^-]\d*.?\d+$">
		  <br/>
		  <br/>  
		  <fmt:message key="Weight" /><input name="weight" value="${dish.weight}" required pattern="^[^-]\d*.?\d+$">
		  <br/>
		  <p><input type="submit" value="<fmt:message key="Edit_dish" />"></p>
  </form>
  <a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a>
  <h3>${message}</h3>
</body>
</html>