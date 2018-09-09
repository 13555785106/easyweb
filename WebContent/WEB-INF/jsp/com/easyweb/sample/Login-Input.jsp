<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="baseUrl" value="/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form method="post">
		<table align="center">
			<caption>登录</caption>
			<tr>
				<td colspan="3"><c:forEach items="${exeErrors}" var="err">
						<c:out value="${err}" />
						<br />
					</c:forEach></td>
			</tr>
			<tr>
				<th>账号：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="text" name="account"
					value="${reqParams.account}" /></td>
				<td><span> ${paramErrors.account[0]}</span></td>
			</tr>
			<tr>
				<th>密码：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="password" name="passwd" value="${reqParams.passwd}" /></td>
				<td><span>${paramErrors.passwd[0]}</span></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="确定" /></td>
			</tr>
		</table>
	</form>
</body>
</html>