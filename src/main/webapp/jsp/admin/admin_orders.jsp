<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Orders" /></title>
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
		<table>
		<c:forEach var="elem" items="${orders}" varStatus="status">
			<tr><td><c:out value="${status.count}"/></td>
			<td><c:out value="${elem.userLogin}"/></td>
			<td><c:out value="${elem.date}"/></td>
			<td><c:out value="${elem.time}"/></td>
			<td><fmt:message key="Comment" />>>><c:out value="${elem.comment}"/></td>
			<td><fmt:message key="Description" />>>><c:out value="${elem.description}"/></td>
			<td><fmt:message key="Total" /><c:out value="${elem.totalCost}"/></td>
			<td><c:out value="${elem.payment}"/></td>
			<td><fmt:message key="Paid" />=<c:out value="${elem.paid}"/></td>
			<td>
			<form method="GET" action="<c:url value="/controller"/>">
	       		<input type="hidden" name="command" value="view_dishes">
				<input name="orderId" type="hidden" value="${elem.id}">
				<input name="admin_page" type="hidden" value="true">
				<input type="submit" value="<fmt:message key="View_dishes" />">
			</form>
			<form method="POST" action="<c:url value="/controller"/>">
				<input type="hidden" name="command" value="paid_order">
				<input type="hidden" name="orderId" value="${elem.id}">
			    <input type="submit" value="<fmt:message key="Paid" />">
			</form>
			<form method="POST" action="<c:url value="/controller"/>">
				<input type="hidden" name="command" value="delete_order">
				<input type="hidden" name="orderId" value="${elem.id}">
				<input type="submit" value="<fmt:message key="Delete_order" />">
			</form>
			</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<table>
		<c:forEach var="elem" items="${history_list_orders}" varStatus="status">
			<tr><td><c:out value="${status.count}"/></td>
			<td><c:out value="${elem.name}"/></td>
			<td><c:out value="${elem.description}"/></td>
			<td><c:out value="${elem.price}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h3>${message}</h3>
</body>
</html>