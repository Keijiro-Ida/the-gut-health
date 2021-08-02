package model;

import java.util.ArrayList;

import DAO.MealGenreDAO;

public class GetMealGenreListLogic {
	public ArrayList<MealGenre> execute() {
		MealGenreDAO dao = new MealGenreDAO();
		ArrayList<MealGenre> list = dao.selectMealGenreAll();
		return list;
	}
}
