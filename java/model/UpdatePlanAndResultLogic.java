package model;

import DAO.PlanAndResultDAO;

public class UpdatePlanAndResultLogic {
	public int execute(PlanAndResult planAndResult) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		int result = dao.updatePlanAndResult(planAndResult);
		return result;
	}
}
