<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h2>Login</h2>
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
<form action="LoginServlet" method="post">
	Name£º<input type="text" name="name"><br><br>
	Password£º<input type="password" name="password"><br><br>
	<input type="submit" value="µÇÂ¼">
	<input type="reset" value="ÖØÖÃ"><br>
</form><br>
<a href="register.jsp">Login>>></a>
</body>
</html>