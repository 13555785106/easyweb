<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>翻页展示</title>
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
	<center>翻页展示</center>
	<hr>
	<form method="post">
		<input type="hidden" name="pageNo" value="${reqResult.pageNo}" /> 
		<input type="hidden" name="pageSize" value="${reqResult.pageSize}" />
		<table align="center">
			<caption>用户列表</caption>
			<tr>
				<td colspan="7" align="center">账号:<input type="text"
					name="account" value="${formData.account}" /><input type="submit"
					value="搜索" name="search" /></td>
			</tr>
			<tr>
				<th>账号</th>
				<th>密码</th>
				<th>确认密码</th>
				<th>性别</th>
				<th>生日</th>
				<th>工资</th>
				<th>爱好</th>
			</tr>
			<c:forEach items="${reqResult.datas}" var="user">
				<tr>
					<td>${user.account}</td>
					<td>${user.passwd}</td>
					<td>${user.confirmPasswd}</td>
					<td>${user.sex}</td>
					<td><fmt:formatDate value="${user.birthday}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatNumber value="${user.salary}" pattern="0.00" /></td>
					<td>${user.hobbies}</td>
				</tr>
			</c:forEach>
			<tr>
			</tr>
		</table>
		<table align="center">
			<tr>
				<c:if test="${reqResult.pageCount>1 && reqResult.pageNo>0}">
					<td><input type="submit" value="首页" name="firstPage" /></td>
				</c:if>
				<c:if test="${reqResult.pageNo>0 && reqResult.pageNo<=reqResult.pageCount-1}">
					<td><input type="submit" value="前页" name="previousPage" /></td>
				</c:if>
				<td><c:if test="${reqResult.pageCount>0 }">第${reqResult.pageNo+1}页 /</c:if>
					共${reqResult.pageCount}页</td>
				<c:if test="${reqResult.pageNo>=0 && reqResult.pageNo<reqResult.pageCount-1}">
					<td><input type="submit" value="后页" name="nextPage" /></td>
				</c:if>
				<c:if
					test="${reqResult.pageCount>1 && reqResult.pageNo<reqResult.pageCount-1}">
					<td><input type="submit" value="尾页" name="lastPage" /></td>
				</c:if>
			</tr>
		</table>
	</form>
</body>
</html>