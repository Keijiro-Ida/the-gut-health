package model;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.FoodDAO;
import DAO.MealDAO;

public class GetDigestionMinutesLogic {

	public long[] execute(ArrayList<MealAct> mealActList) throws SQLException {
		long[] digestionMinutes = new long[12];
		MealDAO mealDAO = new MealDAO();
		FoodDAO foodDAO = new FoodDAO();

		for (int j = 0; j < 12; j++) {
			if (mealActList.get(j) != null) {
				digestionMinutes[j] = foodDAO.selectDigestionMinutesFromId(
						mealDAO.selectMealByMealId(mealActList.get(j).getMealId()).getFoodId());
			}
		}
		return digestionMinutes;
	}
}
