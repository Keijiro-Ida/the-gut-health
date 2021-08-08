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
   
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
</head>
<body>
	<h3><%= planAndResult.getPlanAndResultDate() %></h3>
	<form action="/the-gut-healthy/PastPlanAndResultServlet" method="post" id="my_form">
		<table>
			
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
					<% if(digestionMinutes[j] != 0) {%>
					<td><%=digestionMinutes_str[j]%></td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="3"> </td>
					<% if(digestionMinutes[j+1] != 0) { %>
					<td><%=digestionMinutes_str[j+1]%></td>
					<% } else { %>
					<td><td>
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
				<tr>
					<th><%=threeMealsDAO.selectThreeMeals().get(j+3).getThreeMealsName() %></th>
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
									<option value=<%=mealActList.get(j+2).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(j+2).getMealId()).getMealName() %></option>
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
									<option value=<%=mealActList.get(j+3).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(j+3).getMealId()).getMealName() %></option>
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
					<% if(digestionMinutes[j+2] != 0) {%>
					<td><%=digestionMinutes_str[j+2]%></td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="3"> </td>
					<% if(digestionMinutes[j+3] != 0) { %>
					<td><%=digestionMinutes_str[j+3]%></td>
					<% } else { %>
					<td><td>
					<% } %>
					<td rowspan="3"> </td>
					
				</tr>
				<tr>
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
				<tr>
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
									<option value=<%=mealActList.get(8).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(8).getMealId()).getMealName() %></option>
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
									<option value=<%=mealActList.get(9).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(9).getMealId()).getMealName() %></option>
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
					<td><td>
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
			
				<tr>
					<th><%=threeMealsDAO.selectThreeMeals().get(11).getThreeMealsName() %></th>
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
									<option value=<%=mealActList.get(10).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(10).getMealId()).getMealName() %></option>
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
									<option value=<%=mealActList.get(11).getMealId() %>><%=mealDAO.selectMealByMealId(mealActList.get(11).getMealId()).getMealName() %></option>
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
					<% if(digestionMinutes[10] != 0) {%>
					<td><%=digestionMinutes_str[10]%></td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="1"> </td>
					<% if(digestionMinutes[11] != 0) { %>
					<td><%=digestionMinutes_str[11]%></td>
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
		<button type="submit" name="planAndResultSubmit" value="0" form="my_form" >保存</button>
		<button type="submit" name="planAndResultSubmit" value="1" form="my_form" >確定</button>
	</form>

	
	
	

	<a href="/the-gut-healthy/PointServlet">腸活</a>
	<br>
	<a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダー</a>
	<br>
	<a href="/the-gut-healthy/MainServlet">Main</a>
	<br>
	<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
</body>
</html>