package servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
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
import model.CreatePlanAndResultLogic;
import model.GetMealActLogic;
import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.GetPlanAndResultByUsersLogic;
import model.Meal;
import model.MealAct;
import model.MealGenre;
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

		session.setAttribute("mealActList", mealActList);
		session.setAttribute("planAndResult", planAndResult);

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
			} else {
				mealActList.remove(i - 1);
				mealActList.add(i - 1, null);
			}
		}

		MealDAO mealDAO = new MealDAO();
		FoodDAO foodDAO = new FoodDAO();

		long[] durationMinutes = new long[6];
		int[] score = new int[6];
		long[] digestionMinutes = new long[8];
		int totalScorePlan = 0;
		int totalScore = 0;
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

				totalScorePlan += score[j];
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

				totalScore += score[j + 1];
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

		planAndResult.setScorePlan(totalScorePlan);
		planAndResult.setScore(totalScore);

		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
		bo2.execute(planAndResult);

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("digestionMinutes", digestionMinutes);
		request.setAttribute("score", score);

		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);

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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
