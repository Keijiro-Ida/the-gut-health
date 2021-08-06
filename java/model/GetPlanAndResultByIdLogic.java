package model;

import DAO.PlanAndResultDAO;

public class GetPlanAndResultByIdLogic {
	public PlanAndResult execute(int planAndResultId) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult planAndResult = dao.findByPlanAndResultId(planAndResultId);
		return planAndResult;

	}
}
