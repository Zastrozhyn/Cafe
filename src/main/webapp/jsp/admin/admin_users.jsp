<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Users" /></title>
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
		<input type="hidden" name="command" value="view_user">
		<p><input type="submit" value="<fmt:message key="Users" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="deleted_user">
		<p><input type="submit" value="<fmt:message key="Deleted_users" />"></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_user">
		<input name="name" required>
		<input type="submit" value="<fmt:message key="Find_user" />">
	</form>
	<br>
	<table>
		<caption><fmt:message key="Users" /></caption>
		<tr><th><fmt:message key="Name"/></th>
		<th><fmt:message key="Last_name"/></th>
		<th><fmt:message key="Email"/></th>
		<th><fmt:message key="Phone"/></th>
		<th><fmt:message key="Status"/></th>
		<th><fmt:message key="Balance"/></th>
		<th><fmt:message key="Order_history"/></th>
		<th><fmt:message key="Role"/></th>
		<th><fmt:message key="Active"/></th>
		</tr>
		<c:forEach var="elem" items="${users}" varStatus="status">
			<tr>
			<td><c:out value="${elem.name}" /></td>
			<td><c:out value="${elem.lastName}" /></td>
			<td><c:out value="${elem.email}" /></td>
			<td><c:out value="${elem.phone}" /></td>
			<td><c:out value="${elem.account.status}" /></td>
			<td><c:out value="${elem.account.balance}" /></td>
			<td><c:out value="${elem.account.orderHistory}" /></td>
			<td><c:out value="${elem.role}" /></td>
			<td><c:out value="${elem.account.active}" /></td>		
			<td>
			<form method="POST" action="<c:url value="/controller"/>">
				<input type="hidden" name="command" value="block_user">
				<input type="hidden" name="userId" value="${elem.userId}">
				<p><input type="submit" value="<fmt:message key="block/unblock" />"></p>
			</form>
			<form method="POST" action="<c:url value="/controller"/>">
				<input type="hidden" name="command" value="change_role">
				<input type="hidden" name="userId" value="${elem.userId}">
				<p><input type="submit" value="<fmt:message key="admin/client" />"></p>
			</form>
			<form method="POST" action="<c:url value="/controller"/>">
		        <input type="hidden" name="command" value="add_money">
		        <input type="hidden" name="userId" value="${elem.userId}">
				<input name="money" size="3" required pattern="^[^-]\d*.?\d+$">
				<input type="submit" value="<fmt:message key="Add_money" />">
				<br>
			</form>
			</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h3>${message}</h3>
</body>
</html>