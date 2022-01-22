<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form name="add_dish" method="POST" action="controller">
  <input type="hidden" name="command" value="add_dish">
  Name:<input name="name" value="">
  <br/>
  <br/>
  Type:
  <input type="radio" name="type" value="drink"> drink <br>  
  <input type="radio" name="type" value="snack"> snack <br>  
  <input type="radio" name="type" value="desert"> desert <br> 
  <br/>
  <br/>
  Description:<input name="description" value="">
  <br/>
  <br/> 
  Price:<input name="price" value="">
  <br/>
  <br/>  
  Weight:<input name="weight" value="">
  <br/>
  <p><input type="submit" value="add"></p>
  </form>
  <a href="${pageContext.request.contextPath}/jsp/administration.jsp">ADMINISTRATION</a>
    <h3>${message}</h3>
</body>
</html>