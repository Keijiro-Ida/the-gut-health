package model;

import java.sql.SQLException;

import DAO.PlanAndResultDAO;

public class GetPlanAndResultByIdLogic {
	public PlanAndResult execute(int planAndResultId) throws SQLException {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult planAndResult = dao.findByPlanAndResultId(planAndResultId);
		return planAndResult;

	}
}
