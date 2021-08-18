package model;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MealDAO;

public class GetMealListLogic {
	public ArrayList<Meal> execute(int mealGenreId) throws SQLException {
		MealDAO dao = new MealDAO();
		ArrayList<Meal> list = dao.selectMealByMealGenre(mealGenreId);
		return list;
	}
}
