package model;

import DAO.DefaultPlanAndResultDAO;

public class CreateDefaultPlanAndResultLogic {
	public int execute(PostDefaultPlanAndResult postDefault) {
		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		int result = dao.createDefaultPlanAndResult(postDefault);
		return result;
	}
}
