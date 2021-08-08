package model;

import java.util.ArrayList;
import java.util.Map;

import DAO.PlanAndResultDAO;

public class GetAverageScoreListLogic {
	public ArrayList<Integer> execute(int usrId, int year) {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		Map<Integer, Integer> averageScoreMap = dao.getAverageScoreMap(usrId, year);
		ArrayList<Integer> averageScoreList = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			if (averageScoreMap.get(i) == null) {
				averageScoreList.add(0);
			} else {
				averageScoreList.add(averageScoreMap.get(i));
			}
		}
		return averageScoreList;
	}
}
