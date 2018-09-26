<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="baseUrl" value="/" />
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<span class="navbar-brand">
			&lt;- 分权分域演示 -&gt;
			</span>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<c:if test="${showManageUsers}">
					<li><a href="${baseUrl}sample/ex03/ListUser">用户管理</a></li>
				</c:if>
				<c:if test="${showManageFiles}">
					<li><a href="${baseUrl}sample/ex04/ListFile">文件管理</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>