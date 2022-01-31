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
<title><fmt:message key="Profile" /></title>
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
	<fmt:message key="Name" />: ${user.name}
	<br>
	<fmt:message key="Last_name" />: ${user.lastName}
	<br>
	<fmt:message key="Phone" />: ${user.phone}
	<br>
	<fmt:message key="Email" />: ${user.email}
	<br>
	<fmt:message key="Status" />: ${user.account.status}
	<br>
	<fmt:message key="Active" />: ${user.account.active}
	<br>
	<fmt:message key="Balance" />: ${user.account.balance}
  
	<form name="edit" method="GET" action="${pageContext.request.contextPath}/jsp/editprofile.jsp">
		<p><input type="submit" value="<fmt:message key="Edit_profile" />" ></p>
	</form>
	<form name="change_password" method="POST" action="${pageContext.request.contextPath}/jsp/change_password.jsp">
		<p><input type="submit" value="<fmt:message key="Change_password" />" ></p>
	</form>
	<form name="delete" method="POST" action="${pageContext.request.contextPath}/jsp/delete_user.jsp">
		<p><input type="submit" value="<fmt:message key="Delete_user" />" ></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_order_history">
		<input name="name" type="hidden" value="${user.login}">
		<input type="submit" value="<fmt:message key="Order_history" />">
	</form>
	<br>
	<table>
		<c:forEach var="elem" items="${order_history}" varStatus="status">
			<tr><td><c:out value="${status.count}"/></td>
			<td><c:out value="${elem.date}"/></td>
			<td><fmt:message key="Total" /><c:out value="${elem.totalCost}"/></td>
			<td>
			<form method="GET" action="<c:url value="/controller"/>">
	       		<input type="hidden" name="command" value="add_comment">
				<input name="orderId" type="hidden" value="${elem.id}">
				<input name="comment" value="${elem.comment}" size="110" required>
				<input type="submit" value="<fmt:message key="Add_comment" />">
			</form>
			</td>
			<td>
			<form method="GET" action="<c:url value="/controller"/>">
	       		<input type="hidden" name="command" value="view_dishes">
				<input name="orderId" type="hidden" value="${elem.id}">
				<input name="admin_page" type="hidden" value="false">
				<input type="submit" value="<fmt:message key="View_dishes" />">
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
	<h3>${message}</h3>
 	<p><a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a></p>
</body>
</html>