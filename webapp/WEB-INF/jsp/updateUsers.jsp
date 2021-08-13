<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/the-gut-healthy/css/style.css">
<title>腸活アプリ</title>
</head>
<body>
	<div id="pagebody">
		<div id="header">
			<p>腸活アプリ</p>
		</div>
		<div id="main">
			<h3>ユーザー情報変更</h3>
			
			<form action="/the-gut-healthy/UpdateUsersServlet" method="post">
				<table>
					<tr>
					<th>メールアドレス:</th>
					<td><input type="email" name="mail"></td>
					</tr>
					<tr>
					<th>パスワード(8文字):</th>
					<td><input type="password" name="pass"></td>
					</tr>
					<tr>
					<th>新パスワード:</th>
					<td><input type="password" name="pass2"></td>
					</tr>
				</table>
				<br>
				<input type="submit" value="更新" class="btn_main">
				<br>
				<br>
				<a href="/the-gut-healthy/MainServlet">戻る</a>
			</form>
		</div>
	</div>
</body>
</html>