<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
  <form name="authorization" method="POST" action="./controller">
  <input type="hidden" name="command" value="login">
  login:<input name="login" value="">
  <br/>
  <br/>
  password:<input name="password" type="password" value="">
  <br/>
  <p><input type="submit"></p>
  </form>
<a href="${pageContext.request.contextPath}/jsp/registration.jsp">REGISTRATION</a>
</body>
</html>