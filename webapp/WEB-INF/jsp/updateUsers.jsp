<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3>ユーザー情報変更</h3>
	
	<form action="/the-gut-healthy/UpdateUsersServlet" method="post">
		<table>
			<tr>
			<th>メールアドレス</th>
			<td><input type="email" name="mail"></td>
			</tr>
			<tr>
			<th>パスワード</th>
			<td><input type="password" name="pass"></td>
			</tr>
			<tr>
			<th>新パスワード</th>
			<td><input type="password" name="pass2"></td>
			</tr>
		</table>
		<input type="submit" value="更新">
	
	</form>
</body>
</html>