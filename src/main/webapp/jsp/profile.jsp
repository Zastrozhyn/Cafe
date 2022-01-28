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
</head>
<body>
	<fmt:message key="Name" /> "${user.name}"
	<br>
	<fmt:message key="Last_name" /> "${user.lastName}"
	<br>
	<fmt:message key="Phone" /> "${user.phone}"
	<br>
	<fmt:message key="Email" /> "${user.email}"
	<br>
	<fmt:message key="Status" /> "${user.account.status}"
	<br>
	<fmt:message key="Active" /> "${user.account.active}"
	<br>
	<fmt:message key="Balance" /> "${user.account.balance}"
  
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
		<br>
	</form>
	
	<c:forEach var="elem" items="${orders}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<form method="GET" action="<c:url value="/controller"/>">
       		<input type="hidden" name="command" value="add_comment">
			<input name="orderId" type="hidden" value="${elem.id}">
			<input name="comment" value="${elem.comment}" required>
			<input type="submit" value="<fmt:message key="Add_comment" />">
		<br>
	</form>
		<br>		
		<br>
	</c:forEach>
	<h3>${message}</h3>
 	<p><a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a></p>
</body>
</html>