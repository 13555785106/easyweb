<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>文件列表</title>
</head>
<body>
<div class="container">
    <div class="row" >
    <div class="col-md-6 col-md-offset-3">
	<table class="table table-bordered table-hover table-striped">
		<caption class="text-center">文件列表</caption>
		<tr>
			<th>文件名</th>
			<th>时间</th>
			<th>大小</th>
			<th class="text-center">
			<a href="UploadFile" class="btn btn-primary btn-xs" role="button">上传</a>
			</th>
		</tr>
		<c:forEach items="${reqResult}" var="doc">
			<tr>
				<td><a href="DownloadFile?id=${doc.id}">${doc.fileName}</a></td>
				<td><fmt:formatDate value="${doc.dateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${doc.fileSize}</td>
				<td  class="text-center">
				<a href="DelFile?id=${doc.id}"  class="btn btn-primary btn-xs" role="button">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>    
    </div>
    </div>
</div>

</body>
</html>