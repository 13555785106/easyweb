<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="com.easyweb.validator.*" %>
<%@ page import="com.sample.db.DB" %>
<%@ page import="com.sample.db.dac.*" %>
<%@ page import="com.sample.db.model.*" %>
<%@ page import="org.apache.commons.dbutils.handlers.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body>
<%=AuthorityDac.getInstance().allAuths()%>
</body>
</html>