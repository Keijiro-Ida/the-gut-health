package model;

import java.sql.SQLException;

import DAO.DefaultPlanAndResultDAO;

public class CreateDefaultPlanAndResultLogic {
	public int execute(PostDefaultPlanAndResult postDefault) throws SQLException {
		DefaultPlanAndResultDAO dao = new DefaultPlanAndResultDAO();
		int result = dao.createDefaultPlanAndResult(postDefault);
		return result;
	}
}
