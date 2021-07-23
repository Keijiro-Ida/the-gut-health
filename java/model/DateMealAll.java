package model;

import java.io.Serializable;
import java.sql.Date;

public class DateMealAll implements Serializable {
	private int usrId;
	private int dateId;
	private Date dateToday;

	private int actIdBreakfast;
	private int actIdLunch;
	private int actIdSnack;
	private int actIdDinner;
	private int actIdBreakfastPlan;
	private int actIdLunchPlan;
	private int actIdSnackPlan;
	private int actIdDinnerPlan;
	private int score;

	public DateMealAll() {
	}

	public DateMealAll(int usrId, int dateId, Date dateToday) {
		this.usrId = usrId;
		this.dateId = dateId;
		this.dateToday = dateToday;
	}

	public DateMealAll(int usrId, int dateId, Date dateToday,
			int actIdBreakFast, int actIdLunch, int actIdSnack,
			int actIdDinner, int actIdBreakfastPlan, int actIdLunchPlan,
			int actIdSnackPlan, int actIdDinnerPlan, int score) {
		this.usrId = usrId;
		this.dateId = dateId;
		this.dateToday = dateToday;
		this.actIdBreakfast = actIdBreakFast;
		this.actIdLunch = actIdLunch;
		this.actIdSnack = actIdSnack;
		this.actIdDinner = actIdDinner;
		this.actIdBreakfastPlan = actIdBreakfastPlan;
		this.actIdLunchPlan = actIdLunchPlan;
		this.actIdSnackPlan = actIdSnackPlan;
		this.actIdDinnerPlan = actIdDinnerPlan;
		this.score = score;
	}

	public int getUsrId() {
		return usrId;
	}

	public int getDateId() {
		return dateId;
	}

	public Date getDateToday() {
		return dateToday;
	}

	public int getActIdBreakfast() {
		return actIdBreakfast;
	}

	public int getActIdLunch() {
		return actIdLunch;
	}

	public int getActIdSnack() {
		return actIdSnack;
	}

	public int getActIdDinner() {
		return actIdDinner;
	}

	public int getActIdBreakfastPlan() {
		return actIdBreakfastPlan;
	}

	public int getActIdLunchPlan() {
		return actIdLunchPlan;
	}

	public int getActIdSnackPlan() {
		return actIdSnackPlan;
	}

	public int getActIdDinnerPlan() {
		return actIdDinnerPlan;
	}

	public int getScore() {
		return score;

	}

	public void setDateToday(Date date) {
		this.dateToday = date;
	}

	public void setActIdBreakFast(int actId) {
		this.actIdBreakfast = actId;
	}

	public void setActIdLunch(int actId) {
		this.actIdLunch = actId;
	}

	public void setActIdSnack(int actId) {
		this.actIdSnack = actId;
	}

	public void setActIdDinner(int actId) {
		this.actIdDinner = actId;
	}

	public void setActIdBreakFastPlan(int actId) {
		this.actIdBreakfastPlan = actId;
	}

	public void setActIdLunchPlan(int actId) {
		this.actIdLunchPlan = actId;
	}

	public void setActIdSnackPlan(int actId) {
		this.actIdSnackPlan = actId;
	}

	public void setActIdDinnerPlan(int actId) {
		this.actIdDinnerPlan = actId;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
