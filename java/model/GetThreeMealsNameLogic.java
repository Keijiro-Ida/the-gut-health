package model;

import java.util.ArrayList;

import DAO.ThreeMealsAndSnackDAO;

public class GetThreeMealsNameLogic {
	public ArrayList<String> execute() {
		ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
		ArrayList<String> list = threeMealsDAO.selectThreeMeals();
		return list;
	}
}
