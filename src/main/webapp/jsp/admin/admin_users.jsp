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
<title><fmt:message key="Users" /></title>
</head>
<body>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="view_user">
		<p><input type="submit" value="<fmt:message key="Users" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_user">
		<input name="name" required>
		<input type="submit" value="<fmt:message key="Find_user" />">
		<br>
	</form>
	<c:forEach var="elem" items="${users}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<br>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="block_user">
			<input type="hidden" name="userId" value="${elem.userId}">
			<p><input type="submit" value="<fmt:message key="block/unblock" />"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="change_role">
			<input type="hidden" name="userId" value="${elem.userId}">
			<p><input type="submit" value="<fmt:message key="admin/client" />"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
	        <input type="hidden" name="command" value="add_money">
	        <input type="hidden" name="userId" value="${elem.userId}">
			<input name="money" required>
			<input type="submit" value="<fmt:message key="Add_money" />">
			<br>
	</form>
	</c:forEach>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp"><fmt:message key="Administration" /></a>
	<h3>${message}</h3>
</body>
</html>