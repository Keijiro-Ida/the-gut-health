package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MealAct implements Serializable {
	private int planAndResultId;
	private int actId;
	private LocalDateTime actTime;
	private int mealId;
	private int threeMealsId;

	public MealAct() {
	}

	public MealAct(int planAndResultId, int actId, LocalDateTime actTime, int mealId, int threeMealsId) {
		this.planAndResultId = planAndResultId;
		this.actId = actId;
		this.actTime = actTime;
		this.mealId = mealId;
		this.threeMealsId = threeMealsId;

	}

	public int getPlanAndResultId() {
		return planAndResultId;
	};

	public int getActId() {
		return actId;
	}

	public LocalDateTime getActTime() {
		return actTime;
	};

	public int getMealId() {
		return mealId;
	};

	public int getThreeMealsId() {
		return threeMealsId;
	};
}