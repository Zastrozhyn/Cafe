<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${Locale}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Administration" /></title>
</head>
<body>
	  <a href="${pageContext.request.contextPath}/jsp/admin/newdish.jsp"><fmt:message key="Add_dish" /></a>
	  <br>
	  <a href="${pageContext.request.contextPath}/jsp/admin/admin_users.jsp"><fmt:message key="Users" /></a>
	  <br>
	  <a href="${pageContext.request.contextPath}/jsp/admin/admin_orders.jsp"><fmt:message key="Orders" /></a>
	  <br>	  
	  <a href="${pageContext.request.contextPath}/jsp/mainPage.jsp"><fmt:message key="Main_page" /></a>
</body>
</html>