<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3>腸活アプリ</h3>
	<c:choose>
		<c:when test="${users != null }">
		<p>ログインできました。</p>
		<a href="/the-gut-healthy/MainServlet">メイン画面</a>
		</c:when>
		<c:otherwise>
		<p>ログインできませんでした</p>
		<a href="/the-gut-healthy">TOP画面</a>
		</c:otherwise>
	</c:choose>
</body>
</html>