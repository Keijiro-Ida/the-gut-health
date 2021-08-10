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
	<h3>ユーザー情報変更</h3>
	<c:choose>
		<c:when test="${errMsg != null }">
			<c:out value="${errMsg }"/>
		</c:when>
		<c:otherwise>
			ユーザー情報更新しました。
		</c:otherwise>
	</c:choose>
	<br>
	<a href="/the-gut-healthy/MainServlet">メイン画面</a>
</body>
</html>