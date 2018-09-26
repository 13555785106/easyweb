<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UploadFile</title>
</head>
<body>

<form method="post" enctype="multipart/form-data">
		<table align="center">
			<caption>文件上传</caption>
			<tr>
				<td colspan="3"><c:forEach items="${exeErrors}" var="err">
						<c:out value="${err}" />
						<br />
					</c:forEach></td>
			</tr>
			<tr>
				<td>png文件：</td>
				<td><input type="file" name="file"></td>
				<td>${paramErrors.fileName[0]} ${paramErrors.fileSize[0]}</td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="ok"
					name="ok"></td>
			</tr>
		</table>
	</form>
</body>
</html>