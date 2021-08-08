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
</head>
<body>
	<h3>年グラフ</h3>
		<a href="?year=<%=mc.getYear() -1 %>&month=<%=mc.getMonth()%>">前年</a>
		<a href="?year=<%=mc.getYear() + 1 %>&month=<%=mc.getMonth()%>">翌年</a>
		
	<canvas id="myLineChart"></canvas>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
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
					borderColor: "rgba(200,112,126,1)",
					backGroundColor:"rgba(0, 0, 0, 0)"
				}
			],
		},
		options: {
			title: {
				display: true,
				text: title
			},
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
	<a href="/the-gut-healthy/MonthGraphServlet">日別スコアグラフ</a>
	<br>
	<a href="/the-gut-healthy/CalenderServlet">カレンダー</a>
</body>
</html>