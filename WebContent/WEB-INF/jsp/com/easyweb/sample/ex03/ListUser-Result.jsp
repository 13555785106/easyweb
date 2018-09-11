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
<link rel="stylesheet" type="text/css"
	href="${contextPath}theme/jquery-ui/jquery-ui.theme.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}theme/jquery-ui/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}theme/jquery-ui/jquery.datetimepicker.css">
<script src="${contextPath}theme/js/jquery.js"></script>
<script src="${contextPath}theme/js/bootstrap.min.js"></script>
<script src="${contextPath}theme/jquery-ui/jquery-ui.js"></script>
<script src="${contextPath}theme/jquery-ui/jquery.datetimepicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户列表</title>
</head>
<body>
<jsp:include page="/Menu" />
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
				<table class="table table-bordered table-hover table-striped">
					<caption class="text-center">用户列表</caption>
					<tr>
						<th>账号</th>
						<th>密码</th>
						<th>性别</th>
						<th>生日</th>
						<th>工资</th>
						<th>爱好</th>
						<th>权限</th>
						<c:if test="${authOfManageUsers>=10001}">
						<th class="text-center"><a href="AddUser"
							class="btn btn-primary btn-xs" role="button">添加</a></th>
						</c:if>
					</tr>
					<c:forEach items="${reqResult}" var="user">
						<tr>
							<td>${user.account}</td>
							<td>${user.passwd}</td>
							<td>${user.sex}</td>
							<td><fmt:formatDate value="${user.birthday}"
									pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatNumber value="${user.salary}" pattern="0.00" /></td>
							<td>${user.hobbies}</td>
							<td>
							<c:forEach items="${authTypes}" var="authTypeItem">
							<c:set var="showAuthority" value=""/>
							<c:forEach items="${authTypeItem.value}" var="val">
							<c:if test="${fn:contains(user.authorities,val.id)}">
								<c:set var="showAuthority" value="true"/>
							</c:if>
							</c:forEach>
							
							
							<c:if test="${showAuthority}">
							${authTypeItem.key.name}:
								<c:forEach items="${authTypeItem.value}" var="authority">
									<c:if test="${authority.id != 99999 && fn:contains(user.authorities,authority.id)}">
									${authority.name}
									</c:if>
								</c:forEach><br/>
							</c:if>
							</c:forEach>	
							</td>
							<c:if test="${authOfManageUsers>=10001}">
							<td class="text-center">
							<c:if test="${authOfManageUsers>=10002}">
							<a href="ChgUser?id=${user.id}"
								class="btn btn-primary btn-xs" role="button">修改</a> 
								</c:if>
								<c:if test="${authOfManageUsers>=10003}">
								<a
								href="DelUser/${user.id}" class="btn btn-primary btn-xs"
								role="button">删除</a>
								</c:if>
								</td>
							</c:if>	
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

</body>
</html>