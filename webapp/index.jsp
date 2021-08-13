<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<form id="login" action="/the-gut-healthy/LoginServlet" method="post">
				<table>
				<tr>
				<th>メールアドレス:</th><th><input type="email" name="mail"></th>
				</tr>
				<tr>
				<th>パスワード(８文字):</th><th><input type="password" name="pass"></th>
				</tr>
				</table>
				<br>
				<input type="submit" class="btn_main" value="login" >
			</form>
			<br>
			<a href="/the-gut-healthy/SignUpServlet">新規登録</a>
		</div>
	</div>
</body>
</html>