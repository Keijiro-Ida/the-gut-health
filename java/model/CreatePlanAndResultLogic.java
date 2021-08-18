package model;

import java.sql.SQLException;

import DAO.PlanAndResultDAO;

public class CreatePlanAndResultLogic {
	public PlanAndResult execute(PostPlanAndResult postPlanAndResult) throws SQLException {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult date = dao.createPlanAndResult(postPlanAndResult);
		return date;
	}
}
