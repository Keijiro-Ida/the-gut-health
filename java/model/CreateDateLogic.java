package model;

import DAO.DateMealAllDAO;

public class CreateDateLogic {
	public DateMealAll execute(PostDate postDate) {
		DateMealAllDAO dao = new DateMealAllDAO();
		DateMealAll date = dao.createDateMealAll(postDate);
		return date;
	}
}
