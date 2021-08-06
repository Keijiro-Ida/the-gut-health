package servlet;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FoodDAO;
import DAO.MealDAO;
import model.GetMealActLogic;
import model.GetPlanAndResultByIdLogic;
import model.MealAct;
import model.PlanAndResult;

/**
 * Servlet implementation class PastPlanAndResultServlet
 */
@WebServlet("/PastPlanAndResultServlet")
public class PastPlanAndResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int planAndResultId = Integer.parseInt(request.getParameter("planAndResultId"));
		GetPlanAndResultByIdLogic bo = new GetPlanAndResultByIdLogic();
		PlanAndResult planAndResult = bo.execute(planAndResultId);
		ArrayList<MealAct> mealActList = new ArrayList<>();
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

		MealDAO mealDAO = new MealDAO();
		FoodDAO foodDAO = new FoodDAO();

		long[] durationMinutes = new long[6];
		int[] score = new int[6];
		long[] digestionMinutes = new long[8];

		for (int j = 0; j < 6; j += 2) {
			if (mealActList.get(j) != null) {
				digestionMinutes[j] = foodDAO.selectDigestionMinutesFromId(
						mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getFoodId());
				if (mealActList.get(j + 2) != null) {
					durationMinutes[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 2).getActTime()).toMinutes();

				} else if (j <= 3 && mealActList.get(j + 2) == null && mealActList.get(j + 4) != null) {
					durationMinutes[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 4).getActTime()).toMinutes();
				}

				if (durationMinutes[j] == 0) {
					score[j] = 0;
				} else if (durationMinutes[j] >= digestionMinutes[j] + 180) {
					score[j] = 80;
				} else if (durationMinutes[j] >= digestionMinutes[j] + 120) {
					score[j] = 60;
				} else if (durationMinutes[j] >= digestionMinutes[j] + 60) {
					score[j] = 40;
				} else if (durationMinutes[j] >= digestionMinutes[j]) {
					score[j] = 30;
				} else if (durationMinutes[j] >= digestionMinutes[j] - 60) {
					score[j] = 15;
				} else {
					score[j] = 5;
				}

			}
			if (mealActList.get(j + 1) != null) {
				digestionMinutes[j + 1] = foodDAO.selectDigestionMinutesFromId(
						mealDAO.selectMealByMealId(mealActList.get(j + 1).getMealId()).getFoodId());
				if (mealActList.get(j + 3) != null) {
					durationMinutes[j + 1] = Duration
							.between(mealActList.get(j + 1).getActTime(), mealActList.get(j + 3).getActTime())
							.toMinutes();

				} else if (j + 1 <= 4 && mealActList.get(j + 3) == null && mealActList.get(j + 5) != null) {
					durationMinutes[j + 1] = Duration
							.between(mealActList.get(j + 1).getActTime(), mealActList.get(j + 5).getActTime())
							.toMinutes();
				}

				if (durationMinutes[j + 1] == 0) {
					score[j + 1] = 0;
				} else if (durationMinutes[j + 1] >= digestionMinutes[j + 1] + 180) {
					score[j + 1] = 80;
				} else if (durationMinutes[j + 1] >= digestionMinutes[j + 1] + 120) {
					score[j + 1] = 60;
				} else if (durationMinutes[j + 1] >= digestionMinutes[j + 1] + 60) {
					score[j + 1] = 40;
				} else if (durationMinutes[j + 1] >= digestionMinutes[j + 1]) {
					score[j + 1] = 30;
				} else if (durationMinutes[j + 1] >= digestionMinutes[j + 1] - 60) {
					score[j + 1] = 15;
				} else {
					score[j + 1] = 5;
				}

			}

		}
		if (mealActList.get(6) != null) {
			digestionMinutes[6] = foodDAO.selectDigestionMinutesFromId(
					mealDAO.selectMealByMealId(mealActList.get(6).getMealId()).getFoodId());
		}
		if (mealActList.get(7) != null) {
			digestionMinutes[7] = foodDAO.selectDigestionMinutesFromId(
					mealDAO.selectMealByMealId(mealActList.get(7).getMealId()).getFoodId());
		}

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("digestionMinutes", digestionMinutes);
		request.setAttribute("score", score);

		request.setAttribute("mealActList", mealActList);
		request.setAttribute("planAndResult", planAndResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastPlanAndResult.jsp");
		dispatcher.forward(request, response);
	}

}
