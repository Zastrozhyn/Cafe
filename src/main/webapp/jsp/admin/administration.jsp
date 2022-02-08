<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<title><fmt:message key="Administration" /></title>
</head>
<body>
	  <a href="${abs}/jsp/admin/newdish.jsp"><fmt:message key="Add_dish" /></a>
	  <br>
	  <a href="${abs}/controller?command=view_user"><fmt:message key="Users" /></a>
	  <br>
	  <a href="${abs}/jsp/admin/admin_orders.jsp"><fmt:message key="Orders" /></a>
	  <br>	  
	  <h3>${message}</h3>
</body>
</html>