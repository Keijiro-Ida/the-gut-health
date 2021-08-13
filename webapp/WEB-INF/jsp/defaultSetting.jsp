<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% 
   DefaultPlanAndResult defaultSetting = (DefaultPlanAndResult) request.getAttribute("defaultSetting");
   Map<MealGenre, ArrayList<Meal>> mealMap = (Map<MealGenre, ArrayList<Meal>>)request.getAttribute("mealMap");
   ArrayList<MealGenre> genreList =(ArrayList<MealGenre>)request.getAttribute("genreList");
   
   MealDAO mealDAO = new MealDAO();
   ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
   FoodDAO foodDAO = new FoodDAO();
   ArrayList<String> timeList = (ArrayList<String>)request.getAttribute("timeList");
   ArrayList<Integer> mealIdList = (ArrayList<Integer>)request.getAttribute("mealIdList");
   
   
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
	
			
			<form action="/the-gut-healthy/DefaultSettingServlet" method="post" id="my_form">
				<table>
					
					<tr>
						<th> </th><th colspan="2">Default設定</th>
					</tr>    
					         
					<tr>
						<th> </th><th>Time</th><th>Food</th>
					</tr>
					
					<%if(defaultSetting != null) { %>
						<% for(int j = 0; j < 12; j += 2) {%>
							<tr>
								<th><%=threeMealsDAO.selectThreeMeals().get(j+1).getThreeMealsName() %></th>
										<td>
										<% if( "".equals(timeList.get(j/2)) || timeList.get(j/2) == null) {%>
										<input type="time" name="meal_time<%=j+1 %>"  form="my_form" />
										<% } else { %>
										<input type="time" name="meal_time<%=j+1 %>"  form="my_form" value="<%=timeList.get(j/2) %>"/>
										<% } %>
										</td>
										<td>
										
										<select name="meal_name<%=j+1%>" >
											<% if(mealIdList.get(j/2) == null  || mealIdList.get(j/2) == 0) {%>
												<option value=0>選択してください</option>
											<% } else { %>
												<option value=<%=mealIdList.get(j/2) %>><%=mealDAO.selectMealByMealId(mealIdList.get(j/2)).getMealName()%></option>
											<% } %>
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
									
						<% } %>
					<% } else { %>
						<% for(int j = 0; j < 12; j += 2) {%>
							<tr>
								<th><%=threeMealsDAO.selectThreeMeals().get(j+1).getThreeMealsName() %></th>
										<td>
										<input type="time" name="meal_time<%=j+1 %>"  form="my_form" />
										</td>
										<td>
										
										<select name="meal_name<%=j+1%>" >
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
									
						<% } %>
					<% } %>
					
				</table>
				<br>
				
				<button type="submit" name="defaultSubmit" value="0" form="my_form" class="btn_main" >削除</button>
				<button type="submit" name="defaultSubmit" value="1" form="my_form" class="btn_main">登録</button>
			</form>
				
			<br>
			
			<a href="/the-gut-healthy/MainServlet">戻る</a>
		</div>
	</div>
</body>
</html>