<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Edit_dish" /></title>
</head>
<body>
 	<form method="POST" action="<c:url value="/controller"/>">
    	  <input type="hidden" name="command" value="edit_dish">
 		  <fmt:message key="Id" /> "${dish.id}"
 		  <fmt:message key="Deleted" />:"${dish.archive}" 
		  <input type="hidden" name="dishId" value="${dish.id}">
	      <br/>
		  <br/>
		  <fmt:message key="Name" /><input name="name" value="${dish.name}"required pattern="[\d\D]{1,255}">
		  <br/>
		  <br/>
		  <input type="hidden" name="type" value="${dish.type}">
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
    <form name="edit_dish" method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="delete_dish">
		<input type="hidden" name="dishId" value="${dish.id}">	
		<input type="hidden" name="archive" value="${dish.archive}">
		<input type="submit" value="<fmt:message key="Delete/restore" />" >
	</form>
  <h3>${message}</h3>
</body>
</html>