<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)session.getAttribute("mealActList");
   ArrayList<String> mealActList_str = (ArrayList<String>)request.getAttribute("mealActList_str");
   long[] durationMinutes = (long[])request.getAttribute("durationMinutes");
   int[] score = (int[])request.getAttribute("score");
   long[] digestionMinutes = (long[])request.getAttribute("digestionMinutes");
   String[] durationMinutes_str = (String[])request.getAttribute("durationMinutes_str");
   String[] digestionMinutes_str = (String[])request.getAttribute("digestionMinutes_str");
   Map<MealGenre, ArrayList<Meal>> mealMap = (Map<MealGenre, ArrayList<Meal>>)request.getAttribute("mealMap");
   ArrayList<MealGenre> genreList =(ArrayList<MealGenre>)request.getAttribute("genreList");
   ArrayList<String> threeMealsList = (ArrayList<String>)request.getAttribute("threeMealsList");

   Calendar now = Calendar.getInstance();
   
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
					<ul><li><a href="/the-gut-healthy/Point1Servlet">使い方</a></li>
						<li><a href="/the-gut-healthy/Point2Servlet">腸活とは？</a></li>
						<li><a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダー</a></li>
						<li><a href="/the-gut-healthy/MonthGraphServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1%>">偏差値グラフ</a></li>
					</ul>
			</div>
			<br>
			<br>
			<div id="timeAndFood">
				<table border="2" style="border-collapse: collapse">
					
					<tr>
						<th> </th><th colspan="2">Plan</th><th colspan="2">Result</th>
					</tr>    
					         
					<tr>
						<th> </th><th>Time</th><th>Food</th><th>Time</th><th>Food</th>
					</tr>
					
					
					<tr>
						<% for(int j = 0; j < 8; j += 4) {%>
							<th><%=threeMealsList.get(j+1) %></th>
								<%if(mealActList.get(j) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
									<%=mealActList.get(j).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(j) %>
									</td>
								
								<% } %>
									<%if(mealActList.get(j+1) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(j+1).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(j+1) %>
									</td>
								
								<% } %>
								
								
							
						</tr>
						<tr>
							<th>消化時間</th>
							<% if(digestionMinutes[j] != 0) {%>
							<td><%=digestionMinutes_str[j]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"> </td>
							<% if(digestionMinutes[j+1] != 0) { %>
							<td><%=digestionMinutes_str[j+1]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"> </td>
							
						</tr>
						<tr>
							<th>スキマ時間</th>
							<% if(durationMinutes[j] != 0) {%>
							<td><%=durationMinutes_str[j]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(durationMinutes[j+1] != 0) {%>
							<td><%=durationMinutes_str[j+1]%></td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
						<tr>
							<th>ポイント</th>
							<% if(score[j] != 0) {%>
							
							<td><%=score[j] %></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[j+1] != 0) {%>
							<td><%=score[j+1] %></td>
						
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
						<tr class="snack">
							<th><%=threeMealsList.get(j+3) %></th>
								<%if(mealActList.get(j+2) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(j+2).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(j+2)%>
									</td>
								
								<% } %>
									<%if(mealActList.get(j+3) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(j+3).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(j+3) %>
								
									</td>
								
								<% } %>
								
								
							
						</tr>
						<tr class="snack">
							<th>消化時間</th>
							<% if(digestionMinutes[j+2] != 0) {%>
							<td><%=digestionMinutes_str[j+2]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"> </td>
							<% if(digestionMinutes[j+3] != 0) { %>
							<td><%=digestionMinutes_str[j+3]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"> </td>
							
						</tr>
						<tr class="snack">
							<th>スキマ時間</th>
							<% if(durationMinutes[j+2] != 0) {%>
							<td><%=durationMinutes_str[j+2]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(durationMinutes[j+3] != 0) {%>
							<td><%=durationMinutes_str[j+3]%></td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
						<tr class="snack">
							<th>ポイント</th>
							<% if(score[j+2] != 0) {%>
							<td><%=score[j+2] %></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[j+3] != 0) {%>
							<td><%=score[j+3] %></td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
					<% } %>
					<tr>
							<th><%=threeMealsList.get(9)%></th>
								<%if(mealActList.get(8) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(8).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(8) %>
		
									</td>
								
								<% } %>
									<%if(mealActList.get(9) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(9).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(9) %>
									</td>
								
								<% } %>
						</tr>
						<tr>
							<th>消化時間</th>
							<% if(digestionMinutes[8] != 0) {%>
							<td><%=digestionMinutes_str[8]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"></td>
							<% if(digestionMinutes[9] != 0) { %>
							<td><%=digestionMinutes_str[9]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"></td>
							
						</tr>
						<tr>
							<th>スキマ時間</th>
							<% if(durationMinutes[8] != 0) {%>
							<td><%=durationMinutes_str[8]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(durationMinutes[9] != 0) {%>
							<td><%=durationMinutes_str[9]%></td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
						<tr>
							<th>ポイント</th>
							<% if(score[8] != 0) {%>
							<td><%=score[8] %></td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[9] != 0) {%>
							<td><%=score[9] %></td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
					
						<tr class="snack">
							<th><%=threeMealsList.get(11) %></th>
								<%if(mealActList.get(10) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(10).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(10) %>
									</td>
								
								<% } %>
									<%if(mealActList.get(11) == null){%>		
									<td>
									</td>
									<td>
									</td>
								<% } else { %>
									<td>
										<%=mealActList.get(11).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
									</td>
									<td>
										<%=mealActList_str.get(11)%>
									</td>
								
								<% } %>
								
								
							
						</tr>
						<tr class="snack">
						<th>消化時間</th>
							<% if(digestionMinutes[10] != 0) {%>
							<td><%=digestionMinutes_str[10]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="1"> </td>
							<% if(digestionMinutes[11] != 0) { %>
							<td><%=digestionMinutes_str[11]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="1">
							<% if(mealActList.get(11) != null){ %>
								<% if(mealActList.get(11).getMealId() == 15) {%>
								うまいっすよねぇ・・・・・
								<% } %>
							<% } %>
							 </td>
							
						</tr>
					
					
					<tr id="isCommittedScore">
						<th>腸活偏差値</th>
						<td><span class="score"><%=planAndResult.getScorePlan() %></span></td>
						<td></td>
						<td><span class="score"><%=planAndResult.getScore() %></span></td>
						<td>
							<% if(planAndResult.getScore() > 75 ) { %>
								<%= ScoreMessage.getMsg75() %>
							<% } else if(planAndResult.getScore() >= 65) {%>
								<%= ScoreMessage.getMsg65() %>
							<% } else if(planAndResult.getScore() >= 55) {%>
								<%= ScoreMessage.getMsg55() %>
							<% } else if(planAndResult.getScore() >= 45) {%>
								<%= ScoreMessage.getMsg45() %>
							<% } else if(planAndResult.getScore() >= 35) {%>
								<%= ScoreMessage.getMsg35() %>
							<% } %>
						</td>
					</tr>
					
				</table>
				<br>
				<button type="button" class="btn_main" onclick="location.href='/the-gut-healthy/MainServlet?isCommitted=0'" >修正</button>
				<br>
				<br>
				<a href="/the-gut-healthy/DefaultSettingServlet">デフォルト設定</a>
				<br>
				<a href="/the-gut-healthy/UpdateUsersServlet">ユーザー情報変更</a>
				<br>
				<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
			</div>
		</div>
	</div>
</body>

</html>
