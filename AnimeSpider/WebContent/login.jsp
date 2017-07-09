<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<%
	request.setCharacterEncoding("GBK");
%>
<%
	List<String> info = (List<String>)request.getAttribute("info");
	if (info != null)
	{
		Iterator<String> iter = info.iterator();
		while (iter.hasNext())
		{
			%>
			<h4><%=iter.next() %></h4>
			<%
		} 
	}
%>
<form action="login" method="post">
	name/email£º<input type="text" name="name"><br><br>
	password£º<input type="password" name="password"><br><br>
	<input type="submit" value="login">
	<a href="register.jsp">Register</a>
</form>>
</body>
</html>