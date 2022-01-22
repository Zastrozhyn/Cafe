<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="users" method="GET" action="./controller">
		<input type="hidden" name="command" value="viewuser">
		<p><input type="submit" value="users"></p>
	</form>
	<c:forEach var="elem" items="${users}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem}" />
		<br>
	</c:forEach>
	  <a href="${pageContext.request.contextPath}/jsp/newdish.jsp">ADD DISH</a>
	   <a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>