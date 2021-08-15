package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FoodDAO;
import DAO.MealActDAO;
import DAO.MealDAO;
import model.GetDurationMinutesLogic;
import model.GetMealActLogic;
import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.GetPlanAndResultByIdLogic;
import model.GetScoreLogic;
import model.GetThreeMealsNameLogic;
import model.Meal;
import model.MealAct;
import model.MealGenre;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostMealActLogic;
import model.UpdatePlanAndResultLogic;

/**
 * Servlet implementation class PastPlanAndResultServlet
 */
@WebServlet("/PastPlanAndResultServlet")
public class PastPlanAndResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int planAndResultId = Integer.parseInt(request.getParameter("planAndResultId"));

		GetPlanAndResultByIdLogic bo = new GetPlanAndResultByIdLogic();
		PlanAndResult planAndResult = bo.execute(planAndResultId);
		ArrayList<MealAct> mealActList = new ArrayList<>();
		GetMealActLogic bo2 = new GetMealActLogic();
		HttpSession session = request.getSession();

		String isCommitted_str = request.getParameter("isCommitted");

		if ("0".equals(isCommitted_str)) {
			planAndResult.setIsCommitted(false);
			UpdatePlanAndResultLogic bo3 = new UpdatePlanAndResultLogic();
			bo3.execute(planAndResult);
		}
		MealAct mealAct1 = bo2.execute(planAndResult.getActIdBreakfastPlan());
		MealAct mealAct2 = bo2.execute(planAndResult.getActIdBreakfast());
		MealAct mealAct3 = bo2.execute(planAndResult.getActIdAMSnackPlan());
		MealAct mealAct4 = bo2.execute(planAndResult.getActIdAMSnack());
		MealAct mealAct5 = bo2.execute(planAndResult.getActIdLunchPlan());
		MealAct mealAct6 = bo2.execute(planAndResult.getActIdLunch());
		MealAct mealAct7 = bo2.execute(planAndResult.getActIdPMSnackPlan());
		MealAct mealAct8 = bo2.execute(planAndResult.getActIdPMSnack());
		MealAct mealAct9 = bo2.execute(planAndResult.getActIdDinnerPlan());
		MealAct mealAct10 = bo2.execute(planAndResult.getActIdDinner());
		MealAct mealAct11 = bo2.execute(planAndResult.getActIdNightSnackPlan());
		MealAct mealAct12 = bo2.execute(planAndResult.getActIdNightSnack());
		mealActList.add(mealAct1);
		mealActList.add(mealAct2);
		mealActList.add(mealAct3);
		mealActList.add(mealAct4);
		mealActList.add(mealAct5);
		mealActList.add(mealAct6);
		mealActList.add(mealAct7);
		mealActList.add(mealAct8);
		mealActList.add(mealAct9);
		mealActList.add(mealAct10);
		mealActList.add(mealAct11);
		mealActList.add(mealAct12);

		MealDAO mealDAO = new MealDAO();
		FoodDAO foodDAO = new FoodDAO();

		long[] durationMinutes = new long[10];
		String[] durationMinutes_str = new String[10];
		int[] score = new int[10];
		long[] digestionMinutes = new long[12];
		String[] digestionMinutes_str = new String[12];

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic();
		durationMinutes = durationBO.execute(mealActList);
		for (int j = 0; j < 12; j++) {
			if (mealActList.get(j) != null) {
				digestionMinutes[j] = foodDAO.selectDigestionMinutesFromId(
						mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getFoodId());

			}

			GetScoreLogic scoreBO = new GetScoreLogic();
			score = scoreBO.execute(digestionMinutes, durationMinutes);

		}
		for (int i = 0; i < 10; i++) {
			if (durationMinutes[i] != 0) {
				long hour = durationMinutes[i] / 60;
				long minutes = durationMinutes[i] - hour * 60;
				durationMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}
		for (int i = 0; i < 12; i++) {
			if (digestionMinutes[i] != 0) {
				long hour = digestionMinutes[i] / 60;
				long minutes = digestionMinutes[i] - hour * 60;
				digestionMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);

		request.setAttribute("score", score);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		session.setAttribute("mealActList", mealActList);
		session.setAttribute("planAndResult", planAndResult);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();
		ArrayList<String> threeMealsList = threeMealsName.execute();
		request.setAttribute("threeMealsList", threeMealsList);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
		ArrayList<MealGenre> genreList = getMealGenreBO.execute();
		GetMealListLogic getMealListBO = new GetMealListLogic();
		Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
		for (MealGenre mealGenre : genreList) {
			ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
			mealMap.put(mealGenre, mealList);

		}
		request.setAttribute("mealMap", mealMap);
		request.setAttribute("genreList", genreList);
		if (planAndResult.getIsCommitted() == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastPlanAndResult.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsp/pastPlanAndResultIsCommitted.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PlanAndResult planAndResult = (PlanAndResult) session.getAttribute("planAndResult");
		ArrayList<MealAct> mealActList = (ArrayList<MealAct>) session.getAttribute("mealActList");
		PostMealActLogic bo = new PostMealActLogic();

		for (int i = 1; i <= 12; i++) {
			String mealId_String = request.getParameter("meal_name" + i);
			String meal_time_String = request.getParameter("meal_time" + i);

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
					planAndResult.setActIdAMSnackPlan(mealAct.getActId());
					break;
				case 4:
					planAndResult.setActIdAMSnack(mealAct.getActId());
					break;
				case 5:
					planAndResult.setActIdLunchPlan(mealAct.getActId());
					break;
				case 6:
					planAndResult.setActIdLunch(mealAct.getActId());
					break;
				case 7:
					planAndResult.setActIdPMSnackPlan(mealAct.getActId());
					break;
				case 8:
					planAndResult.setActIdPMSnack(mealAct.getActId());
					break;
				case 9:
					planAndResult.setActIdDinnerPlan(mealAct.getActId());
					break;
				case 10:
					planAndResult.setActIdDinner(mealAct.getActId());
					break;
				case 11:
					planAndResult.setActIdNightSnackPlan(mealAct.getActId());
					break;
				case 12:
					planAndResult.setActIdNightSnack(mealAct.getActId());
					break;
				}
				mealActList.remove(i - 1);
				mealActList.add(i - 1, mealAct);
			} else {
				mealActList.remove(i - 1);
				mealActList.add(i - 1, null);
			}
		}

		MealDAO mealDAO = new MealDAO();
		FoodDAO foodDAO = new FoodDAO();

		long[] durationMinutes = new long[10];
		String[] durationMinutes_str = new String[10];
		int[] score = new int[10];
		long[] digestionMinutes = new long[12];
		String[] digestionMinutes_str = new String[12];

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic();
		durationMinutes = durationBO.execute(mealActList);
		for (int j = 0; j < 12; j++) {
			if (mealActList.get(j) != null) {
				digestionMinutes[j] = foodDAO.selectDigestionMinutesFromId(
						mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getFoodId());

			}

			GetScoreLogic scoreBO = new GetScoreLogic();
			score = scoreBO.execute(digestionMinutes, durationMinutes);

		}
		for (int i = 0; i < 10; i++) {
			if (durationMinutes[i] != 0) {
				long hour = durationMinutes[i] / 60;
				long minutes = durationMinutes[i] - hour * 60;
				durationMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}
		for (int i = 0; i < 12; i++) {
			if (digestionMinutes[i] != 0) {
				long hour = digestionMinutes[i] / 60;
				long minutes = digestionMinutes[i] - hour * 60;
				digestionMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("score", score);

		int totalScorePlan = 0;
		int totalScore = 0;
		for (int i = 0; i < 10; i += 2) {
			totalScorePlan += score[i];
			totalScore += score[i + 1];

		}

		boolean isCommitted = false;
		String isCommitted_str = request.getParameter("planAndResultSubmit");
		if (isCommitted_str.equals("1")) {
			isCommitted = true;
		}
		planAndResult.setScorePlan(totalScorePlan);
		planAndResult.setScore(totalScore);
		planAndResult.setIsCommitted(isCommitted);

		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
		bo2.execute(planAndResult);

		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();
		ArrayList<String> threeMealsList = threeMealsName.execute();
		request.setAttribute("threeMealsList", threeMealsList);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
		ArrayList<MealGenre> genreList = getMealGenreBO.execute();
		GetMealListLogic getMealListBO = new GetMealListLogic();
		Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
		for (MealGenre mealGenre : genreList) {
			ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
			mealMap.put(mealGenre, mealList);

		}
		request.setAttribute("mealMap", mealMap);
		request.setAttribute("genreList", genreList);

		if (planAndResult.getIsCommitted() == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pastPlanAndResult.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsp/pastPlanAndResultIsCommitted.jsp");
			dispatcher.forward(request, response);
		}

	}
}
