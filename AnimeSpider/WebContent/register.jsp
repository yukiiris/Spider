<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
<h2>register</h2>


<form action="RegisterServlet" method="post">	
	name£º<input type="text" name="name"><br><br>
	password£º<input type="text" name="password"><br><br>
	verify£º<input type="text" name="verify"><br><br>
	<input type="submit" name="submit" value="register">
</form>
</body>
</html>