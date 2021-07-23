package model;

import DAO.DateMealAllDAO;

public class updateDateMealAllLogic {
	public int execute(DateMealAll dateMealAll) {
		DateMealAllDAO dao = new DateMealAllDAO();
		int result = dao.updateDateMealAll(dateMealAll);
		return result;
	}
}
