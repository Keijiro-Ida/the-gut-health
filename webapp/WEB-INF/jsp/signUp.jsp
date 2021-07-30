<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活</title>
</head>
<body>
	<h3>腸活アプリ</h3>
	<form action="/the-gut-healthy/SignUpServlet" method="post">
		<table>
			<tr>
			<th>メールアドレス:</th><th><input type="email" name="mail"></th>
			</tr>
			<tr>
			<th>パスワード:</th><th><input type="password" name="pass1"></th>
			</tr>
			<tr>
			<th>パスワード(確認):</th><th><input type="password" name="pass2"></th>
			</tr>
		</table>
		<input type="submit" class=btn_signUp value="新規登録">
	</form>
	<a href="/the-gut-healthy">TOP画面</a>
</body>
</html>