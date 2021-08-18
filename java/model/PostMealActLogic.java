package model;

import java.sql.SQLException;

import DAO.MealActDAO;

public class PostMealActLogic {
	public MealAct execute(PostMealAct postMealAct) throws SQLException {
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.mealActCreate(postMealAct);
		return mealAct;
	}
}
