<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="contextPath" value="/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 注意css文件与js文件的引用顺序，被依赖的放在前面！ -->
<link rel="stylesheet" href="${contextPath}theme/css/bootstrap.min.css">
<script src="${contextPath}theme/js/jquery.js"></script>
<script src="${contextPath}theme/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<jsp:include page="/Menu" />
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">权限类型</h3>
		</div>
		<div class="panel-body">
			按照权限之间的关系基本可以分为两类，一种是权限之间的存在级别关系；另一种是权限之间不相关的平级关系。本例演示了两种权限模型的基本实现。
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">1. 用户权限规则</h3>
		</div>
		<div class="panel-body">
			用户管理在权限上是按大小分级的，查看、添加、修改、删除权限依次增大，拥有了后面的权限也就拥有了前面的权限。例如你具有了修改用户的权限，你也就有了查看、添加用户的权限。
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">2. 文件权限规则</h3>
		</div>
		<div class="panel-body">
			文件管理在权限上是不分级的，查看、添加、修改、删除权限是平等的，权限各自独立。例如你具有了修改文件的权限，那么你仅具有修改文件的权限，不具备其它的权限。
		</div>
	</div>
</body>
</html>