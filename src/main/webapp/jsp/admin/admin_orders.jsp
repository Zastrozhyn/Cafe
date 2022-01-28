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
<title><fmt:message key="Orders" /></title>
</head>
<body>
    <form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="view_order">
		<p><input type="submit" value="<fmt:message key="All_order" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="unpaid_order">
		<input type="submit" value="<fmt:message key="Unpaid_order" />">
		<br>
	</form>
	<br>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="today_order">
		<input type="submit" value="<fmt:message key="Today_order" />">
	<br>
	</form>
	<br>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_order">
		<input name="name" required>
		<input type="submit" value="<fmt:message key="Find_order" />">
		<br>
	</form>
	<br>
	<c:forEach var="elem" items="${orders}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="paid_order">
			<input type="hidden" name="orderId" value="${elem.id}">
			<p><input type="submit" value="<fmt:message key="Paid" />"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="delete_order">
			<input type="hidden" name="orderId" value="${elem.id}">
			<p><input type="submit" value="<fmt:message key="Delete_order" />"></p>
		</form>
	</c:forEach>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a>
	<h3>${message}</h3>
</body>
</html>