<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.MyCalendar, model.PlanAndResult" %>
<% 
	MyCalendar mc = (MyCalendar) session.getAttribute("mc");
	int rows = mc.getData().length;
	PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=mc.getYear() %>年<%=mc.getMonth() %>月カレンダー</title>
  <link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">

</head>
<body>
	<div id="container">
	<h1><%=mc.getYear() %>年<%=mc.getMonth() %>月のカレンダー</h1>
	
	<p>
		<a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()-1 %>">前月</a>
		<a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()+1 %>">翌月</a>
	</p>
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
						<% if(mc.getPlanAndResult_cal()[i][j].getPlanAndResultId() == planAndResult.getPlanAndResultId()) {%>
							<td><a href="/the-gut-healthy/MainServlet" >
							<%= mc.getData()[i][j] %>
							</a>
							<% if(mc.getPlanAndResult_cal()[i][j].getIsCommitted() == true) {%>
						 		<br>
								<%= mc.getPlanAndResult_cal()[i][j].getScore() %>
							<% } %>
							</td>
						<% } else { %>
							<td><a href="/the-gut-healthy/PastPlanAndResult?planAndResultId=<%=mc.getPlanAndResult_cal()[i][j].getPlanAndResultId()%>" >
							<%= mc.getData()[i][j] %>
							</a>
							<% if(mc.getPlanAndResult_cal()[i][j].getIsCommitted() == true) {%>
						 		<br>
								<%= mc.getPlanAndResult_cal()[i][j].getScore() %>
							<% } %>
							</td>
						<% } %>
					<% } else { %>
						<td><%= mc.getData()[i][j] %></td>
					
					<% } %>
					
				<% } %>
			</tr>
			
		<% } %>
	
	
	</table>
	<a href="/the-gut-healthy/MonthGraphServlet">グラフ</a>
	<a href="/the-gut-healthy/MainServlet">Main</a>
	</div>
</body>
</html>