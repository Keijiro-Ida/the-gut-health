package model;

import java.io.Serializable;

public class ActDate implements Serializable {
	private int usrId;
	private int dateId;
	private ActDate actDate;

	private int actIdBreakfast;
	private int actIdLunch;
	private int actIdSnack;
	private int actIdDinner;
	private int actIdBreakfastPlan;
	private int actIdLunchPlan;
	private int actIdSnackPlan;
	private int actIdDinnerPlan;
	private int score;

	public ActDate() {
	}

	public ActDate(int usrId, int dateId, ActDate actDate,
			int actIdBreakFast, int actIdLunch, int actIdSnack,
			int actIdDinner, int actIdBreakfastPlan, int actIdLunchPlan,
			int actIdSnackPlan, int actIdDinnerPlan) {
		this.usrId = usrId;
		this.dateId = dateId;
		this.actDate = actDate;
		this.actIdBreakfast = actIdBreakFast;
		this.actIdLunch = actIdLunch;
		this.actIdSnack = actIdSnack;
		this.actIdDinner = actIdDinner;
		this.actIdBreakfastPlan = actIdBreakfastPlan;
		this.actIdLunchPlan = actIdLunchPlan;
		this.actIdSnackPlan = actIdSnackPlan;
		this.actIdDinnerPlan = actIdDinnerPlan;
	}

	public int getUsrId() {
		return usrId;
	}

	public int getDateId() {
		return dateId;
	}

	public ActDate getActDate() {
		return actDate;
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

}
