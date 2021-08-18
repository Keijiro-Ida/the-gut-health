package model;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ThreeMealsAndSnackDAO;

public class GetThreeMealsNameLogic {
	public ArrayList<String> execute() throws SQLException {
		ThreeMealsAndSnackDAO threeMealsDAO = new ThreeMealsAndSnackDAO();
		ArrayList<String> list = threeMealsDAO.selectThreeMeals();
		return list;
	}
}
