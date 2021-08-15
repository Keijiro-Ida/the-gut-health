<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.PlanAndResult, model.MyCalendar, java.util.Calendar" %>
<% 
	MyCalendar mc = (MyCalendar)session.getAttribute("mc");
	

%>
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
		<div id="nav">
				<ul class="float">
					<li><a href="?year=<%=mc.getYear()-1 %>&month=<%=mc.getMonth()%>">前年</a></li>
					<li><a href="?year=<%=mc.getYear()+1%>&month=<%=mc.getMonth()%>">翌年</a></li>
					<li><a href="/the-gut-healthy/MonthGraphServlet">日別グラフ</a></li>
					<li><a href="/the-gut-healthy/CalendarServlet?year=<%=mc.getYear()%>&month=<%=mc.getMonth()%>">カレンダー</a></li>
				</ul>
			</div>
			<br>
			<br>
			<br>
			<h1><%=mc.getYear() %>年</h1>	
			<canvas id="myLineChart"></canvas>
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
		
			
			<br>
			<br>
			<a href="/the-gut-healthy/MainServlet" id="return">Main画面へ戻る</a>
			
		</div>
	</div>
		<script>
	var mcYear = ${mc.getYear()};
	var title = mcYear + '年' ;
	var monthList = ${monthList};
	var monthList_str = monthList.map( month => month + '月');
	console.log(monthList_str);

	
	var averageScoreList = ${averageScoreList};
	console.log(averageScoreList);
	
	var ctx = document.getElementById("myLineChart");
	
	var myLineChart = new Chart(ctx, {
		type: 'line',
		data: {
			labels: monthList_str,
			datasets:[
				{ 
					label:'月平均スコア',
					data: averageScoreList,
					borderColor: "rgba(100,100,255,1)",
					backGroundColor:"rgba(0, 0, 0, 0)"
				}
			],
		},
		options: {
	
			scales: {
				yAxes: [{
					ticks: {
						suggestedMax:140,
						suggestedMin: 0,
						stepSize: 10,
						callback: function(value, index, values){
							return value + '点'
						}
					}
				}]
			},
		}
		
		
		
	});
	
	
	</script>
</body>
</html>