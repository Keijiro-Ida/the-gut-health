<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3>腸活アプリ</h3>
	
	<form id="login" action="/the-gut-healthy/LoginServlet" method="post">
		<table>
		<tr>
		<th>メールアドレス</th><th><input type="email" name="mail"></th>
		</tr>
		<tr>
		<th>パスワード</th><th><input type="password" name="pass"></th>
		</tr>
		</table>
		<input type="submit" class="btn_login" value="login">
	</form>
		<a href="/the-gut-healthy/SignUpServlet">新規登録</a>
</body>
</html>