<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration"%>
<% PlanAndResult planAndResult = (PlanAndResult)request.getAttribute("planAndResult");
   long[] durationMinutes = (long[])request.getAttribute("durationMinutes");
   int[] score = (int[])request.getAttribute("score");
   long[] digestionMinutes = (long[])request.getAttribute("digestionMinutes");
   ArrayList<MealAct> mealActList = (ArrayList<MealAct>)request.getAttribute("mealActList");

   
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
	<h3><%=planAndResult.getPlanAndResultDate() %></h3>
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
							</td>
							<td>
							</td>
						<% } else { %>
							<td>
								<%=mealActList.get(j).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
							</td>
							<td>
								<%=mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getMealName()%>
							</td>
						
						<% } %>
							<%if(mealActList.get(j+1) == null){%>		
							<td>
							</td>
							<td>
							</td>
						<% } else { %>
							<td>
								<%=mealActList.get(j).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
							</td>
							<td>
								<%=mealDAO.selectMealByMealId(mealActList.get(j+1).getMealId()).getMealName()%>
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
							</td>
							<td>
							</td>
						<% } else { %>
							<td>
								<%=mealActList.get(6).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
							</td>
							<td>
								<%=mealDAO.selectMealByMealId(mealActList.get(6).getMealId()).getMealName()%>
							</td>						
						<% } %>
							<%if(mealActList.get(7) == null){%>		
							<td>
							</td>
							<td>
							</td>
						<% } else { %>
							<td>
								<%=mealActList.get(7).getActTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) %>
							</td>
							<td>
								<%=mealDAO.selectMealByMealId(mealActList.get(7).getMealId()).getMealName()%>
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


	
	
	

	<a href="/the-gut-healthy/CalendarServlet?year=<%=now.get(Calendar.YEAR)%>&month=<%=now.get(Calendar.MONTH) + 1 %>">カレンダー</a>
	<br>

</body>
</html>