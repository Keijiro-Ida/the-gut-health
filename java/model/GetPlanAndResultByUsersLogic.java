package model;

import DAO.PlanAndResultDAO;
import model.users.Users;

public class GetPlanAndResultByUsersLogic {
	public PlanAndResult execute(Users users) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult date = dao.findByUsers(users);
		return date;
	}
}
