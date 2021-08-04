package model;

public class ThreeMealsAndSnack {
	private int threeMealsId;
	private String threeMealsName;

	public ThreeMealsAndSnack(int threeMealsId, String threeMealsName) {
		this.threeMealsId = threeMealsId;
		this.threeMealsName = threeMealsName;
	}

	public int getThreeMealsId() {
		return threeMealsId;
	}

	public String getThreeMealsName() {
		return threeMealsName;
	}
}
