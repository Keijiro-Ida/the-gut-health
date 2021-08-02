package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreatePlanAndResultLogic;
import model.GetPlanAndResultByUsersLogic;
import model.MealAct;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostMealActLogic;
import model.PostPlanAndResult;
import model.UpdatePlanAndResultLogic;
import model.users.Users;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		GetPlanAndResultByUsersLogic bo = new GetPlanAndResultByUsersLogic();
		PlanAndResult planAndResult = bo.execute(users);

		if (planAndResult == null) {
			CreatePlanAndResultLogic bo2 = new CreatePlanAndResultLogic();
			PostPlanAndResult postPlanAndResult = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			planAndResult = bo2.execute(postPlanAndResult);

		}
		session.setAttribute("planAndResult", planAndResult);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PlanAndResult planAndResult = (PlanAndResult) session.getAttribute("planAndResult");

		int morningMealId = Integer.parseInt(request.getParameter("result_morning_meal"));
		LocalTime morningLocalTime = LocalTime.parse(request.getParameter("breakfast_time"),
				DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime morningTime = LocalDateTime.of(planAndResult.getDate(), morningLocalTime);
		PostMealAct postMealAct = new PostMealAct(planAndResult.getPlanAndResultId(), morningTime, morningMealId, 1);

		PostMealActLogic bo = new PostMealActLogic();
		MealAct mealAct = bo.execute(postMealAct);
		planAndResult.setActIdBreakfast(mealAct.getActId());
		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
		bo2.execute(planAndResult);

		System.out.println("planAndResult" + " " + planAndResult.getActIdBreakfast());
		session.setAttribute("planAndResult", planAndResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
