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

import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.Meal;
import model.MealGenre;

/**
 * 腸活のポイント画面１への遷移
 */
@WebServlet("/Point1Servlet")
public class Point1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			GetMealListLogic getMealListBO = new GetMealListLogic(); //食事ジャンルに伴う食事リストの獲得
			GetMealGenreListLogic getMealGenreBO = new GetMealGenreListLogic(); //食事ジャンルの獲得
			ArrayList<MealGenre> genreList = getMealGenreBO.execute();

			Map<MealGenre, ArrayList<Meal>> mealMap = new HashMap<>();
			for (MealGenre mealGenre : genreList) {
				ArrayList<Meal> mealList = getMealListBO.execute(mealGenre.getMealGenreId());
				mealMap.put(mealGenre, mealList);
			}
			request.setAttribute("mealMap", mealMap);
			request.setAttribute("genreList", genreList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/point1.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/the-gut-healthy/error.jsp");
		}
	}

}
