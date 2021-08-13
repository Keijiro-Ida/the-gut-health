<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活</title>
<link rel="stylesheet" type="text/css" href="/the-gut-healthy/css/style.css">
</head>
<body>
	<div id="pagebody">
		<div id="header">
			<p>腸活アプリ</p>
		</div>
		<div id="main">
		<form action="/the-gut-healthy/SignUpServlet" method="post">
			<table>
				<tr>
				<th>メールアドレス:</th><th><input type="email" name="mail"></th>
				</tr>
				<tr>
				<th>パスワード(8文字):</th><th><input type="password" name="pass1"></th>
				</tr>
				<tr>
				<th>パスワード(確認):</th><th><input type="password" name="pass2"></th>
				</tr>
			</table>
			<br>
			<input type="submit" class="btn_main" value="新規登録">
			<br>
		</form>
		<br>
		<a href="/the-gut-healthy">TOP画面</a>
		</div>
	</div>
</body>
</html>