package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PlanAndResult implements Serializable {
	private int usrId;
	private int planAndResultId;
	private LocalDate date;

	private int actIdBreakfast;
	private int actIdLunch;
	private int actIdSnack;
	private int actIdDinner;
	private int score;
	private int actIdBreakfastPlan;
	private int actIdLunchPlan;
	private int actIdSnackPlan;
	private int actIdDinnerPlan;
	private int scorePlan;

	public PlanAndResult() {
	}

	public PlanAndResult(int usrId, int planAndResultId, LocalDate date) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.date = date;

	}

	public PlanAndResult(int usrId, int planAndResultId, LocalDate date,
			int actIdBreakFast, int actIdLunch, int actIdSnack,
			int actIdDinner, int score, int actIdBreakfastPlan, int actIdLunchPlan,
			int actIdSnackPlan, int actIdDinnerPlan, int scorePlan) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.date = date;
		this.actIdBreakfast = actIdBreakFast;
		this.actIdLunch = actIdLunch;
		this.actIdSnack = actIdSnack;
		this.actIdDinner = actIdDinner;
		this.score = score;
		this.actIdBreakfastPlan = actIdBreakfastPlan;
		this.actIdLunchPlan = actIdLunchPlan;
		this.actIdSnackPlan = actIdSnackPlan;
		this.actIdDinnerPlan = actIdDinnerPlan;
		this.scorePlan = scorePlan;
	}

	public int getUsrId() {
		return usrId;
	}

	public int getPlanAndResultId() {
		return planAndResultId;
	}

	public LocalDate getDate() {
		return date;
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

	public int getScore() {
		return score;

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

	public int getScorePlan() {
		return scorePlan;

	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setActIdBreakfast(int actId) {
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

	public void setScore(int score) {
		this.score = score;
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

	public void setScorePlan(int scorePlan) {
		this.scorePlan = scorePlan;
	}
}
