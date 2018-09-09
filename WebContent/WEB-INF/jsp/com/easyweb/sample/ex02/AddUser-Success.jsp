<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加账号成功</title>
<style type="text/css">
table caption {
	font-size: 24px;
	font-weight: bolder;
}

table, td, th {
	border-collapse: collapse;
	border: 1px solid green;
}

th {
	text-align: left;
	color: black;
	background-color: LightGrey;
	padding-left: 8px;
}
</style>
</head>
<body>
	<table align="center">
		<caption>账号信息</caption>
		<tr>
			<th>ID：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.id}</td>
		</tr>
		<tr>
			<th>账号：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.account}</td>
		</tr>
		<tr>
			<th>密码：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.passwd}</td>
		</tr>
		<tr>
			<th>确认密码：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.confirmPasswd}</td>
		</tr>
		<tr>
			<th>生日：</th>
			<td><fmt:formatDate value="${reqResult.birthday}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>性别：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.sex}</td>
		</tr>
		<tr>
			<th>工资：</th>
			<td>${reqResult.salary}</td>
		</tr>
		<tr>
			<th>爱好：<span style="color: red; font-weight: bolder;">*</span></th>
			<td>${reqResult.hobbies}</td>
		</tr>
		<tr>
		<td colspan="2" align="center"><a href="ListUser">用户列表</a></td>
		</tr>
	</table>
</body>
</html>