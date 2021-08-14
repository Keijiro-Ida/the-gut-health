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
		<br>
		<br>
		<br>
		<div id="main">
			<c:choose>
				<c:when test="${users != null }">
				<p>ログインできました。</p>
				<a href="/the-gut-healthy/MainServlet">メイン画面</a>
				</c:when>
				<c:otherwise>
				<p>ログインできませんでした。</p>
				<a href="/the-gut-healthy">TOP画面</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>