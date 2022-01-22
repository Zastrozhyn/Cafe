<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
  <form name="registration" method="POST" action="controller">
  <input type="hidden" name="command" value="registration">
  Name:<input name="name" value="">
  <br/>
  <br/>
  Last Name:<input name="last_name" value="">
  <br/>
  <br/>
  Phone:<input name="phone" value="">
  <br/>
  <br/> 
  email:<input type="email" name="email" value="">
  <br/>
  <br/>  
  login:<input name="login" value="">
  <br/>
  <br/>  
  password:<input name="password" type="password" value="">
  <br/>
  <br/>
  confirm password:<input name="confirm_password" type="password" value="">
  <p><input type="submit"></p>
  </form>
  <h3>${registration_result}</h3>
  <a href="${pageContext.request.contextPath}/jsp/mainPage.jsp">MAIN PAGE</a>
</body>
</html>