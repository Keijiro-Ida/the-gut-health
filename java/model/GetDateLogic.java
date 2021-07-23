package model;

import DAO.DateMealAllDAO;
import model.users.Users;

public class GetDateLogic {
	public DateMealAll execute(Users users) {
		DateMealAllDAO dao = new DateMealAllDAO();
		DateMealAll date = dao.findByUsers(users);
		return date;
	}
}
