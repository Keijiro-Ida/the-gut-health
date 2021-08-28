package model;

import java.sql.SQLException;

import DAO.MealActDAO;

public class GetMealActLogic {
	public MealAct execute(int actId) throws SQLException {
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.selectByActId(actId);
		return mealAct;
	}
}
