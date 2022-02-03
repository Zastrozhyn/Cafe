<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<link rel="stylesheet" href="${abs}/jsp/css/style.css">
<head>
<meta charset="UTF-8">
<title><fmt:message key="Menu" /></title>
</head>
<body>
	<table class="mini_table">
	<tr>
	<td class="bd-hide">
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="">
		<p><input type="submit" value="<fmt:message key="ALL_MENU" />"></p>
	</form>
	</td>
	<td class="bd-hide">
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="snack">
		<p><input type="submit" value="<fmt:message key="SNACK_MENU" />"></p>
	</form>
	</td>
	<td class="bd-hide">
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="desert">
		<p><input type="submit" value="<fmt:message key="DESERT_MENU" />"></p>
	</form>
	</td>
	<td class="bd-hide">
	<form method="GET" action="<c:url value="/controller"/>">
		<input type="hidden" name="command" value="menu">
		<input type="hidden" name="type" value="drink">
		<p><input type="submit" value="<fmt:message key="DRINK_MENU" />"></p>
	</form>
	</td>
	<td class="bd-hide">
	<c:if test="${admin}">
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="menu">
			<input type="hidden" name="type" value="deleted">
			<p><input type="submit" value="<fmt:message key="Deleted" />"></p>
		</form>
	</c:if>
	</td>
	</tr>
	</table>
	<h2 align="center"><fmt:message key="Menu" /></h2>
	<table>
		<tr><th><fmt:message key="Name"/></th>
		<th><fmt:message key="Description"/></th>
		<th><fmt:message key="Weight"/></th>
		<th><fmt:message key="Price"/></th>
		</tr>
		<c:forEach var="elem" items="${menu}" varStatus="status" begin="${begin}" end="${end}">
			<tr>
		    <td><c:out value="${elem.name}"/></td>
		    <td><c:out value="${elem.description}"/></td>
		    <td align="center"><c:out value="${elem.weight}"/></td>
		    <td align="center"><c:out value="${elem.price}"/></td>
		    <td class="bd-hide">
			<c:if test="${client}">
				<form name="add_to_order" method="POST" action="<c:url value="/controller"/>">
				 	<input type="hidden" name="command" value="add_to_order">
				 	<input type="hidden" name="dishId" value="${elem.id}">
				 	<input type="submit" value="<fmt:message key="Add_to_order" />" >
			 	</form>
			 </c:if>
			 <c:if test="${admin}">
			 	<form name="edit_dish" method="POST" action="<c:url value="/controller"/>">
				 	<input type="hidden" name="command" value="go_to_edit_dish">
				 	<input type="hidden" name="dishId" value="${elem.id}">	
				 	<input type="submit" value="<fmt:message key="Edit_dish" />" >
				</form>
		     </c:if>
		     </td></tr>
		</c:forEach>
	</table>
	<br>
	<fmt:message key="Page" /> ${pages}
	<br>
	<fmt:message key="Current_page" /> ${current_page}
	<c:if test="${pages>1 and current_page < pages}">
		<form  method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="pagination">
			<input type="hidden" name="next_page" value="1">	
			<input type="submit" value="next" >
		</form>
	</c:if>
	<c:if test="${pages>1 and current_page > 1}">
		<form  method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="pagination">
			<input type="hidden" name="next_page" value="-1">	
			<input type="submit" value="back" > 
		</form>	
	</c:if>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>