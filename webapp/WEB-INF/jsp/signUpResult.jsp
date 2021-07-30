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
		<c:when test="${signUp != null }">
		<p>新規登録完了しました</p>
		<p>メールアドレス:
		<c:out value="${signUp.mail }"/>
		</p>
		</c:when>
		<c:otherwise>
		<p>登録できませんでした。</p>
		</c:otherwise>
	</c:choose>
	<a href="/the-gut-healthy">TOP画面</a>
</body>
</html>