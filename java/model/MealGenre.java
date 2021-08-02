package model;

public class MealGenre {
	private int mealGenreId;
	private String mealGenreName;

	public MealGenre() {
	}

	public MealGenre(int mealGenreId, String mealGenreName) {
		this.mealGenreId = mealGenreId;
		this.mealGenreName = mealGenreName;
	}

	public int getMealGenreId() {
		return mealGenreId;
	}

	public String getMealGenreName() {
		return mealGenreName;
	}
}
