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
<title>添加账号</title>
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


<script type="text/javascript">
	$(document).ready(
			function() {
				$("input[name='birthday']")
						.datepicker(
								{
									dateFormat : 'yy-mm-dd',
									monthNames : [ "1月", "2月", "3月", "4月",
											"5月", "6月", "7月", "8月", "9月",
											"10月", "11月", "12月" ],
									dayNamesMin : [ '日', "一", "二", "三", "四",
											"五", "六" ],
									prevText : "上一月",
									nextText : "下一月"
								});
			});
</script>
</head>
<body>
	<form method="post">
		<table align="center">
			<caption>添加账号</caption>
			<tr>
				<th>账号：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="text" name="account"
					value="${reqParams.account}" /></td>
				<td><span> ${reqErrors.account}</span></td>
			</tr>
			<tr>
				<th>密码：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="password" name="passwd" value="${reqParams.passwd}" /></td>
				<td><span>${reqErrors.passwd}</span></td>
			</tr>
			<tr>
				<th>确认密码：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="password" name="confirmPasswd"
					value="${reqParams.confirmPasswd}" /></td>
				<td><span>${reqErrors.confirmPasswd}</span></td>
			</tr>
			<tr>
				<th>生日：</th>
				<td><input type="text" name="birthday"
					value="${reqParams.birthday}" size="12" /></td>
				<td><span>${reqErrors.birthday}</span></td>
			</tr>
			<tr>
				<th>性别：<span style="color: red; font-weight: bolder;">*</span></th>
				<td>
				<c:set var="defaultSex" value="${reqParams.sex}" />
				
				<c:if test="${empty defaultSex}">
						<c:set var="defaultSex" value="男" />
				</c:if> 
				
					<c:forEach var="sex" items="${fn:split('男,女',',')}">
						<c:set var="sexChecked" value="" />
						<c:if test="${defaultSex==sex}">
							<c:set var="sexChecked" value="checked" />
						</c:if>
						<label>${sex}</label>
						<input type="radio" name="sex" value="${sex}" ${sexChecked} />
					</c:forEach></td>
				<td><span>${reqErrors.sex}</span></td>
			</tr>
			<tr>
				<th>工资：</th>
				<td><input type="text" name="salary" value="${reqParams.salary}" size="6" /></td>
				<td><span>${reqErrors.salary}</span></td>
			</tr>
			<tr>
				<th>爱好：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><c:forEach var="hobby" items="${fn:split('足球,篮球,排球',',')}">
						<c:set var="hobbyChecked" value="" />
						<c:if test="${fn:contains(reqParams.hobbies,hobby)}">
							<c:set var="hobbyChecked" value="checked" />
						</c:if>
						<label>${hobby}</label>
						<input type="checkbox" name="hobbies" value="${hobby}"
							${hobbyChecked} />
					</c:forEach></td>
				<td><span>${reqErrors.hobbies}</span></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="确定" /></td>
			</tr>
		</table>
	</form>
</body>
</html>