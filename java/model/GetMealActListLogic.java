package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetMealActListLogic {

	public ArrayList<MealAct> execute(PlanAndResult planAndResult) throws SQLException {
		ArrayList<MealAct> mealActList = new ArrayList<MealAct>();
		GetMealActLogic bo3 = new GetMealActLogic();
		MealAct mealAct1 = bo3.execute(planAndResult.getActIdBreakfastPlan());
		MealAct mealAct2 = bo3.execute(planAndResult.getActIdBreakfast());
		MealAct mealAct3 = bo3.execute(planAndResult.getActIdAMSnackPlan());
		MealAct mealAct4 = bo3.execute(planAndResult.getActIdAMSnack());
		MealAct mealAct5 = bo3.execute(planAndResult.getActIdLunchPlan());
		MealAct mealAct6 = bo3.execute(planAndResult.getActIdLunch());
		MealAct mealAct7 = bo3.execute(planAndResult.getActIdPMSnackPlan());
		MealAct mealAct8 = bo3.execute(planAndResult.getActIdPMSnack());
		MealAct mealAct9 = bo3.execute(planAndResult.getActIdDinnerPlan());
		MealAct mealAct10 = bo3.execute(planAndResult.getActIdDinner());
		MealAct mealAct11 = bo3.execute(planAndResult.getActIdNightSnackPlan());
		MealAct mealAct12 = bo3.execute(planAndResult.getActIdNightSnack());
		mealActList.add(mealAct1);
		mealActList.add(mealAct2);
		mealActList.add(mealAct3);
		mealActList.add(mealAct4);
		mealActList.add(mealAct5);
		mealActList.add(mealAct6);
		mealActList.add(mealAct7);
		mealActList.add(mealAct8);
		mealActList.add(mealAct9);
		mealActList.add(mealAct10);
		mealActList.add(mealAct11);
		mealActList.add(mealAct12);
		return mealActList;
	}
}
