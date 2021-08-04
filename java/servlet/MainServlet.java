package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MealActDAO;
import model.CreatePlanAndResultLogic;
import model.GetMealActLogic;
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
		ArrayList<MealAct> mealActList = new ArrayList<>();
		if (planAndResult == null) {
			CreatePlanAndResultLogic bo2 = new CreatePlanAndResultLogic();
			PostPlanAndResult postPlanAndResult = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			planAndResult = bo2.execute(postPlanAndResult);

		}
		GetMealActLogic bo3 = new GetMealActLogic();
		MealAct mealAct1 = bo3.execute(planAndResult.getActIdBreakfastPlan());
		MealAct mealAct2 = bo3.execute(planAndResult.getActIdBreakfast());
		MealAct mealAct3 = bo3.execute(planAndResult.getActIdLunchPlan());
		MealAct mealAct4 = bo3.execute(planAndResult.getActIdLunch());
		MealAct mealAct5 = bo3.execute(planAndResult.getActIdSnackPlan());
		MealAct mealAct6 = bo3.execute(planAndResult.getActIdSnack());
		MealAct mealAct7 = bo3.execute(planAndResult.getActIdDinnerPlan());
		MealAct mealAct8 = bo3.execute(planAndResult.getActIdDinner());
		mealActList.add(mealAct1);
		mealActList.add(mealAct2);
		mealActList.add(mealAct3);
		mealActList.add(mealAct4);
		mealActList.add(mealAct5);
		mealActList.add(mealAct6);
		mealActList.add(mealAct7);
		mealActList.add(mealAct8);

		session.setAttribute("mealActList", mealActList);
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
		ArrayList<MealAct> mealActList = (ArrayList<MealAct>) session.getAttribute("mealActList");
		PostMealActLogic bo = new PostMealActLogic();

		for (int i = 1; i <= 8; i++) {
			String mealId_String = request.getParameter("meal_name" + i);
			String meal_time_String = request.getParameter("meal_time" + i);
			System.out.println("mealId_String" + i + " " + mealId_String);
			System.out.println("meal_time_String" + i + " " + meal_time_String);
			if (!("0".equals(mealId_String)) && !("".equals(meal_time_String))
					&& mealId_String != null && meal_time_String != null) {
				int mealId = Integer.parseInt(mealId_String);
				LocalTime meal_LocalTime = LocalTime.parse(meal_time_String,
						DateTimeFormatter.ofPattern("HH:mm"));
				LocalDateTime meal_LocalDateTime = LocalDateTime.of(planAndResult.getPlanAndResultDate(),
						meal_LocalTime);
				PostMealAct postMealAct = new PostMealAct(planAndResult.getPlanAndResultId(), meal_LocalDateTime,
						mealId,
						i);
				MealActDAO mealActDAO = new MealActDAO();
				mealActDAO.deleteMealAct(planAndResult.getPlanAndResultId(), i);
				MealAct mealAct = bo.execute(postMealAct);
				switch (i) {
				case 1:
					planAndResult.setActIdBreakfastPlan(mealAct.getActId());
					break;
				case 2:
					planAndResult.setActIdBreakfast(mealAct.getActId());
					break;
				case 3:
					planAndResult.setActIdLunchPlan(mealAct.getActId());
					break;
				case 4:
					planAndResult.setActIdLunch(mealAct.getActId());
					break;
				case 5:
					planAndResult.setActIdSnackPlan(mealAct.getActId());
					break;
				case 6:
					planAndResult.setActIdSnack(mealAct.getActId());
					break;
				case 7:
					planAndResult.setActIdDinnerPlan(mealAct.getActId());
					break;
				case 8:
					planAndResult.setActIdDinner(mealAct.getActId());
					break;
				}
				mealActList.remove(i - 1);
				mealActList.add(i - 1, mealAct);
			}
		}
		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
		bo2.execute(planAndResult);

		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
