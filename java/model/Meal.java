package model;

public class Meal {

	private int mealId;
	private String mealName;
	private int foodId;
	private int mealGenreId;

	public Meal() {
	}

	public Meal(int mealId, String mealName, int foodId, int mealGenreId) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.foodId = foodId;
		this.mealGenreId = mealGenreId;
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

	public int getMealGenreId() {
		return mealGenreId;
	}

}
