<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)session.getAttribute("mealActList");
   long[] durationMinutes = (long[])request.getAttribute("durationMinutes");
   int[] score = (int[])request.getAttribute("score");
   long[] digestionMinutes = (long[])request.getAttribute("digestionMinutes");
   Map<MealGenre, ArrayList<Meal>> mealMap = (Map<MealGenre, ArrayList<Meal>>)request.getAttribute("mealMap");
   ArrayList<MealGenre> genreList =(ArrayList<MealGenre>)request.getAttribute("genreList");
   
   MealDAO mealDAO = new MealDAO();
   ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
   FoodDAO foodDAO = new FoodDAO();
	
   Calendar now = Calendar.getInstance();
   
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3>腸活アプリ</h3>
	<form action="/the-gut-healthy/MainServlet" method="post" id="my_form">
		<table>
			
			<tr>
				<th> </th><th colspan="2">Plan</th><th colspan="2">Result</th>
			</tr>    
			         
			<tr>
				<th> </th><th>Time</th><th>Food</th><th>Time</th><th>Food</th>
			</tr>
			
			
			<tr>
				<% for(int j = 0; j < 6; j += 2) {%>
					<th><%=threeMealsDAO.selectThreeMeals().get(j+1).getThreeMealsName() %></th>
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
									<option value=<%=mealActList.get(j).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getMealName() %></option>
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
									<option value=<%=mealActList.get(j+1).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(j+1).getMealId()).getMealName() %></option>
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
					<% if(mealActList.get(j) != null) {%>
					<td><%=digestionMinutes[j]%>分</td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="3"> </td>
					<% if(mealActList.get(j+1) != null) { %>
					<td><%=digestionMinutes[j+1]%>分</td>
					<% } else { %>
					<td><td>
					<% } %>
					<td rowspan="3"> </td>
					
				</tr>
				<tr>
					<th>スキマ時間</th>
					<% if(durationMinutes[j] != 0) {%>
					<td><%=durationMinutes[j]%>分</td>
					<% } else { %>
					<td></td>
					<% } %>
					<% if(durationMinutes[j+1] != 0) {%>
					<td><%=durationMinutes[j+1]%>分</td>
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
			<% } %>
			
			<tr>
					<th><%=threeMealsDAO.selectThreeMeals().get(7).getThreeMealsName() %></th>
						<%if(mealActList.get(6) == null){%>		
							<td>
							<input type="time" name="meal_time7"  form="my_form" />
							</td>
							<td>
							<select name="meal_name7" >
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
								<input type="time" name="meal_time7" form="my_form"
								 value="<%=mealActList.get(6).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
							</td>
							<td>
							<select name="meal_name7" >
									<option value=<%=mealActList.get(6).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(6).getMealId()).getMealName() %></option>
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
							<%if(mealActList.get(7) == null){%>		
							<td>
							<input type="time" name="meal_time8"  form="my_form" />
							</td>
							<td>
							<select name="meal_name8" >
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
								<input type="time" name="meal_time8" form="my_form"
								 value="<%=mealActList.get(7).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>" />
							</td>
							<td>
							<select name="meal_name8" >
									<option value=<%=mealActList.get(7).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(7).getMealId()).getMealName() %></option>
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
				<tr>
				<th>消化時間</th>
					<% if(mealActList.get(6) != null) {%>
					<td><%=digestionMinutes[6]%>分</td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="1"> </td>
					<% if(mealActList.get(7) != null) { %>
					<td><%=digestionMinutes[7]%>分</td>
					<% } else { %>
					<td><td>
					<% } %>
					<td rowspan="1"> </td>
					
				</tr>
			
			
			<tr>
				<th>TOTALスコア</th>
				<td><%=planAndResult.getScorePlan() %> / 120 点</td>
				<td></td>
				<td><%=planAndResult.getScore() %> / 120 点</td>
				<td>
				<% if(planAndResult.getScore() == 120 ) { %>
				すごい！！
				<% } else if(planAndResult.getScore() >= 100) {%>
				がんばりました。
				<% } else if(planAndResult.getScore() >= 80) {%>
				もう少し!!
				<% } %>
				</td>
			</tr>
			
		</table>
		<input type="submit" name="planAndResultSubmit" form="my_form" value="登録" />
	</form>

	
	
	

	<a href="/the-gut-healthy/PointServlet">腸活</a>
	<br>
	<a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダー</a>
	<br>
	<a href="/the-gut-healthy/SettingServlet">Setting</a>
	<br>
	<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
</body>
</html>