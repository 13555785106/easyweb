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
<!-- 注意css文件与js文件的引用顺序，被依赖的放在前面！ -->
<link rel="stylesheet" href="${baseUrl}theme/css/bootstrap.min.css">
<script src="${baseUrl}theme/js/jquery.js"></script>
<script src="${baseUrl}theme/js/bootstrap.min.js"></script>
<title>登录</title>
</head>
<body>
	<div class="container">
		<form method="post" role="form" class="form-inline">
			<div class="row">
				<div class="col-md-4 col-md-offset-4" style="text-align: center">
					<h2 class="text-primary">欢迎登录</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<c:forEach items="${exeErrors}" var="err">
						<c:out value="${err}" />
						<br />
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-md-offset-4" style="padding-left:24px;">
					<label for="account">账号：</label><span
						style="color: red; font-weight: bolder;">*</span> <input
						type="text" name="account" value="${reqParams.account}"
						class="form-control" /> 
				</div>
				<div class="col-md-3">
				${paramErrors.account[0]}
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-md-offset-4" style="padding-left:24px;">
					<label for="passwd">密码：</label><span
						style="color: red; font-weight: bolder;">*</span> <input
						type="password" name="passwd" value="${reqParams.passwd}"
						class="form-control" />
				</div>
				<div class="col-md-3">
				${paramErrors.passwd[0]}
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4" style="text-align: center">
					<input type="submit" value="确定" class="btn btn-danger"/>
				</div>
			</div>

		</form>
	</div>
</body>
</html>