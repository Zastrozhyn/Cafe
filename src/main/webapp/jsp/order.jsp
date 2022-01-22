<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="elem" items="${orderList}" varStatus="status">
		<c:out value="${status.count}" />
		<c:out value="${elem.name}" />
		<c:out value="${elem.price}" />
		<br>
		 <form name="delete_from_order" method="GET" action="controller">
		 	<input type="hidden" name="command" value="delete_from_order">
		 	<input type="hidden" name="dishId" value="${elem.id}">
		 	<p><input type="submit" value="delete" ></p>
		 </form>
	</c:forEach>
  Total= ${total_cost}
  <form name="order" method="GET" action="controller">
  <input type="hidden" name="command" value="confirm_order">
  <input type="hidden" name="userId" value="${sessionScope.user.userId}">
  <br/>
  <br/>
  Description:<input name="description" required>
  <br/>
  <br/>
  Payment type:
  <br>
  <input type="radio" name="type" value="cash" required> cash <br>  
  <input type="radio" name="type" value="credit_card"> credit_card <br>  
  <input type="radio" name="type" value="account"> account <br> 
  <br/>
  <br/>
  Time:<input name="time" type="time" required>
  <br/>
  <br/>  
  <p><input type="submit" value="Confirm"></p>
  </form>
  <a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
  <h3>${message}</h3>
</body>
</html>