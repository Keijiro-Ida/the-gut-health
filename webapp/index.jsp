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
			<div id="main_text">
				<h1>腸活アプリ</h1>
				<p>『全ての体調不良は腸の乱れが原因』とされ、近年腸の役割が注目されています。</p>
			<p>腸を健康に保つことが、老化防止や病気の予防、心の健康につながると言われています。</p>
			<p>このアプリでは、ゲーム感覚で腸活を行えるように色々な要素を盛り込みました。</p>
				<br>
			<table>
			<tr><td>テストユーザー: </td><td>aaa@aaa.com</td></tr>
			<tr><td>パスワード: </td><td>11223344</td></tr>
			</table>
			<br>
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

			<br>
			<a href="/the-gut-healthy/SignUpServlet">新規登録</a>
			</div>
		</div>
	</div>
</body>
</html>