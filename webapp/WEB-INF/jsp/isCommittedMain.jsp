<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)session.getAttribute("mealActList");
   long[] durationMinutes = (long[])request.getAttribute("durationMinutes");
   int[] score = (int[])request.getAttribute("score");
   long[] digestionMinutes = (long[])request.getAttribute("digestionMinutes");
   String[] durationMinutes_str = (String[])request.getAttribute("durationMinutes_str");
   String[] digestionMinutes_str = (String[])request.getAttribute("digestionMinutes_str");
   Map<MealGenre, ArrayList<Meal>> mealMap = (Map<MealGenre, ArrayList<Meal>>)request.getAttribute("mealMap");
   ArrayList<MealGenre> genreList =(ArrayList<MealGenre>)request.getAttribute("genreList");
   
   MealDAO mealDAO = new MealDAO();
   ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
   FoodDAO foodDAO = new FoodDAO();
	
   Calendar now = Calendar.getInstance();
   String[] msg60 = {"がんばってますね", "がんばれ〜！","もう少し!","もう少しでツルツルの腸が!", "ファイト!!", "ガンバ！！", "あとちょっとです！！"};
   String[] msg80 = {"がんばりました","継続してきましょう！", "腸が喜んでいます!", "腸のレベルが上がりました"};
   String[] msg100 = {"素晴らしいです!", "よくがんばりました！", "腸が歓喜しています！", "すごい!継続しましょう！"};
   String[] msg120 ={"あなたの腸はアスリートレベルです","素晴らしい!!", "スゴッ", "ヤバッ", "腸が喜んでます!!", "あなたすごいですね。", "半端ない!","あなたの腸はつるつるです。"};
   String[] msg140 ={"あなたの腸は超人レベルです", "鬼ヤバッ", "半端ないッ!!", "あなたの腸は最強です"};
   int num60 = (int) (Math.random() * (msg60.length));
   int num80 = (int) (Math.random() * (msg80.length));
   int num100 = (int) (Math.random() * (msg100.length));
   int num120 = (int) (Math.random() * (msg120.length));
   int num140 = (int) (Math.random() * (msg140.length));
   
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
					<ul><li><a href="/the-gut-healthy/PointServlet">腸活</a></li>
						<li><a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダー</a></li>
						<li><a href="/the-gut-healthy/MonthGraphServlet">月スコアグラフ</a></li>
						<li><a href="/the-gut-healthy/DefaultSettingServlet">デフォルト設定</a></li>
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
							<th><%=threeMealsDAO.selectThreeMeals().get(j+1).getThreeMealsName() %></th>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getMealName() %>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(j+1).getMealId()).getMealName() %>
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
							<th>スコア</th>
							<% if(score[j] != 0) {%>
							
							<td><%=score[j] %> 点</td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[j+1] != 0) {%>
							<td><%=score[j+1] %> 点</td>
						
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
						<tr class="snack">
							<th><%=threeMealsDAO.selectThreeMeals().get(j+3).getThreeMealsName() %></th>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(j+2).getMealId()).getMealName() %>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(j+3).getMealId()).getMealName() %>
								
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
							<th>スコア</th>
							<% if(score[j+2] != 0) {%>
							<td><%=score[j+2] %> 点</td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[j+3] != 0) {%>
							<td><%=score[j+3] %> 点</td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
					<% } %>
					<tr>
							<th><%=threeMealsDAO.selectThreeMeals().get(9).getThreeMealsName() %></th>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(8).getMealId()).getMealName() %>
		
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
										<%=mealDAO.selectMealByMealId(mealActList.get(9).getMealId()).getMealName() %>
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
							<th>スコア</th>
							<% if(score[8] != 0) {%>
							<td><%=score[8] %> 点</td>
							<% } else { %>
							<td></td>
							<% } %>
							<% if(score[9] != 0) {%>
							<td><%=score[9] %> 点</td>
							<% } else { %>
							<td></td>
							<% } %>
						</tr>
					
						<tr class="snack">
							<th><%=threeMealsDAO.selectThreeMeals().get(11).getThreeMealsName() %></th>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(10).getMealId()).getMealName() %>
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
										<%=mealDAO.selectMealByMealId(mealActList.get(11).getMealId()).getMealName() %>
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
						<th>TOTALスコア</th>
						<td><%=planAndResult.getScorePlan() %> / 120 点</td>
						<td></td>
						<td><%=planAndResult.getScore() %> / 120 点</td>
						<td>
						<% if(planAndResult.getScore() > 120 ) { %>
							<%= msg140[num140] %>
							<% } else if(planAndResult.getScore() >= 100) {%>
							<%= msg120[num120] %>
							<% } else if(planAndResult.getScore() >= 80) {%>
							<%= msg100[num100] %>
							<% } else if(planAndResult.getScore() >= 60) {%>
							<%= msg80[num80] %>
							<% } else if(planAndResult.getScore() >= 40) {%>
							<%= msg60[num60] %>
						<% } %>
						
						</td>
					</tr>
					
				</table>
				<br>
				<button type="button" class="btn_main" onclick="location.href='/the-gut-healthy/MainServlet?isCommitted=0'" >修正</button>
				<br>
				<br>
				<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
			</div>
		</div>
	</div>
</body>

</html>
