<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
<h2>Register</h2>
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

<form action="RegisterServlet" method="post">	
	Name£º<input type="text" name="name"><br><br>
	Password£º<input type="text" name="password"><br><br>
	ÔÙ´ÎÊäÈë£º<input type="text" name="confirm"><br><br>
	<input type="submit" name="submit" value="×¢²á">
	<input type="reset" name="reset"> 
</form>
</body>
</html>