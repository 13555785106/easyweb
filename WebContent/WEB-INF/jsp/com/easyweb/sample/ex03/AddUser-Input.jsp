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
		<!--
	 此处设置不需要进行trim的字段，如果没有设置，表单数据将全部进行trim。每个字段使用方括号括起来，各个字段之间使用逗号分隔。可以不设置，默认都进行trim，即去掉字符串两边的空格。-->
		<input type="hidden" name="fieldNotNeedTrim"
			value="[passwd],[confirmPasswd]" />
		<!--由于在服务器端无法获知哪些字段是字符串数组类型，所以此处通过arrayFiels类指定。每个字段使用方括号括起来，各个字段之间使用逗号分隔。可以不设置，默认没有。-->
		<input type="hidden" name="arrayFields" value="[hobbies],[authorities]" />
		<!--字符串数组类型的数据合并成字符串时使用的分隔符。可以不设置，默认使用逗号。-->
		<input type="hidden" name="arraySeparator" value="," />
		<table align="center">
			<caption>添加账号</caption>
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
				<td><input type="password" name="passwd"
					value="${reqParams.passwd}" /></td>
				<td><span>${paramErrors.passwd[0]}</span></td>
			</tr>
			<tr>
				<th>确认密码：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><input type="password" name="confirmPasswd"
					value="${reqParams.confirmPasswd}" /></td>
				<td><span>${paramErrors.confirmPasswd[0]}</span></td>
			</tr>
			<tr>
				<th>生日：</th>
				<td><input type="text" name="birthday"
					value="${reqParams.birthday}" size="12" /></td>
				<td><span>${paramErrors.birthday[0]}</span></td>
			</tr>
			<tr>
				<th>性别：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><c:set var="defaultSex" value="${reqParams.sex}" /> <c:if
						test="${empty defaultSex}">
						<c:set var="defaultSex" value="男" />
					</c:if> <c:forEach var="sex" items="${fn:split('男,女',',')}">
						<c:set var="sexChecked" value="" />
						<c:if test="${defaultSex==sex}">
							<c:set var="sexChecked" value="checked" />
						</c:if>
						<label>${sex}</label>
						<input type="radio" name="sex" value="${sex}" ${sexChecked} />
					</c:forEach></td>
				<td><span>${paramErrors.sex[0]}</span></td>
			</tr>
			<tr>
				<th>工资：</th>
				<td><input type="text" name="salary"
					value="${reqParams.salary}" size="6" /></td>
				<td><span>${paramErrors.salary[0]}</span></td>
			</tr>
			<tr>
				<th>爱好：<span style="color: red; font-weight: bolder;">*</span></th>
				<td><c:forEach var="hobby" items="${fn:split('足球,篮球,排球',',')}">
						<c:set var="hobbyChecked" value="" />
						<c:if test="${fn:contains(reqParams.hobbies,hobby)}">
							<c:set var="hobbyChecked" value="checked" />
						</c:if>
						<label>${hobby}</label>
						<input type="checkbox" name="hobbies" value="${hobby}" ${hobbyChecked} />
					</c:forEach></td>
				<td><span>${paramErrors.hobbies[0]}</span></td>
			</tr>
			<tr>
				<th>权限</th>
				<td>
				<c:forEach items="${authTypes}" var="authTypeItem">
					${authTypeItem.key.name}<br/>
					    
						<c:forEach items="${authTypeItem.value}" var="authority">
						    <c:set var="authorityChecked" value="" />
						    <c:if test="${fn:contains(reqParams.authorities,authority.id)}">
						 		<c:set var = "authorityChecked" value="checked"/>
						 	</c:if>
						 	<c:set var = "inputType" value="radio"/>
						 	<c:if test="${authTypeItem.key.mode==1}">
						 		<c:set var = "inputType" value="checkbox"/>
						 	</c:if>
							<input type="${inputType}" name="authorities" value="${authority.id}" ${authorityChecked} />${authority.name}
						</c:forEach>
						<br/>
				</c:forEach>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="确定" /></td>
			</tr>
		</table>
	</form>
</body>
</html>