<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GET方式传递中文</title>
</head>
<body>
	<%
		String name = URLEncoder.encode("肖俊峰","UTF-8");
		String sex = URLEncoder.encode("男","UTF-8");
	%>
	<a href="UTF8Parameter?name=<%=name%>&sex=<%=sex%>">UTF8Parameter</a>
</body>
</html>