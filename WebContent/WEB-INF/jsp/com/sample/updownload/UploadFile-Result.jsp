<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UploadFile</title>
</head>
<body>
<%=application.getRealPath("/")%><br/>
${reqResult.fileName}<br/>
${reqResult.fileSize}<br/>
${reqResult.path}<br/>
${reqResult.dateTime}<br/>
<a href="ListFile">文件列表</a>
</body>
</html>