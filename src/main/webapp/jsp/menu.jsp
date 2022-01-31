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
<title><fmt:message key="Menu" /></title>
<style>
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid black;
    }
</style>
</head>
<body>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="">
		<p><input type="submit" value="<fmt:message key="ALL_MENU" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="snack">
		<p><input type="submit" value="<fmt:message key="SNACK_MENU" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="desert">
		<p><input type="submit" value="<fmt:message key="DESERT_MENU" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="drink">
		<p><input type="submit" value="<fmt:message key="DRINK_MENU" />"></p>
	</form>
	<br>
	<br>
	<table>
		<caption><fmt:message key="Menu" /></caption>
		<tr><th></th><th><fmt:message key="Name"/></th>
		<th><fmt:message key="Description"/></th>
		<th><fmt:message key="Weight"/></th>
		<th><fmt:message key="Price"/></th>
		</tr>
		<c:forEach var="elem" items="${menu}" varStatus="status">
		    <tr><td><c:out value="${status.count}"/></td>
		    <td><c:out value="${elem.name}"/></td>
		    <td><c:out value="${elem.description}"/></td>
		    <td><c:out value="${elem.weight}"/></td>
		    <td><c:out value="${elem.price}"/></td>
		    <td align="center">
			<c:if test="${client}">
				<form name="add_to_order" method="POST" action="<c:url value="/controller"/>">
				 	<input type="hidden" name="command" value="add_to_order">
				 	<input type="hidden" name="dishId" value="${elem.id}">
				 	<input type="submit" value="<fmt:message key="Add_to_order" />" >
			 	</form>
			 </c:if>
			 <c:if test="${admin}">
			 	<form name="edit_dish" method="POST" action="<c:url value="/controller"/>">
				 	<input type="hidden" name="command" value="go_to_edit_dish">
				 	<input type="hidden" name="dishId" value="${elem.id}">	
				 	<input type="submit" value="<fmt:message key="Edit_dish" />" >
				</form>
		     </c:if>
		     </td></tr>
		</c:forEach>
	</table>
		<c:if test="${sessionScope.client}">
		<p><a href="${pageContext.request.contextPath}/jsp/order.jsp"><fmt:message key="Order" /></a></p>
	</c:if>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a>
	<h3>${message}</h3>
</body>
</html>