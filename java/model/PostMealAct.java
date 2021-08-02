package model;

import java.time.LocalDateTime;

public class PostMealAct {
	private int planAndResultId;
	private LocalDateTime actTime;
	private int mealId;
	private int threeMealsId;

	public PostMealAct() {
	}

	public PostMealAct(int planAndResultId, LocalDateTime actTime, int mealId, int threeMealsId) {
		this.planAndResultId = planAndResultId;
		this.actTime = actTime;
		this.mealId = mealId;
		this.threeMealsId = threeMealsId;
	}

	public int getPlanAndResultId() {
		return planAndResultId;
	}

	public LocalDateTime getActTime() {
		return actTime;
	}

	public int getMealId() {
		return mealId;
	}

	public int getThreeMealsId() {
		return threeMealsId;
	}
}
