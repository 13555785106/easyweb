<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无权访问</title>
</head>
<body>
	<center>
		<img src="${pageContext.request.contextPath}/images/delete-icon.png" />
	</center>
	<center style="font-size: 32px; font-weight: bolder;">你无权访问此页面</center>
</body>
</html>