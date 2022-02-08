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
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="change_locale">
			<input type="hidden" name="Locale" value="ru_RU">
			<p><input style="float: right;" type="submit" value="RU"></p>
		</form>
		<form method="GET" action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="change_locale">
			<input type="hidden" name="Locale" value="en_US">
			<p><input style="float: right;" type="submit" value="EN"></p>
		</form>
	<br>
	<h3><fmt:message key="Hello" /> </h3>
	<h3>100BYN - 5%</h3>
	<h3>500BYN - 10%</h3>
	<h3>1000BYN - 20%</h3>
	<h3>${message}</h3>
</body>
<c:import url="../includes/footer.jsp" />
</html>