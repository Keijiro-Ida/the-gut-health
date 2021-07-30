package model;

public class MealType {
	private int mealTypeId;
	private String mealTypeName;

	public MealType(int mealTypeId, String mealTypeName) {
		this.mealTypeId = mealTypeId;
		this.mealTypeName = mealTypeName;
	}

	public int getMealTypeId() {
		return mealTypeId;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}
}
