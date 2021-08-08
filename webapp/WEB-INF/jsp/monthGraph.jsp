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
<title>グラフ</title>
</head>
<body>
	<h3>折れ線グラフ</h3>
	
	<a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()-1 %>">前月</a>
		<a href="?year=<%=mc.getYear() %>&month=<%=mc.getMonth()+1 %>">翌月</a>
		
	<canvas id="myLineChart"></canvas>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
	<script>
	var mcYear = ${mc.getYear()};
	var mcMonth = ${mc.getMonth()};
	var title = mcYear + '年' + mcMonth + '月';
	var scoreList = ${scoreList};
	var dateList = ${dateList};
	var dateList_str = dateList.map(date => mcMonth + '月' + date + '日');
	var ctx = document.getElementById("myLineChart");
	
	var myLineChart = new Chart(ctx, {
		type: 'line',
		data: {
			labels: dateList_str,
			datasets:[
				{ 
					label:'日別スコア',
					data: scoreList,
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
		
		
		
		
	<a href="/the-gut-healthy/YearGraphServlet?year=<%=mc.getYear() %>&month=<%=mc.getMonth() %>">月別平均グラフ</a>
	<br>
	<a href="/the-gut-healthy/CalendarServlet?year=<%=mc.getYear()%>&month=<%=mc.getMonth()%>">カレンダー</a>
</body>
</html>