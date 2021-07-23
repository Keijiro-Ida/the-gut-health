package model;

import DAO.MealActDAO;

public class PostMealActLogic {
	public MealAct execute(PostMealAct postMealAct) {
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.mealActCreate(postMealAct);
		return mealAct;
	}
}
