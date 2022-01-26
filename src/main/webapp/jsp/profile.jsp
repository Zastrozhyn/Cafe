<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
	Name: "${user.name}"
	<br>
	Last Name: "${user.lastName}"
	<br>
	Phone: "${user.phone}"
	<br>
	email: "${user.email}"
	<br>
	status: "${user.account.status}"
	<br>
	active: "${user.account.active}"
	<br>
	balance: "${user.account.balance}"
  
	<form name="edit" method="GET" action="${pageContext.request.contextPath}/jsp/editprofile.jsp">
		<p><input type="submit" value="EDIT" ></p>
	</form>
	<form name="change_password" method="POST" action="${pageContext.request.contextPath}/jsp/change_password.jsp">
		<p><input type="submit" value="CHANGE PASSWORD" ></p>
	</form>
	<form name="delete" method="POST" action="${pageContext.request.contextPath}/jsp/delete_user.jsp">
		<p><input type="submit" value="DELETE ACCOUNT" ></p>
	</form>
	<form method="GET" action="<c:url value="/controller"/>">
        <input type="hidden" name="command" value="find_order_history">
		<input name="name" type="hidden" value="${user.login}">
		<input type="submit" value="ORDER HISTORY">
		<br>
	</form>
	
	<c:forEach var="elem" items="${orders}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<form method="GET" action="<c:url value="/controller"/>">
       		<input type="hidden" name="command" value="add_comment">
			<input name="orderId" type="hidden" value="${elem.id}">
			<input name="comment" value="${elem.comment}" required>
			<input type="submit" value="ADD COMMENT">
		<br>
	</form>
		<br>		
		<br>
	</c:forEach>
	<h3>${message}</h3>
 	<p><a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a></p>
</body>
</html>