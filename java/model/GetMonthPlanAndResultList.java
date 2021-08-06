package model;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.PlanAndResultDAO;
import model.users.Users;

public class GetMonthPlanAndResultList {
	public ArrayList<PlanAndResult> execute(Users users, LocalDate localDate) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		ArrayList<PlanAndResult> list = dao.getMonthPlanAndResultList(users, localDate);
		return list;
	}
}
