package model;

import DAO.PlanAndResultDAO;

public class CreatePlanAndResultLogic {
	public PlanAndResult execute(PostPlanAndResult postPlanAndResult) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult date = dao.createPlanAndResult(postPlanAndResult);
		return date;
	}
}
