<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Dish</title>
</head>
<body>
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="add_dish">
		Name:<input name="name" required>
		<br/>
		<br/>
		Type:
		<input type="radio" name="type" value="drink"> drink <br>  
		<input type="radio" name="type" value="snack"> snack <br>  
		<input type="radio" name="type" value="desert"> desert <br> 
		<br/>
		<br/>
		Description:<input name="description" >
		<br/>
		<br/> 
		Price:<input name="price" required>
		<br/>
		<br/>  
		Weight:<input name="weight" required>
		<br/>
		<p><input type="submit" value="add"></p>
	</form>
<a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp">ADMINISTRATION</a>
<h3>${message}</h3>
</body>
</html>