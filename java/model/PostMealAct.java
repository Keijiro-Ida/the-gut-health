package model;

import java.time.LocalDateTime;

public class PostMealAct {
	private int usrId;
	private int planAndResultId;
	private LocalDateTime actTime;
	private int mealId;
	private int mealTypeId;

	public PostMealAct() {
	}

	public PostMealAct(int usrId, int planAndResultId, LocalDateTime actTime, int mealId, int mealTypeId) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.actTime = actTime;
		this.mealId = mealId;
		this.mealTypeId = mealTypeId;
	}

	public int getUsrId() {
		return usrId;

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

	public int getMealTypeId() {
		return mealTypeId;
	}
}
