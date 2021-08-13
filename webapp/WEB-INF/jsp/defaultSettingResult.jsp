<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
<link rel="stylesheet" type="text/css" href="/the-gut-healthy/css/style.css">
</head>
<body>
	<div id="pagebody">
		<div id="header">
			<p>腸活アプリ</p>
		</div>
		<div id="main">
			<h3>デフォルト設定</h3>
			<c:out value="${msg }" />
			<br>
			<br>
			<a href="/the-gut-healthy/MainServlet" >戻る</a>
		</div>
	</div>
</body>
</html>