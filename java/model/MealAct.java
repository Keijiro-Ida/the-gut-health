package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MealAct implements Serializable {
	private int usrId;
	private int planAndResultId;
	private int actId;
	private LocalDateTime actTime;
	private int mealId;
	private int mealTypeId;

	public MealAct() {
	}

	public MealAct(int usrId, int planAndResultId, int actId, LocalDateTime actTime, int mealId, int mealTypeId) {
		this.usrId = usrId;
		this.planAndResultId = planAndResultId;
		this.actId = actId;
		this.actTime = actTime;
		this.mealId = mealId;
		this.mealTypeId = mealTypeId;

	}

	public int getUsrId() {
		return usrId;
	};

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

	public int getMealTypeId() {
		return mealTypeId;
	};
}