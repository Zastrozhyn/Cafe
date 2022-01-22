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
	<c:forEach var="elem" items="${menu}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<br>
		 <c:if test="${sessionScope.client}">
		 <form name="add_to_order" method="POST" action="controller">
		 	<input type="hidden" name="command" value="add_to_order">
		 	<input type="hidden" name="dishId" value="${elem.id}">
		 	<p><input type="submit" value="add to order" ></p>
		 </form>
		 </c:if>
		 <c:if test="${sessionScope.admin}">
		 	<form name="go_edit_dish" method="POST" action="controller">
		 	<input type="hidden" name="command" value="go_to_edit_dish">
		 	<input type="hidden" name="dishId" value="${elem.id}">	
		 	<p><input type="submit" value="EDIT" ></p>
			</form>
	     </c:if>
	</c:forEach>
	  <h3>${orderList}</h3>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>