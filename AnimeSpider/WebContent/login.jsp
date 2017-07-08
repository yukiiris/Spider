<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<form action="LoginServlet" method="post">
	name/email：<input type="text" name="name"><br><br>
	password：<input type="password" name="password"><br><br>
	<input type="submit" value="login">
	<a href="register.jsp">Register</a>
</form>>
</body>
</html>