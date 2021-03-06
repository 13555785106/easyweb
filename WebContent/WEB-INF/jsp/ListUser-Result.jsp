<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url var="contextPath" value="/" />
<!-- 注意css文件与js文件的引用顺序，被依赖的放在前面！ -->
						
<link rel="stylesheet" href="${contextPath}theme/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${contextPath}theme/jquery-ui/jquery-ui.theme.css">
<link rel="stylesheet" type="text/css" href="${contextPath}theme/jquery-ui/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="${contextPath}theme/jquery-ui/jquery.datetimepicker.css">
<script src="${contextPath}theme/js/jquery.js"></script>
<script src="${contextPath}theme/js/bootstrap.min.js"></script>
<script src="${contextPath}theme/jquery-ui/jquery-ui.js"></script>
<script src="${contextPath}theme/jquery-ui/jquery.datetimepicker.js"></script>
<title>用户列表</title>
</head>
<body>

	<table class="table table-bordered table-hover table-striped">
		<caption class="text-center">用户列表</caption>
		<thead>
			<tr>
				<th>账号</th>
				<th>密码</th>
				<th>性别</th>
				<th>生日</th>
				<th>工资</th>
				<th>爱好</th>
				<th class="text-center"><a href="AddUser" class="btn btn-primary btn-xs" role="button">添加</a></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reqResult}" var="user">
				<tr>
					<td>${user.account}</td>
					<td>${user.passwd}</td>
					<td>${user.sex}</td>
					<td><fmt:formatDate value="${user.birthday}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatNumber value="${user.salary}" pattern="0.00" /></td>
					<td>${user.hobbies}</td>
					<td class="text-center">
					<a href="ChgUser?id=${user.id}"  class="btn btn-primary btn-xs" role="button">修改</a> 
					<a href="DelUser?id=${user.id}"  class="btn btn-primary btn-xs" role="button">删除</a>
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>