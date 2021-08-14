package servlet;

import java.io.IOException;
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

import DAO.DefaultPlanAndResultDAO;
import DAO.FoodDAO;
import DAO.MealActDAO;
import DAO.MealDAO;
import DAO.PlanAndResultDAO;
import model.CreatePlanAndResultLogic;
import model.DefaultPlanAndResult;
import model.GetDurationMinutesLogic;
import model.GetMealActLogic;
import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.GetPlanAndResultByUsersLogic;
import model.GetScoreLogic;
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
		String isCommitted_str = request.getParameter("isCommitted");

		if ("0".equals(isCommitted_str)) {
			planAndResult.setIsCommitted(false);
			UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
			bo2.execute(planAndResult);
		}
		GetMealActLogic bo3 = new GetMealActLogic();
		MealAct mealAct1 = bo3.execute(planAndResult.getActIdBreakfastPlan());
		MealAct mealAct2 = bo3.execute(planAndResult.getActIdBreakfast());
		MealAct mealAct3 = bo3.execute(planAndResult.getActIdAMSnackPlan());
		MealAct mealAct4 = bo3.execute(planAndResult.getActIdAMSnack());
		MealAct mealAct5 = bo3.execute(planAndResult.getActIdLunchPlan());
		MealAct mealAct6 = bo3.execute(planAndResult.getActIdLunch());
		MealAct mealAct7 = bo3.execute(planAndResult.getActIdPMSnackPlan());
		MealAct mealAct8 = bo3.execute(planAndResult.getActIdPMSnack());
		MealAct mealAct9 = bo3.execute(planAndResult.getActIdDinnerPlan());
		MealAct mealAct10 = bo3.execute(planAndResult.getActIdDinner());
		MealAct mealAct11 = bo3.execute(planAndResult.getActIdNightSnackPlan());
		MealAct mealAct12 = bo3.execute(planAndResult.getActIdNightSnack());
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

		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		DefaultPlanAndResult defaultSetting = dao.findByUsrId(users.getUsrId());
		ArrayList<String> timeList = new ArrayList<String>();
		ArrayList<Integer> mealIdList = new ArrayList<Integer>();

		if (defaultSetting != null) {

			timeList.add(defaultSetting.getBreakfastTime());
			timeList.add(defaultSetting.getAmSnackTime());
			timeList.add(defaultSetting.getLunchTime());
			timeList.add(defaultSetting.getPmSnackTime());
			timeList.add(defaultSetting.getDinnerTime());
			timeList.add(defaultSetting.getNightSnackTime());
			mealIdList.add(defaultSetting.getBreakfastId());
			mealIdList.add(defaultSetting.getAmSnackID());
			mealIdList.add(defaultSetting.getLunchId());
			mealIdList.add(defaultSetting.getPmSnackId());
			mealIdList.add(defaultSetting.getDinnerId());
			mealIdList.add(defaultSetting.getNightSnackId());

		}
		request.setAttribute("defaultSetting", defaultSetting);
		request.setAttribute("timeList", timeList);
		request.setAttribute("mealIdList", mealIdList);

		if (planAndResult.getIsCommitted() == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/isCommittedMain.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
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

		int totalScorePlan = 0;
		int totalScore = 0;
		for (int i = 0; i < 10; i += 2) {
			totalScorePlan += score[i];
			totalScore += score[i + 1];

		}

		planAndResult.setScorePlan(totalScorePlan);
		planAndResult.setScore(totalScore);

		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
		bo2.execute(planAndResult);

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

		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		DefaultPlanAndResult defaultSetting = dao.findByUsrId(users.getUsrId());
		ArrayList<String> timeList = new ArrayList<String>();
		ArrayList<Integer> mealIdList = new ArrayList<Integer>();

		if (defaultSetting != null) {

			timeList.add(defaultSetting.getBreakfastTime());
			timeList.add(defaultSetting.getAmSnackTime());
			timeList.add(defaultSetting.getLunchTime());
			timeList.add(defaultSetting.getPmSnackTime());
			timeList.add(defaultSetting.getDinnerTime());
			timeList.add(defaultSetting.getNightSnackTime());
			mealIdList.add(defaultSetting.getBreakfastId());
			mealIdList.add(defaultSetting.getAmSnackID());
			mealIdList.add(defaultSetting.getLunchId());
			mealIdList.add(defaultSetting.getPmSnackId());
			mealIdList.add(defaultSetting.getDinnerId());
			mealIdList.add(defaultSetting.getNightSnackId());

		}
		request.setAttribute("defaultSetting", defaultSetting);
		request.setAttribute("timeList", timeList);
		request.setAttribute("mealIdList", mealIdList);

		boolean isCommitted = false;
		String isCommitted_str = request.getParameter("planAndResultSubmit");
		if (isCommitted_str.equals("1")) {
			isCommitted = true;
			planAndResult.setIsCommitted(isCommitted);
		} else if (isCommitted_str.equals("2")) {
			MealActDAO mealActDAO = new MealActDAO();
			mealActDAO.deleteMealActById(planAndResult.getActIdBreakfastPlan());
			mealActDAO.deleteMealActById(planAndResult.getActIdBreakfast());
			mealActDAO.deleteMealActById(planAndResult.getActIdAMSnackPlan());
			mealActDAO.deleteMealActById(planAndResult.getActIdAMSnack());
			mealActDAO.deleteMealActById(planAndResult.getActIdLunch());
			mealActDAO.deleteMealActById(planAndResult.getActIdLunchPlan());
			mealActDAO.deleteMealActById(planAndResult.getActIdPMSnackPlan());
			mealActDAO.deleteMealActById(planAndResult.getActIdPMSnack());
			mealActDAO.deleteMealActById(planAndResult.getActIdDinnerPlan());
			mealActDAO.deleteMealActById(planAndResult.getActIdDinner());
			mealActDAO.deleteMealActById(planAndResult.getActIdNightSnack());
			mealActDAO.deleteMealActById(planAndResult.getActIdNightSnackPlan());
			for (int i = 0; i < mealActList.size(); i++) {
				mealActList.set(i, null);
			}
			PlanAndResultDAO planAndResultDAO = new PlanAndResultDAO();
			planAndResultDAO.deletePlanAndResult(planAndResult.getPlanAndResultId());
			CreatePlanAndResultLogic createLogic = new CreatePlanAndResultLogic();
			PostPlanAndResult postPlanAndResult = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			planAndResult = createLogic.execute(postPlanAndResult);
			
			durationMinutes = new long[10];
			durationMinutes_str = new String[10];
			score = new int[10];
			digestionMinutes = new long[12];
			digestionMinutes_str = new String[12];

		}
		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("score", score);
		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);

		if (planAndResult.getIsCommitted() == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/isCommittedMain.jsp");
			dispatcher.forward(request, response);
		}

	}

}
