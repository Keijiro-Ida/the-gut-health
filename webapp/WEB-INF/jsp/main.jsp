<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*"%>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)session.getAttribute("mealActList");
   
	
   GetMealGenreListLogic bo = new GetMealGenreListLogic();
   ArrayList<MealGenre> genreList = bo.execute();
   GetMealListLogic bo2 = new GetMealListLogic();
   Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
   for(MealGenre mealGenre : genreList){
	   ArrayList<Meal> mealList = bo2.execute(mealGenre.getMealGenreId());
	   mealMap.put(mealGenre, mealList);
	 
   }
   
   ThreeMealsAndSnackDAO dao = new ThreeMealsAndSnackDAO();
   ArrayList<ThreeMealsAndSnack> threeMealsList = dao.selectThreeMeals();
   MealDAO dao2 = new MealDAO();
   
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
			
			<% for(int k = 0; k < 8; k += 2) {%>
			<tr>
				
				<th><%=threeMealsList.get(k+1).getThreeMealsName() %></th>		
				<td>
					<%if(mealActList.get(k) == null){%>
					<input type="time" name="meal_time<%=threeMealsList.get(k).getThreeMealsId() %>" form="my_form" />
					<% } else { %>
					<input type="time" name="meal_time<%=threeMealsList.get(k).getThreeMealsId() %>" form="my_form" value=<%=mealActList.get(k).getActTime().getHour() + ":" + mealActList.get(k).getActTime().getMinute()  %>/>
					<% } %>
				</td>
				<td>
					<%if(mealActList.get(k) == null){%>
						<select name="meal_name<%=threeMealsList.get(k).getThreeMealsId() %>">
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
					<% } else { %>
						<select name="meal_name<%=threeMealsList.get(k).getThreeMealsId() %>">
							<option value=0><%= dao2.selectMealByMealId(mealActList.get(k).getMealId()). %></option>
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
					<% } %>
				</td>
				<td>

					<input type="time" name="meal_time<%=threeMealsList.get(k+1).getThreeMealsId() %>" form="my_form" />
				</td>
				<td>

					<select name="meal_name<%=threeMealsList.get(k+1).getThreeMealsId() %>">
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
			</tr>
			<tr>
				<th>消化時間</th><td>4:00</td><td rowspan="3"> </td><td>2:00</td><td rowspan="3"> </td>
			</tr>
			<tr>
				<th>スキマ時間</th><td>4:00</td><td>2:00</td>
			</tr>
			<tr>
				<th>スコア</th><td>30</td><td>30</td>
			</tr>
			
			<% } %>
			
			
			<tr>
				<th>TOTALスコア</th><td>100</td><td></td><td>85</td><td></td>
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