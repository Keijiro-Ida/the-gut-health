package model;

import java.io.Serializable;

public class MyCalendar implements Serializable {
	private int year;
	private int month;
	private String[][] data;
	private PlanAndResult[][] planAndResult_cal;

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public String[][] getData() {
		return data;
	}

	public PlanAndResult[][] getPlanAndResult_cal() {
		return planAndResult_cal;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public void setPlanAndResult_cal(PlanAndResult[][] planAndResult_cal) {
		this.planAndResult_cal = planAndResult_cal;
	}
}
