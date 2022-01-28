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
	<c:forEach var="elem" items="${menu}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<c:if test="${client}">
			<form method="POST" action="<c:url value="/controller"/>">
			 	<input type="hidden" name="command" value="add_to_order">
			 	<input type="hidden" name="dishId" value="${elem.id}">
			 	<p><input type="submit" value="<fmt:message key="Add_to_order" />" ></p>
		 	</form>
		 </c:if>
		 <c:if test="${admin}">
		 	<form method="POST" action="<c:url value="/controller"/>">
			 	<input type="hidden" name="command" value="go_to_edit_dish">
			 	<input type="hidden" name="dishId" value="${elem.id}">	
			 	<p><input type="submit" value="<fmt:message key="Edit_dish" />" ></p>
			</form>
	     </c:if>
	</c:forEach>
	<a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a>
</body>
</html>