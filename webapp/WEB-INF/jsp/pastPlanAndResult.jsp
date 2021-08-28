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
			<h1><%= planAndResult.getPlanAndResultDate() %></h1>
			<div id="timeAndFood">
			<form action="/the-gut-healthy/PastPlanAndResultServlet" method="post" id="my_form">
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
									<input type="time" name="meal_time<%=j+1 %>"  form="my_form" />
									</td>
									<td>
									<select name="meal_name<%=j+1 %>" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time<%=j+1 %>" form="my_form"
										 value="<%=mealActList.get(j).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name<%=j+1 %>" >
											<option value=<%=mealActList.get(j).getMealId() %>><%=mealActList_str.get(j) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								
								<% } %>
									<%if(mealActList.get(j+1) == null){%>		
									<td>
									<input type="time" name="meal_time<%=j+2 %>"  form="my_form" />
									</td>
									<td>
									<select name="meal_name<%=j+2 %>" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time<%=j+2 %>" form="my_form"
										 value="<%=mealActList.get(j+1).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name<%=j+2 %>" >
											<option value=<%=mealActList.get(j+1).getMealId() %>><%=mealActList_str.get(j+1) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
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
									<input type="time" name="meal_time<%=j+3 %>"  form="my_form" />
									</td>
									<td>
									<select name="meal_name<%=j+3 %>" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time<%=j+3 %>" form="my_form"
										 value="<%=mealActList.get(j+2).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name<%=j+3 %>" >
											<option value=<%=mealActList.get(j+2).getMealId() %>><%=mealActList_str.get(j+2) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								
								<% } %>
									<%if(mealActList.get(j+3) == null){%>		
									<td>
									<input type="time" name="meal_time<%=j+4 %>"  form="my_form" />
									</td>
									<td>
									<select name="meal_name<%=j+4 %>" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time<%=j+4 %>" form="my_form"
										 value="<%=mealActList.get(j+3).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name<%=j+4 %>" >
											<option value=<%=mealActList.get(j+3).getMealId() %>><%=mealActList_str.get(j+3) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
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
							<th><%=threeMealsList.get(9) %></th>
								<%if(mealActList.get(8) == null){%>		
									<td>
									<input type="time" name="meal_time9"  form="my_form" />
									</td>
									<td>
									<select name="meal_name9" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time9" form="my_form"
										 value="<%=mealActList.get(8).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name9" >
											<option value=<%=mealActList.get(8).getMealId() %>><%=mealActList_str.get(8) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								
								<% } %>
									<%if(mealActList.get(9) == null){%>		
									<td>
									<input type="time" name="meal_time10"  form="my_form" />
									</td>
									<td>
									<select name="meal_name10" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time10" form="my_form"
										 value="<%=mealActList.get(9).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name10" >
											<option value=<%=mealActList.get(9).getMealId() %>><%=mealActList_str.get(9) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
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
							<td rowspan="3"> </td>
							<% if(digestionMinutes[9] != 0) { %>
							<td><%=digestionMinutes_str[9]%></td>
							<% } else { %>
							<td></td>
							<% } %>
							<td rowspan="3"> </td>
							
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
									<input type="time" name="meal_time11"  form="my_form" />
									</td>
									<td>
									<select name="meal_name11" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time11" form="my_form"
										 value="<%=mealActList.get(10).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name11" >
											<option value=<%=mealActList.get(10).getMealId() %>><%=mealActList_str.get(10) %></option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								
								<% } %>
									<%if(mealActList.get(11) == null){%>		
									<td>
									<input type="time" name="meal_time12"  form="my_form" />
									</td>
									<td>
									<select name="meal_name12" >
											<option value=0>選択してください</option>
											<% for(MealGenre mealGenre : genreList) {%>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
									</td>
								<% } else { %>
									<td>
										<input type="time" name="meal_time12" form="my_form"
										 value="<%=mealActList.get(11).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
									</td>
									<td>
									<select name="meal_name12" >
											<option value=<%=mealActList.get(11).getMealId() %>><%=mealActList_str.get(11) %></option>
											<% for(MealGenre mealGenre : genreList) { %>
												<optgroup label=<%=mealGenre.getMealGenreName() %>>
													<% for(int i = 0; i < mealMap.get(mealGenre).size(); i++)  {%>
											  			<option label=<%= mealMap.get(mealGenre).get(i).getMealName() %> value=<%= mealMap.get(mealGenre).get(i).getMealId() %>>
											 			 <%= mealMap.get(mealGenre).get(i).getMealName()%>
											 			 </option>
													<% } %>
												</optgroup>
											<% } %>
									</select>
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
							<td rowspan="1"> </td>
							
						</tr>
					
					
					<tr>
						<th>腸活偏差値</th>
						<td><span class="score"><%=planAndResult.getScorePlan() %> </span></td>
						<td></td>
						<td><span class="score"><%=planAndResult.getScore() %> </span></td>
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
				<button type="submit" name="planAndResultSubmit" value="0" form="my_form" class="btn_main" >保存</button>
				<button type="submit" name="planAndResultSubmit" value="1" form="my_form" class="btn_main">確定</button>
			</form>
			</div>
			
			<br>
			<br>
			<a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダーに戻る</a>
			<br>
			<a href="/the-gut-healthy/MainServlet">Main画面へ戻る</a>
			<br>
		</div>
	</div>
</body>
</html>