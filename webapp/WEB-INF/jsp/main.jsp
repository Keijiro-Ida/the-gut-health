<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)session.getAttribute("mealActList");
   MealDAO mealDAO = new MealDAO();
   ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
   FoodDAO foodDAO = new FoodDAO();
   
   long[] durationMinutes = new long[6];
   int[] score = new int[6];
   long[] digestionMinutes = new long[6];
   int totalScorePlan = 0;
   int totalScore =0;
   for(int j = 0; j < 6; j += 2) {
	   if(mealActList.get(j) != null) {
		    digestionMinutes[j] = foodDAO.selectDigestionMinutesFromId(mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getFoodId());
		    if(mealActList.get(j+2) != null){
		    	durationMinutes[j] = Duration.between(mealActList.get(j).getActTime(), mealActList.get(j+2).getActTime()).toMinutes();
		   
		   		if(durationMinutes[j] >= digestionMinutes[j] + 60){
				 	 score[j] = 40;
			 	 } else if (durationMinutes[j] >= digestionMinutes[j]){
				  	score[j] = 30;
			  	} else if (durationMinutes[j] >= digestionMinutes[j] -60) {
				  	score[j] = 15;
			  	} else {
				  	score[j] = 5;
			  	}
		    }
		   totalScorePlan += score[j];
	   }
	   if(mealActList.get(j+1) != null) {
		   digestionMinutes[j+1] = foodDAO.selectDigestionMinutesFromId(mealDAO.selectMealByMealId(mealActList.get(j+1).getMealId()).getFoodId());
		   if(mealActList.get(j+3) != null) {
		   durationMinutes[j+1] = Duration.between(mealActList.get(j+1).getActTime(), mealActList.get(j+3).getActTime()).toMinutes();
		   
			   if(durationMinutes[j+1] > digestionMinutes[j+1] + 60){
					  score[j+1] = 40;
				  } else if (durationMinutes[j+1] >= digestionMinutes[j+1]){
					  score[j+1] = 30;
				  } else if (durationMinutes[j+1] >= digestionMinutes[j+1] -60) {
					  score[j+1] = 15;
				  } else {
					  score[j+1] = 5;
				  }
		  	 }
		   totalScore += score[j+1];
	   }
	
   }
   
   GetMealGenreListLogic bo = new GetMealGenreListLogic();
   ArrayList<MealGenre> genreList = bo.execute();
   GetMealListLogic bo2 = new GetMealListLogic();
   Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
   for(MealGenre mealGenre : genreList){
	   ArrayList<Meal> mealList = bo2.execute(mealGenre.getMealGenreId());
	   mealMap.put(mealGenre, mealList);
	 
   }
   
   
   
   
   
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
					<% if(mealActList.get(j) != null && mealActList.get(j+2) != null) { %>
					<td> <%=durationMinutes[j]%>分</td>
					<% } else {%> 
					<td> </td>
					<% } %>
					<% if(mealActList.get(j+1) != null && mealActList.get(j+3) != null) { %>
					<td> <%=durationMinutes[j+1] %>分</td>
					<% } else {%> 
					<td> </td>
					<% } %>
				</tr>
				<tr>
					<th>スコア</th>
					<% System.out.println(score[j+1]); %>
					<td><%=score[j] %></td><td><%= score[j+1] %></td>
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
					<td><%=foodDAO.selectDigestionMinutesFromId(mealDAO.selectMealByMealId(mealActList.get(6).getMealId()).getFoodId()) %>分</td>
					<% } else { %>
					<td></td>
					<% } %>
					<td rowspan="1"> </td>
					<% if(mealActList.get(7) != null) { %>
					<td><%=foodDAO.selectDigestionMinutesFromId(mealDAO.selectMealByMealId(mealActList.get(7).getMealId()).getFoodId()) %>分</td>
					<% } else { %>
					<td><td>
					<% } %>
					<td rowspan="1"> </td>
					
				</tr>
			
			
			<tr>
				<th>TOTALスコア</th><td><%=totalScorePlan %></td><td></td><td><%=totalScore %></td><td></td>
			</tr>
			
		</table>
		<input type="submit" name="planAndResultSubmit" form="my_form" value="登録" />
	</form>

	
	
	

	<a href="/the-gut-healthy/PointServlet">腸活</a>
	<br>
	<a href="/the-gut-healthy/CalenderServlet">カレンダー</a>
	<br>
	<a href="/the-gut-healthy/SettingServlet">Setting</a>
	<br>
	<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
</body>
</html>