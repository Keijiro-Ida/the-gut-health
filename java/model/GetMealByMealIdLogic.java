package model;

import DAO.MealDAO;

public class GetMealByMealIdLogic {
	public Meal execute(int mealId) {
		MealDAO dao = new MealDAO();
		Meal meal = dao.selectMealByMealId(mealId);
		return meal;
	}

}
