<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/imports.jspf" %>
<!DOCTYPE html>
<html>
<header>
	<c:import url="../includes/header.jsp" />
</header>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${abs}/jsp/css/style.css">
<title><fmt:message key="Main_page" /></title>
</head>
<body>
	<h3><fmt:message key="Hello" /> </h3>
	<h3>100BYN - 5%</h3>
	<h3>500BYN - 10%</h3>
	<h3>1000BYN - 20%</h3>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>