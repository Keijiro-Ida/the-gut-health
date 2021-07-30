package model;

import DAO.MealActDAO;

public class GetMealActLogic {
	public MealAct execute(int actId) {
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.selectByActId(actId);
		return mealAct;
	}
}
