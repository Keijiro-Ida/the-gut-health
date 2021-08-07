package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PlanAndResult implements Serializable {
	private int usrId;
	private int planAndResultId;
	private LocalDate planAndResultDate;
	private int actIdBreakfastPlan;
	private int actIdBreakfast;
	private int actIdAMSnackPlan;
	private int actIdAMSnack;
	private int actIdLunchPlan;
	private int actIdLunch;
	private int actIdPMSnackPlan;
	private int actIdPMSnack;
	private int actIdDinnerPlan;
	private int actIdDinner;
	private int actIdNightSnackPlan;
	private int actIdNightSnack;
	private int scorePlan;
	private int score;
	private boolean isCommitted;

	public PlanAndResult() {
	}

	public PlanAndResult(int usrId, int planAndResultId, LocalDate planAndResultDate) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.planAndResultDate = planAndResultDate;

	}

	public PlanAndResult(int usrId, int planAndResultId, LocalDate planAndResultDate,
			int actIdBreakfastPlan, int actIdBreakfast,
			int actIdAMSnackPlan, int actIdAMSnack,
			int actIdLunchPlan, int actIdLunch,
			int actIdPMSnackPlan, int actIdPMSnack,
			int actIdDinnerPlan, int actIdDinner,
			int actIdNightSnackPlan, int actIdNightSnack,
			int scorePlan, int score, boolean isCommitted) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.planAndResultDate = planAndResultDate;
		this.actIdBreakfastPlan = actIdBreakfastPlan;
		this.actIdBreakfast = actIdBreakfast;
		this.actIdAMSnackPlan = actIdAMSnackPlan;
		this.actIdAMSnack = actIdAMSnack;
		this.actIdLunchPlan = actIdLunchPlan;
		this.actIdLunch = actIdLunch;
		this.actIdPMSnackPlan = actIdPMSnackPlan;
		this.actIdPMSnack = actIdPMSnack;
		this.actIdDinnerPlan = actIdDinnerPlan;
		this.actIdDinner = actIdDinner;
		this.actIdNightSnackPlan = actIdNightSnackPlan;
		this.actIdNightSnack = actIdNightSnack;
		this.scorePlan = scorePlan;
		this.score = score;
		this.isCommitted = isCommitted;
	}

	public int getUsrId() {
		return usrId;
	}

	public int getPlanAndResultId() {
		return planAndResultId;
	}

	public LocalDate getPlanAndResultDate() {
		return planAndResultDate;
	}

	public int getActIdBreakfastPlan() {
		return actIdBreakfastPlan;
	}

	public int getActIdBreakfast() {
		return actIdBreakfast;
	}

	public int getActIdAMSnackPlan() {
		return actIdAMSnackPlan;
	}

	public int getActIdAMSnack() {
		return actIdAMSnack;
	}

	public int getActIdLunchPlan() {
		return actIdLunchPlan;
	}

	public int getActIdLunch() {
		return actIdLunch;
	}

	public int getActIdPMSnackPlan() {
		return actIdPMSnackPlan;
	}

	public int getActIdPMSnack() {
		return actIdPMSnack;
	}

	public int getActIdDinnerPlan() {
		return actIdDinnerPlan;
	}

	public int getActIdDinner() {
		return actIdDinner;
	}

	public int getActIdNightSnackPlan() {
		return actIdNightSnackPlan;
	}

	public int getActIdNightSnack() {
		return actIdNightSnack;
	}

	public int getScorePlan() {
		return scorePlan;

	}

	public int getScore() {
		return score;

	}

	public boolean getIsCommitted() {
		return isCommitted;
	}

	public void setPlanAndResultDate(LocalDate planAndResultDate) {
		this.planAndResultDate = planAndResultDate;
	}

	public void setActIdBreakfastPlan(int actId) {
		this.actIdBreakfastPlan = actId;
	}

	public void setActIdBreakfast(int actId) {
		this.actIdBreakfast = actId;
	}

	public void setActIdAMSnackPlan(int actId) {
		this.actIdAMSnackPlan = actId;
	}

	public void setActIdAMSnack(int actId) {
		this.actIdAMSnack = actId;
	}

	public void setActIdLunchPlan(int actId) {
		this.actIdLunchPlan = actId;
	}

	public void setActIdLunch(int actId) {
		this.actIdLunch = actId;
	}

	public void setActIdPMSnackPlan(int actId) {
		this.actIdPMSnackPlan = actId;
	}

	public void setActIdPMSnack(int actId) {
		this.actIdPMSnack = actId;
	}

	public void setActIdDinnerPlan(int actId) {
		this.actIdDinnerPlan = actId;
	}

	public void setActIdDinner(int actId) {
		this.actIdDinner = actId;
	}

	public void setActIdNightSnackPlan(int actId) {
		this.actIdNightSnackPlan = actId;
	}

	public void setActIdNightSnack(int actId) {
		this.actIdNightSnack = actId;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setScorePlan(int scorePlan) {
		this.scorePlan = scorePlan;
	}

	public void setIsCommitted(boolean isCommitted) {
		this.isCommitted = isCommitted;
	}
}
