<%@page import="java.util.ArrayList"%>
<%@page import="com.AnimeSpider.vo.Anime"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Page</title>
</head>
<body>

<form action="find" method="post">
	anime:<input type="text" name="name"><br><br>
	<input type="submit" name="submit"value="search">
</form>
<%
	List<Anime> animes = (ArrayList<Anime>)request.getAttribute("animes");
	if (animes == null)
	{
%>
		<h4><%= 1 %></h4>
<% 
	}
	else
	{
		for (Anime anime : animes)
		{
	%>
			<h4><%=anime.getName() %></h4>
			<a href=<%=anime.getLink() %>><%= anime.getLink() %></a>
			<a href="follow?uid=<%=session.getAttribute("UID")%>&AName=<%=anime.getName()%>">
			Follow</a>
			<h4>===============================</h4>
	<% 
		}
	}
%>
</body>
</html>