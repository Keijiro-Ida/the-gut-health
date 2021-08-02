package model;

import java.util.ArrayList;

import DAO.MealDAO;

public class GetMealListLogic {
	public ArrayList<Meal> execute(int mealGenreId) {
		MealDAO dao = new MealDAO();
		ArrayList<Meal> list = dao.selectMealByMealGenre(mealGenreId);
		return list;
	}
}
