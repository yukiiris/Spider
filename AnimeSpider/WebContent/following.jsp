<%@page import="java.util.*"%>
<%@page import="com.AnimeSpider.vo.Anime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Following List Page</title>
</head>
<body>
<%
	List<Anime> animes = (ArrayList<Anime>)request.getAttribute("animes");
%>
<%
	if (animes == null)
	{
%>
		
<% 
	}
	else
	{
		for (Anime anime : animes)
		{
	%>
			<a href="details.jsp"><%=anime.getName() %></a>
			<h4>===============================</h4>
	<% 
		}
	}
%>
</body>
</html>