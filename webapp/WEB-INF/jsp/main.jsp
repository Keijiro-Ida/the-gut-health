<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic" %>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   GetMealActLogic bo = new GetMealActLogic();
	


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3>腸活アプリ</h3>
	<p>actId:
	<p><%= planAndResult.getPlanAndResultId() %>
	</p>
	<a href="/the-gut-healthy/CalenderServlet">カレンダー</a>
	<br>
	<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
</body>
</html>