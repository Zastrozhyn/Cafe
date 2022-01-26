<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MENU</title>
</head>
<body>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="">
		<p><input type="submit" value="ALL MENU"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="snack">
		<p><input type="submit" value="SNACK MENU"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="desert">
		<p><input type="submit" value="DESERT MENU"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="drink">
		<p><input type="submit" value="DRINK MENU"></p>
	</form>
	<br>
	<c:forEach var="elem" items="${menu}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<c:if test="${sessionScope.client}">
			<form method="POST" action="<c:url value="/controller"/>">
			 	<input type="hidden" name="command" value="add_to_order">
			 	<input type="hidden" name="dishId" value="${elem.id}">
			 	<p><input type="submit" value="add to order" ></p>
		 	</form>
		 </c:if>
		 <c:if test="${sessionScope.admin}">
		 	<form method="POST" action="<c:url value="/controller"/>">
			 	<input type="hidden" name="command" value="go_to_edit_dish">
			 	<input type="hidden" name="dishId" value="${elem.id}">	
			 	<p><input type="submit" value="EDIT" ></p>
			</form>
	     </c:if>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>