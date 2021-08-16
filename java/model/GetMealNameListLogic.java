package model;

import java.util.ArrayList;

import DAO.MealDAO;

public class GetMealNameListLogic {
	ArrayList<String> mealNameList = new ArrayList<String>();

	public ArrayList<String> execute(ArrayList<MealAct> mealActList) {
		MealDAO mealDAO = new MealDAO();
		for (int i = 0; i < 12; i++) {
			if (mealActList.get(i) != null) {
				mealNameList.add(mealDAO.selectMealByMealId(mealActList.get(i).getMealId()).getMealName());
			} else {
				mealNameList.add(null);
			}

		}
		return mealNameList;
	}
}
