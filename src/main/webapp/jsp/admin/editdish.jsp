<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit dish</title>
</head>
<body>
 	<form method="GET" action="<c:url value="/controller"/>">
    	  <input type="hidden" name="command" value="edit_dish">
 		  Id: "${dish.id}"
		  <input type="hidden" name="dishId" value="${dish.id}"required>
	      <br/>
		  <br/>
		  Name:<input name="name" value="${dish.name}"required>
		  <br/>
		  <br/>
		  Type:
		  <input type="radio" name="type" value="drink" required> drink <br>  
		  <input type="radio" name="type" value="snack"> snack <br>  
		  <input type="radio" name="type" value="desert"> desert <br> 
		  <br/>
		  <br/>
		  Description:<input name="description" value="${dish.description}">
		  <br/>
		  <br/> 
		  Price:<input name="price" value="${dish.price}" required>
		  <br/>
		  <br/>  
		  Weight:<input name="weight" value="${dish.weight}" required>
		  <br/>
		  <p><input type="submit" value="edit"></p>
  </form>
  <a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp">ADMINISTRATION</a>
  <h3>${message}</h3>
</body>
</html>