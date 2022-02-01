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
<h2><fmt:message key="Hello" /> ${user.name}</h2>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>