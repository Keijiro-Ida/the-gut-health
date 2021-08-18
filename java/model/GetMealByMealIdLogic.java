package model;

import java.sql.SQLException;

import DAO.MealDAO;

public class GetMealByMealIdLogic {
	public Meal execute(int mealId) throws SQLException {
		MealDAO dao = new MealDAO();
		Meal meal = dao.selectMealByMealId(mealId);
		return meal;
	}

}
