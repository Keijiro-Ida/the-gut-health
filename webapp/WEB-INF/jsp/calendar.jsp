<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MyCalendar, model.PlanAndResult" %>
<% 
	MyCalendar mc = (MyCalendar) session.getAttribute("mc");
	int rows = mc.getData().length;
	PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
	   int todayPlanAndResultId = (Integer)request.getAttribute("todayPlanAndResultId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=mc.getYear() %>年<%=mc.getMonth() %>月カレンダー</title>
<link rel="stylesheet" type="text/css" href="/the-gut-healthy/css/style.css">


</head>
<body>	
	<div id="pagebody">
		<div id="header">
			<p>腸活アプリ</p>
		</div>
		<div id="main">
			<div id="nav">
				<ul class="float">
					<li><a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()-1 %>">前月</a></li>
					<li><a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()+1 %>">翌月</a></li>
					<li><a href="/the-gut-healthy/MonthGraphServlet" >スコアグラフ</a></li>
					<li><a href="/the-gut-healthy/YearGraphServlet?year=<%=mc.getYear() %>&month=<%=mc.getMonth() %>">月平均グラフ</a></li>
				</ul>
			</div>
			<br>
			<br>
			<br>
			<br>
			<div id="container">
			
		
				<h1><%=mc.getYear() %>年<%=mc.getMonth() %>月</h1>
				<table>
					<tr>
						<th>日</th>
						<th>月</th>
						<th>火</th>
						<th>水</th>
						<th>木</th>
						<th>金</th>
						<th>土</th>
					</tr>
					<% for(int i = 0; i < rows; i++) {%>
						<tr>
							<% for(int j = 0; j < 7; j++) { %>
								
								<% if(mc.getPlanAndResult_cal()[i][j] != null) {%>
									<% if(mc.getPlanAndResult_cal()[i][j].getPlanAndResultId() == todayPlanAndResultId ) {%>
										<td><a href="/the-gut-healthy/MainServlet" class="today">
											<%= mc.getData()[i][j] %>
											<br>
											<span class="score">
											<%= mc.getPlanAndResult_cal()[i][j].getScore() %>点
											</span>
											<br>
											</a>
										</td>
									<% } else { %>
										<td><a href="/the-gut-healthy/PastPlanAndResultServlet?planAndResultId=<%=mc.getPlanAndResult_cal()[i][j].getPlanAndResultId()%>" class="other" >
											<%= mc.getData()[i][j] %>
											<br>
									 		<span class="score"><%= mc.getPlanAndResult_cal()[i][j].getScore() %>点
											</span>
											</a>
										</td>
									<% } %>
								<% } else { %>
									<td><%= mc.getData()[i][j] %>
									<br><br>
									</td>
									
								<% } %>
								
							<% } %>
						</tr>
						
					<% } %>
				
				
				</table>
				<br>
				<br>
				<a href="/the-gut-healthy/MainServlet" id="return">Main画面へ戻る</a>
				
			</div>
		</div>
	</div>

</body>
</html>