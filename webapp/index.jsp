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
		<img src="top-m.jpg" width="800px">
			
			<h2>腸活アプリ</h2>
				<p>『全ての体調不良は腸の乱れが原因』とされ、近年腸の役割が注目されています。</p>
			<p>腸を健康に保つことが、老化防止や病気の予防、心の健康につながると言われています。</p>
			<p>この腸活アプリでは、食事と食事のスキマ時間に着目した腸活をサポートします。</p>
	
			<table align="center">
			<tr><td>テストユーザー: </td><td>aaa@aaa.com</td></tr>
			<tr><td>パスワード: </td><td>11223344</td></tr>
			</table>
			<form id="login" action="/the-gut-healthy/LoginServlet" method="post">
				<table align="center">
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
</body>
</html>