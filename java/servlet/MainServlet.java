package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DAO.DefaultPlanAndResultDAO;
import DAO.MealActDAO;
import DAO.PlanAndResultDAO;
import model.CreatePlanAndResultLogic;
import model.DefaultPlanAndResult;
import model.GetDefaultNameListLogic;
import model.GetDigestionMinutesLogic;
import model.GetDigestionMinutes_strLogic;
import model.GetDurationMinutesLogic;
import model.GetDuration_strLogic;
import model.GetMealActListLogic;
import model.GetMealGenreListLogic;
import model.GetMealIdListLogic;
import model.GetMealListLogic;
import model.GetMealNameListLogic;
import model.GetPlanAndResultByUsersLogic;
import model.GetScoreLogic;
import model.GetThreeMealsNameLogic;
import model.GetTimeListLogic;
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

		Logger logger = LogManager.getLogger("Logger");
		logger.trace("Logged by logger.trace");
		logger.debug("Logged by logger.debug");
		logger.info("Logged by logger.info");

		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		GetPlanAndResultByUsersLogic bo = new GetPlanAndResultByUsersLogic();
		PlanAndResult planAndResult = bo.execute(users); //登録済みの食事計画と実績インスタンスを獲得

		if (planAndResult == null) { //登録がない場合は、食事計画と実績インスタンスを新規で作成
			CreatePlanAndResultLogic bo2 = new CreatePlanAndResultLogic();
			PostPlanAndResult postPlanAndResult = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			try {
				planAndResult = bo2.execute(postPlanAndResult);
				if (planAndResult == null) { //データベースエラー時はエラー画面へ遷移
					response.sendRedirect("/WEB-INF/error.jsp");
				}
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		}
		String isCommitted_str = request.getParameter("isCommitted"); //食事計画と実績の確定パラメータを獲得
		if ("0".equals(isCommitted_str)) { //0の場合は保存の遷移
			planAndResult.setIsCommitted(false);
			UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic();
			bo2.execute(planAndResult);
		}
		GetMealActListLogic mealActListLogic = new GetMealActListLogic(); //食事行為リストの獲得
		ArrayList<MealAct> mealActList = mealActListLogic.execute(planAndResult);

		GetMealNameListLogic mealNameBO = new GetMealNameListLogic(); //食事行為に対する食事名の獲得
		ArrayList<String> mealActList_str = null; //食事名を文字列で獲得
		try {
			mealActList_str = mealNameBO.execute(mealActList);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic(); //スキマ時間を獲得
		long[] durationMinutes = durationBO.execute(mealActList);
		int[] score = new int[10]; //スコア
		long[] digestionMinutes = null; //消化時間

		GetDigestionMinutesLogic digestionLogic = new GetDigestionMinutesLogic(); //消化時間を獲得
		try {
			digestionMinutes = digestionLogic.execute(mealActList); //消化時間
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetScoreLogic scoreBO = new GetScoreLogic(); //スコアを獲得
		score = scoreBO.execute(digestionMinutes, durationMinutes);

		GetDuration_strLogic duration_strBO = new GetDuration_strLogic(); //スキマ時間 文字列の獲得
		String[] durationMinutes_str = duration_strBO.execute(durationMinutes); //スキマ時間 文字列

		GetDigestionMinutes_strLogic digestion_strBO = new GetDigestionMinutes_strLogic(); //消化時間 文字列の獲得
		String[] digestionMinutes_str = digestion_strBO.execute(digestionMinutes); //消化時間 文字列の獲得

		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);
		request.setAttribute("score", score);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		session.setAttribute("mealActList", mealActList);
		request.setAttribute("mealActList_str", mealActList_str);
		session.setAttribute("planAndResult", planAndResult);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic(); //データベースから食事ジャンルを獲得
		ArrayList<MealGenre> genreList = null;
		try {
			genreList = getMealGenreBO.execute();
			if (genreList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		GetMealListLogic getMealListBO = new GetMealListLogic(); //食事ジャンルに対する食事リストを獲得
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

		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO(); //デフォルト設定値を獲得
		DefaultPlanAndResult defaultSetting = null;
		try {
			defaultSetting = dao.findByUsrId(users.getUsrId());
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetDefaultNameListLogic defaultNameListBO = new GetDefaultNameListLogic(); //デフォルト設定の食事IDに伴う食事名の獲得
		ArrayList<String> defaultNameList = null;
		try {
			defaultNameList = defaultNameListBO.execute(defaultSetting);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetTimeListLogic timeListBO = new GetTimeListLogic();
		ArrayList<String> timeList = timeListBO.execute(defaultSetting); //デフォルト設定の時間リストを獲得
		GetMealIdListLogic mealIdListBO = new GetMealIdListLogic();
		ArrayList<Integer> mealIdList = mealIdListBO.execute(defaultSetting); //デフォルト設定の食事IDリストを獲得

		request.setAttribute("defaultSetting", defaultSetting);
		request.setAttribute("timeList", timeList);
		request.setAttribute("mealIdList", mealIdList);
		request.setAttribute("defaultNameList", defaultNameList);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic(); //3食と間食の名前の獲得
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

		if (planAndResult.getIsCommitted() == false) { //保存の場合の遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else { //確定の場合の遷移
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
		PlanAndResult planAndResult = (PlanAndResult) session.getAttribute("planAndResult"); //食事計画と実績インスタンスの獲得
		ArrayList<MealAct> mealActList = (ArrayList<MealAct>) session.getAttribute("mealActList");//食事行為リストの獲得

		for (int i = 1; i <= 12; i++) {
			String mealId_String = request.getParameter("meal_name" + i); //食事IDの獲得
			String meal_time_String = request.getParameter("meal_time" + i); //食事時間の獲得

			if (!("0".equals(mealId_String)) && !("".equals(meal_time_String))
					&& mealId_String != null && meal_time_String != null) { //食事行為の登録、食事計画と実績の登録

				MealAct mealAct = null;
				try {
					mealAct = getMealAct(i, mealId_String, meal_time_String, planAndResult); //食事行為インスタンスの獲得
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
		GetMealNameListLogic mealNameBO = new GetMealNameListLogic(); //食事行為リストに伴う食事名リストの獲得
		ArrayList<String> mealActList_str = null;
		try {
			mealActList_str = mealNameBO.execute(mealActList);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetDurationMinutesLogic durationBO = new GetDurationMinutesLogic(); //スキマ時間の獲得
		long[] durationMinutes = durationBO.execute(mealActList); //スキマ時間

		int[] score = new int[10]; //スコア
		long[] digestionMinutes = new long[12]; //消化時間

		GetDigestionMinutesLogic digestionLogic = new GetDigestionMinutesLogic(); //消化時間の獲得
		try {
			digestionMinutes = digestionLogic.execute(mealActList); //消化時間
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetScoreLogic scoreBO = new GetScoreLogic(); //スコアを獲得
		score = scoreBO.execute(digestionMinutes, durationMinutes);

		GetDuration_strLogic duration_strBO = new GetDuration_strLogic();
		String[] durationMinutes_str = duration_strBO.execute(durationMinutes); //スキマ時間 文字列

		GetDigestionMinutes_strLogic digestion_strBO = new GetDigestionMinutes_strLogic(); //消化時間 文字列
		String[] digestionMinutes_str = digestion_strBO.execute(digestionMinutes);

		int totalScorePlan = 50; //トータルスコア
		int totalScore = 50;
		for (int i = 0; i < 10; i += 2) { //トータルスコアの獲得
			totalScorePlan += score[i];
			totalScore += score[i + 1];

		}

		planAndResult.setScorePlan(totalScorePlan);
		planAndResult.setScore(totalScore);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic(); //食事ジャンルの獲得
		ArrayList<MealGenre> genreList = null;
		try {
			genreList = getMealGenreBO.execute();
			if (genreList == null) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}

		GetMealListLogic getMealListBO = new GetMealListLogic(); //食事ジャンルに伴う食事リストの獲得
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

		boolean isCommitted = false; //
		String isCommitted_str = request.getParameter("planAndResultSubmit");
		if (isCommitted_str.equals("1")) { //確定の場合の遷移
			isCommitted = true;
			planAndResult.setIsCommitted(isCommitted);
		} else if (isCommitted_str.equals("2")) { //削除の場合の遷移
			deleteMealAct(planAndResult); //食事計画と実績インスタンスに紐づく食事行為の削除
			for (int i = 0; i < mealActList.size(); i++) {
				mealActList.set(i, null); //食事行為リストにnullを設定
			}
			PlanAndResultDAO planAndResultDAO = new PlanAndResultDAO();
			planAndResultDAO.deletePlanAndResult(planAndResult.getPlanAndResultId()); //食事計画と実績インスタンスを削除
			CreatePlanAndResultLogic createLogic = new CreatePlanAndResultLogic(); //食事計画と実績インスタンスを獲得
			PostPlanAndResult postPlanAndResult = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			try {
				planAndResult = createLogic.execute(postPlanAndResult);
				if (planAndResult == null) {
					response.sendRedirect("/WEB-INF/error.jsp");
				}
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/error.jsp");
			}

			durationMinutes = new long[10]; //スキマ時間
			durationMinutes_str = new String[10]; //スキマ時間 文字列
			score = new int[10]; //スコア
			digestionMinutes = new long[12]; //消化時間
			digestionMinutes_str = new String[12]; //消化時間 文字列

		}
		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic(); //3食と間食の名前獲得
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

		UpdatePlanAndResultLogic bo2 = new UpdatePlanAndResultLogic(); //食事計画と実績の更新
		int result = bo2.execute(planAndResult);
		if (result != 1) {
			response.sendRedirect("/WEB-INF/error.jsp");
		}
		request.setAttribute("durationMinutes", durationMinutes);
		request.setAttribute("durationMinutes_str", durationMinutes_str);
		request.setAttribute("digestionMinutes", digestionMinutes);
		request.setAttribute("digestionMinutes_str", digestionMinutes_str);
		request.setAttribute("score", score);
		session.setAttribute("planAndResult", planAndResult);
		session.setAttribute("mealActList", mealActList);
		request.setAttribute("mealActList_str", mealActList_str);

		if (planAndResult.getIsCommitted() == false) { //保存の場合の遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else { //確定の場合の遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/isCommittedMain.jsp");
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

	public void deleteMealAct(PlanAndResult planAndResult) {
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
	}

}
