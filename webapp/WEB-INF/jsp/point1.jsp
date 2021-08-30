<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic, java.util.*, model.*, DAO.*, java.time.format.DateTimeFormatter, java.time.Duration, model.ScoreMessage"%>
<% 
   Map<MealGenre, ArrayList<Meal>> mealMap = (Map<MealGenre, ArrayList<Meal>>)request.getAttribute("mealMap");
   ArrayList<MealGenre> genreList =(ArrayList<MealGenre>)request.getAttribute("genreList");
   
%>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>腸活アプリ</title>
<link rel="stylesheet" type="text/css" href="/the-gut-healthy/css/style.css">
<style>
#point{
text-align: left;
}
</style>
</head>
<body>
	<div id="pagebody">
		<div id="header">
			<p>腸活アプリ</p>
		</div>
		<div id="main">
			<h1>腸活アプリの使い方</h1>
			<p>食事と食事のスキマ時間の確保を目的に、以下の機能を盛り込みました。</p>
			<div id="timeAndFood">
					<table border="2" style="border-collapse: collapse">
						
						<tr>
							<th> </th><th colspan="2">Plan</th><th colspan="2">Result</th>
						</tr>    
						         
						<tr>
							<th> </th><th>Time</th><th>Food</th><th>Time</th><th>Food</th>
						</tr>
						
						<tr>
								<th>朝食</th>
										<td>
											<input type="time" name="meal_time<%=9 %>"  form="my_form" value="07:00" />
										</td>
										<td>
										<select name="meal_name9" >	
												<option value=0>サンドウィッチ</option>
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
										<td>
											<input type="time" name="meal_time10"  form="my_form" value="07:35"/>
										</td>
										<td>
										<select name="meal_name10" >
													<option value=0>サンドウィッチ</option>

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
								<th>消化時間</th>
								<td>4時間0分</td>
								<td rowspan="3"> </td>
								<td>4時間0分</td>
								<td rowspan="3"> </td>
								
							</tr>
							<tr>
								<th>スキマ時間</th>
								<td>5時間0分</td>
								<td>3時間35分</td>
							</tr>
							<tr>
								<th>ポイント</th>
								<td>15</td>
								<td>-5</td>
							</tr>
						
							<tr class="snack">
								<th>間食</th>
										<td>
											<input type="time" name="meal_time11"  form="my_form" value="12:00"/>
										
										</td>
										<td>
										<select name="meal_name11" >
													<option value=0>ナッツ</option>
											
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
	
										<td>
													<input type="time" name="meal_time12"  form="my_form" value="11:10"/>
										
										</td>
										<td>
										<select name="meal_name12" >
													<option value=0>バナナ</option>
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
							<tr class="snack">
							<th>消化時間</th>
								<td>4時間0分</td>
								<td rowspan="1"> </td>
								<td>1時間0分</td>
								<td rowspan="1"> </td>
								
							</tr>
							<tr class="snack">
							<th>スキマ時間</th>
								<td></td>
								<td rowspan="1"> </td>
								<td></td>
								<td rowspan="1"> </td>
								
							</tr>
							<tr class="snack">
							<th>ポイント</th>
								<td></td>
								<td rowspan="1"> </td>
								<td></td>
								<td rowspan="1"> </td>
								
							</tr>
						
						
						<tr>
							<th>腸活偏差値</th>
							<td><span class="score">65</span></td>
							<td></td>
							<td><span class="score">45</span></td>
							<td>スキマ時間取りましょう！！
							</td>
						</tr>
						
					</table>
			</div>
			<br>
			<div id="point" >
			<h3>機能①消化時間・スキマ時間・ポイントの計算</h3>
			<ul>
			<li>Time・Foodを登録 → 消化時間の計算</li>
			<li>次のTime・Foodを登録 → スキマ時間・ポイントの計算</li>
			<li>腸活偏差値への反映</li>
			</ul>
			<h3>機能②リマインドメール</h3>
			<ul>
			<li>消化時間の終了時刻 → リマインドメールの送信</li>
			<li>胃腸の休憩を意識的に取得</li>
			</ul>

			<h3>機能③食事履歴、グラフ・カレンダーでの表示</h3>
			<ul>
			<li>カレンダー画面 → 過去の食事の確認・編集</li>
			<li>カレンダー・グラフで、食事傾向を確認</li>
			<li>自分の食事を客観視</li>
			</ul>
			</div>
			<br>
			<a href="/the-gut-healthy/Point2Servlet">腸活とは?</a>
			<br>
			<a href="/the-gut-healthy/MainServlet">Main画面へ戻る</a>
		
		</div>
	</div>
</body>
</html>