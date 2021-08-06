<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.PlanAndResult, model.MyCalendar" %>
<% 
	MyCalendar mc = (MyCalendar)session.getAttribute("mc");
	ArrayList<String> dataList = (ArrayList<String>)request.getAttribute("dataList");
	ArrayList<Integer> scoreList = (ArrayList<Integer>)request.getAttribute("scoreList");
	
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
	var ctx = document.getElementByid("myLineChart");
	var myLineChart = new Chart(ctx, {
		type: 'line',
		data: {
			labels:[
			<% for(int i = 0 ; i < dataList.size(); i ++) { %>
			'<%=dataList.get(i) %>'
			<% if(i != dataList.size()- 1) {%>
			,
			<% }} %> ],
			datasets:[
				{ 
					label:'スコア',
					data:[
						<% for(int i = 0 ; i < scoreList.size(); i ++) { %>
						<%=scoreList.get(i) %>
						<% if(i != scoreList.size()- 1) {%>
						,
						<% }} %> ],
					borderCoor: "rgba(255, 0, 0, 1)",
					backGroundColor:"rgba(0, 0, 0, 0)"
					
					
				}
			],
		},
		options: {
			title: {
				display: true,
				text:''
			},
			scales: {
				yAxes: [{
					ticks: {
						suggestedMax:120,
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
		
		
		
		
	<a href="/the-gut-healthy/YearGraphServlet">Year Graph</a>
	<br>
	<a href="/the-gut-healthy/CalenderServlet">Calender</a>
</body>
</html>