package model;

import java.util.ArrayList;

import DAO.MealDAO;

public class GetDefaultNameListLogic {
	ArrayList<String> defaultMealNameList = new ArrayList<String>();

	public ArrayList<String> execute(DefaultPlanAndResult defaultSetting) {

		MealDAO mealDAO = new MealDAO();
		if (defaultSetting != null) {
			if (defaultSetting.getBreakfastId() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getBreakfastId()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
			if (defaultSetting.getAmSnackID() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getAmSnackID()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
			if (defaultSetting.getLunchId() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getLunchId()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
			if (defaultSetting.getPmSnackId() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getPmSnackId()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
			if (defaultSetting.getDinnerId() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getDinnerId()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
			if (defaultSetting.getNightSnackId() != 0) {
				defaultMealNameList.add(mealDAO.selectMealByMealId(defaultSetting.getNightSnackId()).getMealName());
			} else {
				defaultMealNameList.add(null);
			}
		}
		return defaultMealNameList;
	}
}
