package model;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MealGenreDAO;

public class GetMealGenreListLogic {
	public ArrayList<MealGenre> execute() throws SQLException {
		MealGenreDAO dao = new MealGenreDAO();
		ArrayList<MealGenre> list = dao.selectMealGenreAll();
		return list;
	}
}
