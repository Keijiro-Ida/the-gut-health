package model;

public class Meal {

	private int mealId;
	private String mealName;
	private int foodId;

	public Meal() {
	}

	public Meal(int mealId, String mealName, int foodId) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.foodId = foodId;
	}

	public int getMealId() {
		return mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public int getFoodId() {
		return foodId;
	}

}
