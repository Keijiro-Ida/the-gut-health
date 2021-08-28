package model;

import java.sql.SQLException;

import DAO.PlanAndResultDAO;

public class UpdatePlanAndResultLogic {
	public int execute(PlanAndResult planAndResult) throws SQLException {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		int result = dao.updatePlanAndResult(planAndResult);
		return result;
	}
}
