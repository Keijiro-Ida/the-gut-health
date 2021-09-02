package servlet;

import java.io.IOException;
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
 * デフォルト設定の遷移
 */
@WebServlet("/DefaultSettingServlet")
public class DefaultSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");
			DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();//デフォルト設定インスタンスの獲得
			DefaultPlanAndResult defaultSetting = dao.findByUsrId(users.getUsrId());

			GetTimeListLogic timeListBO = new GetTimeListLogic(); //デフォルト設定の時刻リスト獲得
			ArrayList<String> timeList = timeListBO.execute(defaultSetting);
			GetMealIdListLogic mealIdListBO = new GetMealIdListLogic(); //デフォルト設定の食事IDリスト獲得
			ArrayList<Integer> mealIdList = mealIdListBO.execute(defaultSetting);

			GetDefaultNameListLogic nameListBO = new GetDefaultNameListLogic(); //デフォルト設定の食事名リストの獲得
			ArrayList<String> defaultNameList = nameListBO.execute(defaultSetting);

			request.setAttribute("defaultSetting", defaultSetting);
			request.setAttribute("timeList", timeList);
			request.setAttribute("mealIdList", mealIdList);
			request.setAttribute("defaultNameList", defaultNameList);

			GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic();
			ArrayList<MealGenre> genreList = getMealGenreBO.execute();

			GetMealListLogic getMealListBO = new GetMealListLogic(); //食事一覧リスト獲得
			Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
			for (MealGenre mealGenre : genreList) {
				ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
				mealMap.put(mealGenre, mealList);
			}
			request.setAttribute("mealMap", mealMap);
			request.setAttribute("genreList", genreList);

			GetThreeMealsNameLogic threeMealsName = new GetThreeMealsNameLogic();//3食と間食名獲得
			ArrayList<String> threeMealsList = threeMealsName.execute();

			request.setAttribute("threeMealsList", threeMealsList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/defaultSetting.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("/the-gut-healthy/error.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");

			DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO(); //デフォルト設定インスタンスの獲得
			DefaultPlanAndResult defaultSetting = dao.findByUsrId(users.getUsrId());

			//登録が過去にある場合の遷移
			if (defaultSetting != null) {
				DefaultPlanAndResultDAO defaultDAO = new DefaultPlanAndResultDAO();
				defaultDAO.deleteByUsrId(users.getUsrId());
			}

			String isRegist_str = request.getParameter("defaultSubmit");
			//登録の場合
			if (isRegist_str.equals("1")) {

				PostDefaultPlanAndResult postDefault = new PostDefaultPlanAndResult();
				for (int i = 1; i <= 12; i += 2) {
					String mealId_String = request.getParameter("meal_name" + i);
					String mealTime = request.getParameter("meal_time" + i);

					if (!("0".equals(mealId_String)) && !("".equals(mealTime))
							&& mealId_String != null && mealTime != null) {
						int mealId = Integer.parseInt(mealId_String);
						//デフォルト設定インスタンスの設定
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
				int resultDefault = bo.execute(postDefault);

				if (resultDefault == 1) {
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

		} catch (

		Exception e) {
			response.sendRedirect("/the-gut-healthy/error.jsp");
		}

	}

}
