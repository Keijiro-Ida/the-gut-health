package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import model.users.Users;

public class MyCalendarLogic {
	public MyCalendar createMyCalendar(int year, int month, Users users) {
		MyCalendar mc = new MyCalendar();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);

		mc.setYear(cal.get(Calendar.YEAR));
		mc.setMonth(cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DATE, 1);
		int before = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, daysCount);
		int after = 7 - cal.get(Calendar.DAY_OF_WEEK);
		int total = before + daysCount + after;
		int rows = total / 7;
		String[][] data = new String[rows][7];
		PlanAndResult[][] planAndResult_cal = new PlanAndResult[rows][7];

		LocalDate localDate = LocalDate.of(year, month, 1);
		GetMonthPlanAndResultList logic = new GetMonthPlanAndResultList();
		ArrayList<PlanAndResult> planAndResultList = logic.execute(users, localDate);

		Calendar now = Calendar.getInstance();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 0 && j < before || i == rows - 1 && j >= 7 - after) {
					data[i][j] = "";
					planAndResult_cal[i][j] = null;
				} else {
					int date = i * 7 + j + 1 - before;
					data[i][j] = String.valueOf(date);

					for (PlanAndResult planAndResult : planAndResultList) {

						if (date == planAndResult.getPlanAndResultDate().getDayOfMonth()) {
							planAndResult_cal[i][j] = planAndResult;
							break;
						} else {
							planAndResult_cal[i][j] = null;
						}

					}

					if (now.get(Calendar.DATE) == date && now.get(Calendar.MONTH) == mc.getMonth() - 1
							&& now.get(Calendar.YEAR) == mc.getYear()) {
						data[i][j] = "*" + data[i][j];
					}
				}

			}
		}
		mc.setData(data);
		mc.setPlanAndResult_cal(planAndResult_cal);
		return mc;

	}

}
