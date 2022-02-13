<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Order" /></title>
</head>
<body>
	<p><a href="${abs}/jsp/menu.jsp"><fmt:message key="Menu" /></a></p>
	<br>
	<br>
	<c:forEach var="elem" items="${list_dish}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem.name}" />
		<c:out value="${elem.price}" />
		<br>
	<form method="POST" action="<c:url value="/controller"/>">
		 	<input type="hidden" name="command" value="delete_from_order">
		 	<input type="hidden" name="dishId" value="${elem.id}">
		 	<p><input type="submit" value="<fmt:message key="Delete" />" ></p>
		 </form>
	</c:forEach>
	<fmt:message key="Total" /> ${total_cost}
	<br>
	<fmt:message key="Discount" /> ${user.account.status.discount} %
	<form method="POST" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="confirm_order">
		<input type="hidden" name="userId" value="${sessionScope.user.login}">
		<br/>
		<br/>
		<fmt:message key="Description" /><input name="description" size="40"  required>
		<br/>
		<br/>
		<fmt:message key="Payment_type" />
		<br>
		<input type="radio" name="type" value="cash" checked> <fmt:message key="Cash" /> <br>  
		<input type="radio" name="type" value="credit_card"> <fmt:message key="Credit_card" /> <br>  
		<input type="radio" name="type" value="account"> <fmt:message key="Account" /> <br> 
		<br/>
		<br/>
		<fmt:message key="Time" /><input name="time" type="time" required>
		<br/>
		<br/>  
		<p><input type="submit"></p>
	</form>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>