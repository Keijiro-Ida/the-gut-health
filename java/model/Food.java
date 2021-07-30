package model;

public class Food {
	private int foodId;
	private String foodName;
	private int digestionMinutes_int;

	public Food() {
	}

	public Food(int foodId, String foodName, int digestionMinutes_int) {
		this.foodId = foodId;
		this.digestionMinutes_int = digestionMinutes_int;
		this.foodName = foodName;
	}

	public int getFoodId() {
		return foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public int getDigestionMinutes_int() {
		return digestionMinutes_int;
	}
}
