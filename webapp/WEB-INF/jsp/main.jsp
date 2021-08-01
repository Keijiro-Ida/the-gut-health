<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PlanAndResult, model.GetMealActLogic" %>
<% PlanAndResult planAndResult = (PlanAndResult)session.getAttribute("planAndResult");
   GetMealActLogic bo = new GetMealActLogic();
	


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
				<th>朝食</th>
				<td>
					<input type="time" name="plan_breakfast_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="plan_breakfast_text" form="my_form" />
				</td>
				<td>
					<input type="time" name="breakfast_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="breakfast_text" form="my_form" />
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
			
			
			<tr>
				<th>昼食</th>
				<td>
					<input type="time" name="plan_lunch_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="plan_lunch_text" form="my_form" />
				</td>
				<td>
					<input type="time" name="lunch_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="lunch_text" form="my_form" />
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
			
			
			<tr>
				<th>間食</th>
				<td>
					<input type="time" name="plan_snack_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="plan_snack_text" form="my_form" />
				</td>
				<td>
					<input type="time" name="snack_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="snack_text" form="my_form" />
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
			
			
			<tr>
				<th>夕食</th>
				<td>
					<input type="time" name="plan_dinner_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="plan_dinner_text" form="my_form" />
				</td>
				<td>
					<input type="time" name="dinner_time" form="my_form" />
				</td>
				<td>
					<input type="text" name="dinner_text" form="my_form" />
				</td>
			</tr>
			<tr>
				<th>TOTALスコア</th><td>100</td><td></td><td>85</td><td></td>
			</tr>
			
		</table>
		<input type="submit" name="planAndResultSubmit" form="my_form" value="登録" />
	</form>

	
	
	
	
	<p>actId:
	<p><%= planAndResult.getPlanAndResultId() %>
	</p>
	<a href="/the-gut-healthy/PointServlet">腸活</a>
	<br>
	<a href="/the-gut-healthy/CalenderServlet">カレンダー</a>
	<br>
	<a href="/the-gut-healthy/SettingServlet">Setting</a>
	<br>
	<a href="/the-gut-healthy/LogoutServlet">ログアウト</a>
</body>
</html>