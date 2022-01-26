<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="view_user">
		<p><input type="submit" value="view all users"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_user">
		<input name="name" required>
		<input type="submit" value="find user">
		<br>
	</form>
	<c:forEach var="elem" items="${users}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<br>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="block_user">
			<input type="hidden" name="userId" value="${elem.userId}">
			<p><input type="submit" value="block/unblock"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="change_role">
			<input type="hidden" name="userId" value="${elem.userId}">
			<p><input type="submit" value="admin/client"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
	        <input type="hidden" name="command" value="add_money">
	        <input type="hidden" name="userId" value="${elem.userId}">
			<input name="money" required>
			<input type="submit" value="add money">
			<br>
	</form>
	</c:forEach>
	<br>
	<a href="${pageContext.request.contextPath}/jsp/admin/administration.jsp">ADMINISTRATION</a>
	<h3>${message}</h3>
</body>
</html>