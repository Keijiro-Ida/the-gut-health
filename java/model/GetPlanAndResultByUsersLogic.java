package model;

import java.sql.SQLException;

import DAO.PlanAndResultDAO;
import model.users.Users;

public class GetPlanAndResultByUsersLogic {
	public PlanAndResult execute(Users users) throws SQLException {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult date = dao.findByUsers(users);
		return date;
	}
}
