package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import model.CreateDefaultPlanAndResultLogic;
import model.DefaultPlanAndResult;
import model.GetDefaultNameListLogic;
import model.GetMealGenreListLogic;
import model.GetMealIdListLogic;
import model.GetMealListLogic;
import model.GetThreeMealsNameLogic;
import model.GetTimeListLogic;
import model.Meal;
import model.MealGenre;
import model.PostDefaultPlanAndResult;
import model.users.Users;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/DefaultSettingServlet")
public class DefaultSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		DefaultPlanAndResult defaultSetting = null;
		try {
			defaultSetting = dao.findByUsrId(users.getUsrId());
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/jsp/error.jsp");
		}

		GetTimeListLogic timeListBO = new GetTimeListLogic();
		ArrayList<String> timeList = timeListBO.execute(defaultSetting);
		GetMealIdListLogic mealIdListBO = new GetMealIdListLogic();
		ArrayList<Integer> mealIdList = mealIdListBO.execute(defaultSetting);

		GetDefaultNameListLogic nameListBO = new GetDefaultNameListLogic();
		ArrayList<String> defaultNameList = null;
		try {
			defaultNameList = nameListBO.execute(defaultSetting);
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/jsp/error.jsp");
		}

		request.setAttribute("defaultSetting", defaultSetting);
		request.setAttribute("timeList", timeList);
		request.setAttribute("mealIdList", mealIdList);
		request.setAttribute("defaultNameList", defaultNameList);

		GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
		ArrayList<MealGenre> genreList = null;
		try {
			genreList = getMealGenreBO.execute();
			if (genreList == null) {
				response.sendRedirect("/WEB-INF/jsp/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/jsp/error.jsp");
		}
		GetMealListLogic getMealListBO = new GetMealListLogic();
		Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
		for (MealGenre mealGenre : genreList) {
			try {
				ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
				mealMap.put(mealGenre, mealList);
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/jsp/error.jsp");
			}
		}
		request.setAttribute("mealMap", mealMap);
		request.setAttribute("genreList", genreList);

		GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();
		ArrayList<String> threeMealsList = null;
		try {
			threeMealsList = threeMealsName.execute();
			if (threeMealsList == null) {
				response.sendRedirect("/WEB-INF/jsp/error.jsp");
			}
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/jsp/error.jsp");
		}

		request.setAttribute("threeMealsList", threeMealsList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/defaultSetting.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");

		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		DefaultPlanAndResult defaultSetting = null;
		try {
			defaultSetting = dao.findByUsrId(users.getUsrId());
		} catch (SQLException e) {
			response.sendRedirect("/WEB-INF/jsp/error.jsp");
		}
		if (defaultSetting != null) {
			DefaultPlanAndResultDAO defaultDAO = new DefaultPlanAndResultDAO();
			int result = defaultDAO.deleteByUsrId(users.getUsrId());
			if (result != 1) {
				response.sendRedirect("/WEB-INF/jsp/error.jsp");
			}
		}
		String isRegist_str = request.getParameter("defaultSubmit");
		if (isRegist_str.equals("1")) {

			PostDefaultPlanAndResult postDefault = new PostDefaultPlanAndResult();
			for (int i = 1; i <= 12; i += 2) {
				String mealId_String = request.getParameter("meal_name" + i);
				String mealTime = request.getParameter("meal_time" + i);

				if (!("0".equals(mealId_String)) && !("".equals(mealTime))
						&& mealId_String != null && mealTime != null) {
					int mealId = Integer.parseInt(mealId_String);
					switch (i) {
					case 1:
						postDefault.setBreakfastId(mealId);
						postDefault.setBreakfastTime(mealTime);
						break;
					case 3:
						postDefault.setAmSnackId(mealId);
						postDefault.setAmSnackTime(mealTime);
						break;
					case 5:
						postDefault.setLunchId(mealId);
						postDefault.setLunchTime(mealTime);
						break;
					case 7:
						postDefault.setPmSnackId(mealId);
						postDefault.setPmSnackTime(mealTime);
						break;
					case 9:
						postDefault.setDinnerId(mealId);
						postDefault.setDinnerTime(mealTime);
						break;
					case 11:
						postDefault.setNightSnackId(mealId);
						postDefault.setNightSnackTime(mealTime);
						break;

					}
				}
			}

			postDefault.setUsrId(users.getUsrId());
			CreateDefaultPlanAndResultLogic bo = new CreateDefaultPlanAndResultLogic();
			int result = bo.execute(postDefault);

			if (result == 1) {
				request.setAttribute("msg", "登録完了しました。");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/jsp/defaultSettingResult.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("msg", "システムエラー");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/jsp/defaultSettingResult.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("msg", "削除しました。");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsp/defaultSettingResult.jsp");
			dispatcher.forward(request, response);

		}

	}

}
