package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import DAO.MealActDAO;
import model.GetDigestionMinutesLogic;
import model.GetDigestionMinutes_strLogic;
import model.GetDurationMinutesLogic;
import model.GetDuration_strLogic;
import model.GetMealActListLogic;
import model.GetMealActLogic;
import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.GetMealNameListLogic;
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
		PlanAndResult planAndResult = null;
		try {
			planAndResult = bo.execute(planAndResultId);
			if (planAndResult == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetMealActLogic bo2 = new GetMealActLogic();
		HttpSession session = request.getSession();

		String isCommitted_str = request.getParameter("isCommitted");

		if ("0".equals(isCommitted_str)) {
			planAndResult.setIsCommitted(false);
			UpdatePlanAndResultLogic bo3 = new UpdatePlanAndResultLogic();
			bo3.execute(planAndResult);
		}
		GetMealActListLogic mealActListLogic = new GetMealActListLogic();
		ArrayList<MealAct> mealActList = mealActListLogic.execute(planAndResult);

		GetMealNameListLogic mealNameBO = new GetMealNameListLogic();
		ArrayList<String> mealActList_str = null;
		try {
			mealActList_str = mealNameBO.execute(mealActList);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		request.setAttribute("mealActList_str", mealActList_str);

		long[] durationMinutes = new long[10];
		int[] score = new int[10];
		long[] digestionMinutes = new long[12];

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic();
		durationMinutes = durationBO.execute(mealActList);
		GetDigestionMinutesLogic digestionLogic = new GetDigestionMinutesLogic();
		try {
			digestionMinutes = digestionLogic.execute(mealActList); //消化時間
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetScoreLogic scoreBO = new GetScoreLogic();
		score = scoreBO.execute(digestionMinutes, durationMinutes);

		GetDuration_strLogic duration_strBO = new GetDuration_strLogic();
		String[] durationMinutes_str = duration_strBO.execute(durationMinutes); //スキマ時間 文字列

		GetDigestionMinutes_strLogic digestion_strBO = new GetDigestionMinutes_strLogic(); //消化時間 文字列
		String[] digestionMinutes_str = digestion_strBO.execute(digestionMinutes);

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);

		request.setAttribute("score", score);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		session.setAttribute("mealActList", mealActList);
		session.setAttribute("planAndResult", planAndResult);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();
		ArrayList<String> threeMealsList = null;
		try {
			threeMealsList = threeMealsName.execute();
			if (threeMealsList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		request.setAttribute("threeMealsList", threeMealsList);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
		ArrayList<MealGenre> genreList = null;
		try {
			genreList = getMealGenreBO.execute();
			if (genreList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		GetMealListLogic getMealListBO = new GetMealListLogic();
		Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
		for (MealGenre mealGenre : genreList) {
			try {
				ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
				mealMap.put(mealGenre, mealList);
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}

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

		for (int i = 1; i <= 12; i++) {
			String mealId_String = request.getParameter("meal_name" + i);
			String meal_time_String = request.getParameter("meal_time" + i);
			if (!("0".equals(mealId_String)) && !("".equals(meal_time_String))
					&& mealId_String != null && meal_time_String != null) { //食事行為の登録、食事計画と実績の登録

				MealAct mealAct = null;
				try {
					mealAct = getMealAct(i, mealId_String, meal_time_String, planAndResult);
					if (mealAct == null) {
						response.sendRedirect("/WEB-INF/error.jsp");
					}
				} catch (SQLException e) {
					response.sendRedirect("/WEB-INF/error.jsp");
				}
				setPlanAndResult(i, planAndResult, mealAct);//食事計画と実績インスタンスに食事行為IDを登録

				mealActList.remove(i - 1);
				mealActList.add(i - 1, mealAct);
			} else { //登録が不正の場合はnullを登録
				mealActList.remove(i - 1);
				mealActList.add(i - 1, null);
			}
		}
		GetMealNameListLogic mealNameBO = new GetMealNameListLogic();
		ArrayList<String> mealActList_str = null;
		try {
			mealActList_str = mealNameBO.execute(mealActList);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		request.setAttribute("mealActList_str", mealActList_str);

		long[] durationMinutes = new long[10];
		int[] score = new int[10];
		long[] digestionMinutes = new long[12];

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic();
		durationMinutes = durationBO.execute(mealActList);
		GetDigestionMinutesLogic digestionLogic = new GetDigestionMinutesLogic();
		try {
			digestionMinutes = digestionLogic.execute(mealActList); //消化時間
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		GetScoreLogic scoreBO = new GetScoreLogic();
		score = scoreBO.execute(digestionMinutes, durationMinutes);

		GetDuration_strLogic duration_strBO = new GetDuration_strLogic();
		String[] durationMinutes_str = duration_strBO.execute(durationMinutes); //スキマ時間 文字列

		GetDigestionMinutes_strLogic digestion_strBO = new GetDigestionMinutes_strLogic(); //消化時間 文字列
		String[] digestionMinutes_str = digestion_strBO.execute(digestionMinutes);

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
		int result = bo2.execute(planAndResult);
		if (result != 1) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();
		ArrayList<String> threeMealsList = null;
		try {
			threeMealsList = threeMealsName.execute();
			if (threeMealsList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		request.setAttribute("threeMealsList", threeMealsList);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
		ArrayList<MealGenre> genreList = null;
		try {
			genreList = getMealGenreBO.execute();
			if (genreList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		GetMealListLogic getMealListBO = new GetMealListLogic();
		Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
		for (MealGenre mealGenre : genreList) {
			try {
				ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
				mealMap.put(mealGenre, mealList);
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}

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

	public MealAct getMealAct(int i, String mealId_String, String meal_time_String, PlanAndResult planAndResult)
			throws SQLException {//食事行為の登録、食事計画と実績の登録
		MealAct mealAct = null;
		PostMealActLogic bo = new PostMealActLogic();

		int mealId = Integer.parseInt(mealId_String);
		LocalTime meal_LocalTime = LocalTime.parse(meal_time_String,
				DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime meal_LocalDateTime = LocalDateTime.of(planAndResult.getPlanAndResultDate(),
				meal_LocalTime);
		PostMealAct postMealAct = new PostMealAct(planAndResult.getPlanAndResultId(), meal_LocalDateTime,
				mealId,
				i);
		MealActDAO mealActDAO = new MealActDAO();
		mealActDAO.deleteMealAct(planAndResult.getPlanAndResultId(), i); //以前の登録を削除

		mealAct = bo.execute(postMealAct);
		return mealAct;
	}

	public void setPlanAndResult(int i, PlanAndResult planAndResult, MealAct mealAct) {
		switch (i) { //食事計画と実績インスタンスに食事行為IDを登録
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
	}

}
